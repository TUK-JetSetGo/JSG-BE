package org.tukcapstone.jetsetgo.global.response.result.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.tukcapstone.jetsetgo.global.response.result.ResultCode;

@Getter
@RequiredArgsConstructor
public enum TravelPurposeResultCode implements ResultCode {
    GET_PURPOSE_LIST(200, "STP000", "성공적으로 여행 목적을 조회했습니다."),
    ;
    private final int status;
    private final String code;
    private final String message;
}
