package org.tukcapstone.jetsetgo.global.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="kakaoUser", url="https://kapi.kakao.com")
public interface KakaoUserClient {
    @GetMapping(path="/v2/user/me", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    KakaoResponse.KakaoUserResponse getUserInfo(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    );
}
