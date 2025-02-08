package org.tukcapstone.jetsetgo.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public abstract class AuthResponse {
    private AuthResponse(){}

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResponse{
        private Long userId;
        private String name;
        private String email;
        private boolean isNewUser;
        private String token;
    }
}
