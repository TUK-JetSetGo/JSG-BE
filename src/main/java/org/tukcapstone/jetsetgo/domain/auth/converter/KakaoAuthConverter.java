package org.tukcapstone.jetsetgo.domain.auth.converter;

import java.util.Optional;
import org.springframework.stereotype.Component;
import org.tukcapstone.jetsetgo.domain.auth.dto.AuthResponse.LoginResponse;
import org.tukcapstone.jetsetgo.domain.user.entity.User;
import org.tukcapstone.jetsetgo.domain.user.entity.enums.SocialType;
import org.tukcapstone.jetsetgo.global.external.KakaoResponse;

@Component
public class KakaoAuthConverter {
    public User convertToUser(KakaoResponse.KakaoUserResponse kakaoUserResponse, SocialType socialType) {
        return User.builder()
                .socialId(kakaoUserResponse.getId())
                .name(kakaoUserResponse.getProperties().getNickname())
                .email(Optional.ofNullable(kakaoUserResponse.getEmail()).orElse(""))
                .socialType(socialType)
                .build();
    }

    public LoginResponse convertToLoginResponse(User user, String token, boolean isNewUser) {
        return LoginResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .token(token)
                .isNewUser(isNewUser)
                .build();
    }
}
