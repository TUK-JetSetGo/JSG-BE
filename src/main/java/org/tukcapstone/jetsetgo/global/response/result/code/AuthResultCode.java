package org.tukcapstone.jetsetgo.global.response.result.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.tukcapstone.jetsetgo.global.response.result.ResultCode;

@Getter
@RequiredArgsConstructor
public enum AuthResultCode implements ResultCode {
    AUTHORIZED(200, "SA000", "인증되었습니다"),
    ;
    private final int status;
    private final String code;
    private final String message;
}
