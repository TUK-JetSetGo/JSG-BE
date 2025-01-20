package org.tukcapstone.jetsetgo.global.response.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.tukcapstone.jetsetgo.global.response.exception.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum TravelSpotErrorCode implements ErrorCode {
    NOT_FOUND_COUNTRY(400, "ETS000", "존재 하지 않는 국가입니다."),

    ;
    private final int status;
    private final String code;
    private final String message;
}
