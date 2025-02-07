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

    @PostMapping("/kakao-login")
    @Operation(
            summary = "카카오 로그인 API",
            description = """
                    안드로이드 SDK로 전달받은 카카오 AccessToken을 사용하여 로그인 또는 회원가입을 진행하고, 자체 JWT 토큰을 발급합니다.
                    ### Example JSON:
                    ```json
                    {
                        "accessToken": "2Fq2mys12SF6vPBJGKVCxEXiWq0cmqgCAAAAAQoqJVEAAAGU3lw1ceZNgpjs3oAL"
                    }
                    ```"""
    )
    public ResultResponse<AuthResponse.LoginResponse> kakaoLogin(
            @Valid @RequestBody AuthRequest .KakaoLoginRequest request){

        AuthResponse.LoginResponse loginResponse = authService.kakaoLogin(request.getAccessToken());
        return ResultResponse.onSuccess(LOGIN, loginResponse);
    }
}
