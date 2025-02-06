package org.tukcapstone.jetsetgo.domain.auth.controller;

import static org.tukcapstone.jetsetgo.global.response.result.code.UserResultCode.LOGIN;

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
import org.tukcapstone.jetsetgo.global.response.result.code.UserResultCode;

@RestController
@RequestMapping("/auth")
@Tag(name = "01. 인증 API", description = "인증 관련 API 입니다.")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/kakao-login")
    public ResultResponse<AuthResponse.LoginResponse> kakaoLogin(
            @Valid @RequestBody AuthRequest .KakaoLoginRequest request){

        AuthResponse.LoginResponse loginResponse = authService.kakaoLogin(request.getAccessToken());
        return ResultResponse.onSuccess(LOGIN, loginResponse);
    }
}
