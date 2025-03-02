package org.tukcapstone.jetsetgo.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.tukcapstone.jetsetgo.domain.user.entity.enums.SocialType;

public abstract class AuthRequest {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SocialLoginRequest{
        @NotBlank(message = "AccessToken은 필수 입력 값입니다.")
        private String accessToken;

        @NotNull(message = "SocialType은 필수 입력 값입니다.")
        private SocialType socialType;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RefreshTokenRequest{
        @NotBlank(message = "AccessToken은 필수 입력 값입니다.")
        private String refreshToken;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LogoutRequest{
        @NotBlank(message = "AccessToken은 필수 입력 값입니다.")
        private String refreshToken;
    }


}
