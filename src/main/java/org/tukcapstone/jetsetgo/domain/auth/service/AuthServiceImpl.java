package org.tukcapstone.jetsetgo.domain.auth.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tukcapstone.jetsetgo.domain.auth.dto.AuthResponse;
import org.tukcapstone.jetsetgo.global.external.KakaoResponse;
import org.tukcapstone.jetsetgo.global.external.KakaoUserClient;
import org.tukcapstone.jetsetgo.global.response.exception.GeneralException;
import org.tukcapstone.jetsetgo.global.response.exception.code.UserErrorCode;
import org.tukcapstone.jetsetgo.global.util.JwtTokenProvider;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final KakaoUserClient kakaoUserClient;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public AuthResponse.LoginResponse kakaoLogin(String accessToken) {
        String authorization = "Bearer " + accessToken;
        KakaoResponse.KakaoUserResponse userResponse = kakaoUserClient.getUserInfo(authorization);

        if (userResponse == null || userResponse.getId() == null) {
            throw new GeneralException(UserErrorCode.KAKAO_USER_INFO_NOT_FOUND);
        }

        Long memberId = userResponse.getId();
        String name = userResponse.getProperties().getNickname();

        String token = jwtTokenProvider.createToken(memberId, name);

        return AuthResponse.LoginResponse.builder()
                .memberId(memberId)
                .name(name)
                .token(token)
                .build();
    }
}
