package org.tukcapstone.jetsetgo.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public abstract class AuthRequest {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KakaoLoginRequest{
        @NotBlank(message = "AccessToken은 필수 입력 값입니다.")
        private String accessToken;
    }
}
