package org.tukcapstone.jetsetgo.domain.auth.service;

import static org.tukcapstone.jetsetgo.global.response.exception.code.AuthErrorCode.INVALID_REFRESH_TOKEN;
import static org.tukcapstone.jetsetgo.global.response.exception.code.AuthErrorCode.INVALID_SOCIAL_TYPE;
import static org.tukcapstone.jetsetgo.global.response.exception.code.UserErrorCode.USER_NOT_FOUND;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tukcapstone.jetsetgo.domain.auth.converter.AuthConverter;
import org.tukcapstone.jetsetgo.domain.auth.dto.AuthResponse;
import org.tukcapstone.jetsetgo.domain.auth.entity.RefreshToken;
import org.tukcapstone.jetsetgo.domain.auth.repository.RefreshTokenRepository;
import org.tukcapstone.jetsetgo.domain.user.entity.User;
import org.tukcapstone.jetsetgo.domain.user.entity.enums.SocialType;
import org.tukcapstone.jetsetgo.domain.user.repository.UserRepository;
import org.tukcapstone.jetsetgo.global.response.exception.GeneralException;
import org.tukcapstone.jetsetgo.global.security.JwtTokenProvider;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final List<SocialAuthService> socialAuthServiceList;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final AuthConverter authConverter;
    private final RefreshTokenRepository refreshTokenRepository;


    @Override
    @Transactional
    public AuthResponse.LoginResponse login(SocialType socialType, String accessToken) {
        SocialAuthService authService = socialAuthServiceList.stream()
                .filter(service -> service.getProvider().equals(socialType))
                .findFirst()
                .orElseThrow(() -> new GeneralException(INVALID_SOCIAL_TYPE));

        return authService.authenticate(accessToken);
    }

    @Override
    @Transactional
    public AuthResponse.LoginResponse refresh(String refreshToken) {
        Long userId = jwtTokenProvider.getId(refreshToken);

        RefreshToken savedToken = refreshTokenRepository.findByUserId(userId)
                .orElseThrow(() -> new GeneralException(INVALID_REFRESH_TOKEN));

        if(!savedToken.getToken().equals(refreshToken)){
            throw new GeneralException(INVALID_REFRESH_TOKEN);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(USER_NOT_FOUND));

        String newAccessToken = jwtTokenProvider.createToken(user.getId(), user.getName());
        String newRefreshToken = jwtTokenProvider.createRefreshToken(user.getId());

        savedToken.updateToken(newRefreshToken);
        refreshTokenRepository.save(savedToken);

        return authConverter.convertToLoginRefreshResponse(user,newAccessToken, newRefreshToken, false);
    }
}
