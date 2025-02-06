package org.tukcapstone.jetsetgo.domain.auth.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.tukcapstone.jetsetgo.domain.auth.dto.AuthResponse;
import org.tukcapstone.jetsetgo.global.external.KakaoResponse;
import org.tukcapstone.jetsetgo.global.external.KakaoUserClient;
import org.tukcapstone.jetsetgo.global.response.exception.GeneralException;
import org.tukcapstone.jetsetgo.global.response.exception.code.UserErrorCode;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final KakaoUserClient kakaoUserClient;

    @Override
    @Transactional
    public AuthResponse.LoginResponse kakaoLogin(String accessToken) {
        // "Bearer " 접두어를 붙여 헤더에 전달
        String authorization = "Bearer " + accessToken;
        KakaoResponse.KakaoUserResponse userResponse = kakaoUserClient.getUserInfo(authorization);

        if (userResponse == null || userResponse.getId() == null) {
            throw new GeneralException(UserErrorCode.KAKAO_USER_INFO_NOT_FOUND);
        }

        Long memberId = userResponse.getId();
        String name = userResponse.getProperties().getNickname();

        return AuthResponse.LoginResponse.builder()
                .memberId(memberId)
                .name(name)
                .build();
    }
}
