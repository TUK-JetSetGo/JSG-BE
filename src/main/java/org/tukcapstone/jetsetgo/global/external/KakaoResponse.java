package org.tukcapstone.jetsetgo.global.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public abstract class KakaoResponse {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KakaoUserResponse{
        private Long id;
        private KakaoProperties properties;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KakaoProperties{
        private String nickname;
    }
}
