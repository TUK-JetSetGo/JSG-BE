package org.tukcapstone.jetsetgo.domain.auth.controller;

import static org.tukcapstone.jetsetgo.global.response.result.code.UserResultCode.LOGIN;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tukcapstone.jetsetgo.domain.auth.dto.AuthRequest;
import org.tukcapstone.jetsetgo.domain.auth.dto.AuthResponse;
import org.tukcapstone.jetsetgo.domain.auth.service.AuthService;
import org.tukcapstone.jetsetgo.global.response.ResultResponse;

@RestController
@RequestMapping("/auth")
@Tag(name = "01. 인증 API", description = "인증 관련 API 입니다.")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(
            summary = "소셜 로그인/회원가입 API",
            description = """
                    소셜 인증 제공자로부터 받은 accessToken을 사용하여 로그인 또는 회원가입을 진행합니다.
                    예시 JSON:
                    ```json
                    {
                        "socialType": "KAKAO",
                        "accessToken": "액세스 토큰 값"
                    }
                    ```"""
    )
    public ResultResponse<AuthResponse.LoginResponse> login(
            @Valid @RequestBody AuthRequest.SocialLoginRequest request) {

        AuthResponse.LoginResponse loginResponse =
                authService.login(request.getSocialType(), request.getAccessToken());
        return ResultResponse.onSuccess(LOGIN, loginResponse);
    }
}
