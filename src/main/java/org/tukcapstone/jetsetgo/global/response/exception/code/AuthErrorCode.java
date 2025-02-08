package org.tukcapstone.jetsetgo.global.response.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.tukcapstone.jetsetgo.global.response.exception.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    INVALID_SOCIAL_TYPE(404, "ECA001", "잘못된 소셜 타입")
    ;
    private final int status;
    private final String code;
    private final String message;
}
