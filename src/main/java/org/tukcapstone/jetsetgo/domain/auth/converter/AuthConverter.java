package org.tukcapstone.jetsetgo.domain.auth.converter;

import org.springframework.stereotype.Component;
import org.tukcapstone.jetsetgo.domain.auth.dto.AuthResponse;
import org.tukcapstone.jetsetgo.domain.user.entity.User;

@Component
public class AuthConverter {
    public AuthResponse.LoginResponse convertToLoginRefreshResponse(User user, String token, String refreshToken, boolean isNewUser) {
        return AuthResponse.LoginResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .isNewUser(isNewUser)
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }
}
