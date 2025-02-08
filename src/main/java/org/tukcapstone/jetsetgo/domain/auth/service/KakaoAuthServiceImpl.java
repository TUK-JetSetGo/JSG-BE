package org.tukcapstone.jetsetgo.domain.auth.service;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.tukcapstone.jetsetgo.domain.auth.dto.AuthResponse;
import org.tukcapstone.jetsetgo.domain.auth.dto.AuthResponse.LoginResponse;
import org.tukcapstone.jetsetgo.domain.user.entity.User;
import org.tukcapstone.jetsetgo.domain.user.entity.enums.SocialType;
import org.tukcapstone.jetsetgo.domain.user.repository.UserRepository;
import org.tukcapstone.jetsetgo.global.external.KakaoResponse;
import org.tukcapstone.jetsetgo.global.external.KakaoUserClient;
import org.tukcapstone.jetsetgo.global.response.exception.GeneralException;
import org.tukcapstone.jetsetgo.global.response.exception.code.UserErrorCode;
import org.tukcapstone.jetsetgo.global.util.JwtTokenProvider;

@Service
@AllArgsConstructor
public class KakaoAuthServiceImpl implements SocialAuthService{

    private final KakaoUserClient kakaoUserClient;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Override
    public SocialType getProvider() {
        return SocialType.KAKAO;
    }

    @Override
    public LoginResponse authenticate(String accessToken) {
        String authorization = "Bearer " + accessToken;
        KakaoResponse.KakaoUserResponse userResponse = kakaoUserClient.getUserInfo(authorization);

        if (userResponse == null || userResponse.getId() == null) {
            throw new GeneralException(UserErrorCode.KAKAO_USER_INFO_NOT_FOUND);
        }

        Long kakaoId = userResponse.getId();
        String name = userResponse.getProperties().getNickname();
        String email = Optional.ofNullable(userResponse.getEmail()).orElse("");

        Optional<User> optionalUser = userRepository.findBySocialId(kakaoId);
        boolean isNewUser = false;
        User user;

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            user = User.builder()
                    .socialId(kakaoId)
                    .name(name)
                    .email(email)
                    .socialType(this.getProvider())
                    .build();
            user = userRepository.save(user);
            isNewUser = true;
        }

        String token = jwtTokenProvider.createToken(user.getId(), user.getName());

        return AuthResponse.LoginResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .token(token)
                .isNewUser(isNewUser)
                .build();
    }
}
