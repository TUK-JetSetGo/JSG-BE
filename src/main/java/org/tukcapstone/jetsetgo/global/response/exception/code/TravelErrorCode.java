package org.tukcapstone.jetsetgo.global.response.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.tukcapstone.jetsetgo.global.response.exception.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum TravelErrorCode implements ErrorCode {
    // 국가 / 시 & 도
    NOT_FOUND_COUNTRY(400, "ETS000", "존재 하지 않는 국가입니다."),
    NOT_FOUND_CITY(400, "ETS001", "존재 하지 않는 시 & 도입니다."),

    // 목적 / 테마
    NOT_FOUND_PURPOSE(400, "ETS002", "존재 하지 않는 테마입니다."),
    NOT_FOUND_THEME(400, "ETS003", "존재 하지 않는 목적입니다."),
    ;
    private final int status;
    private final String code;
    private final String message;
}
