package org.tukcapstone.jetsetgo.domain.auth.service;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.tukcapstone.jetsetgo.domain.auth.converter.KakaoAuthConverter;
import org.tukcapstone.jetsetgo.domain.auth.dto.AuthResponse.LoginResponse;
import org.tukcapstone.jetsetgo.domain.auth.entity.RefreshToken;
import org.tukcapstone.jetsetgo.domain.auth.repository.RefreshTokenRepository;
import org.tukcapstone.jetsetgo.domain.user.entity.User;
import org.tukcapstone.jetsetgo.domain.user.entity.enums.SocialType;
import org.tukcapstone.jetsetgo.domain.user.repository.UserRepository;
import org.tukcapstone.jetsetgo.global.external.KakaoResponse;
import org.tukcapstone.jetsetgo.global.external.KakaoUserClient;
import org.tukcapstone.jetsetgo.global.response.exception.GeneralException;
import org.tukcapstone.jetsetgo.global.response.exception.code.UserErrorCode;
import org.tukcapstone.jetsetgo.global.security.JwtTokenProvider;

@Service
@AllArgsConstructor
public class KakaoAuthServiceImpl implements SocialAuthService {

    private static final String BEARER_PREFIX = "Bearer ";

    private final KakaoUserClient kakaoUserClient;
    private final JwtTokenProvider jwtTokenProvider;
    private final KakaoAuthConverter kakaoAuthConverter;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public SocialType getProvider() {
        return SocialType.KAKAO;
    }

    @Override
    public LoginResponse authenticate(String accessToken) {
        var kakaoUserResponse = retrieveKakaoUserResponse(accessToken);
        var authUser = findOrCreateUser(kakaoUserResponse);

        var token = jwtTokenProvider.createToken(authUser.getUser().getId(), authUser.getUser().getName());
        var refreshToken = jwtTokenProvider.createRefreshToken(authUser.getUser().getId());

        saveOrUpdateRefreshToken(authUser.getUser().getId(), refreshToken);

        return kakaoAuthConverter.convertToLoginResponse(authUser.getUser(), token, refreshToken, authUser.isNewUser());
    }

    private KakaoResponse.KakaoUserResponse retrieveKakaoUserResponse(String accessToken) {
        var authorizationHeader = BEARER_PREFIX + accessToken;
        var userResponse = kakaoUserClient.getUserInfo(authorizationHeader);
        if (userResponse == null || userResponse.getId() == null) {
            throw new GeneralException(UserErrorCode.KAKAO_USER_INFO_NOT_FOUND);
        }
        return userResponse;
    }

    private AuthenticatedUser findOrCreateUser(KakaoResponse.KakaoUserResponse userResponse) {
        var kakaoId = userResponse.getId();
        return userRepository.findBySocialId(kakaoId)
                .map(user -> new AuthenticatedUser(user, false))
                .orElseGet(() -> {
                    var newUser = kakaoAuthConverter.convertToUser(userResponse, getProvider());
                    newUser = userRepository.save(newUser);
                    return new AuthenticatedUser(newUser, true);
                });
    }

    private void saveOrUpdateRefreshToken(Long userId, String newRefreshToken) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userId)
                .orElse(new RefreshToken(userId, newRefreshToken));

        refreshToken.updateToken(newRefreshToken);
        refreshTokenRepository.save(refreshToken);
    }

    @Value
    private static class AuthenticatedUser {
        User user;
        boolean newUser;
    }
}
