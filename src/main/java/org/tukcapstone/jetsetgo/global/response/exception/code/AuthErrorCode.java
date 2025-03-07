package org.tukcapstone.jetsetgo.global.response.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.tukcapstone.jetsetgo.global.response.exception.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    INVALID_SOCIAL_TYPE(404, "ECA001", "잘못된 소셜 타입"),
    INVALID_ACCESS_TOKEN(401, "ECA002", "잘못된 AccessToken"),
    INVALID_REFRESH_TOKEN(401, "ECA003", "잘못된 RefreshToken"),
    UNAUTHORIZED(401, "ECA004", "인증되지 않음"),
    ;
    private final int status;
    private final String code;
    private final String message;
}
