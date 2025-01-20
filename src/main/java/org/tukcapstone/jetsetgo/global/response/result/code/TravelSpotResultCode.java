package org.tukcapstone.jetsetgo.global.response.result.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.tukcapstone.jetsetgo.global.response.result.ResultCode;

@Getter
@RequiredArgsConstructor
public enum TravelSpotResultCode implements ResultCode {
    GET_COUNTRY_LIST(200, "ST000", "성공적으로 국가를 조회했습니다."),
    GET_CITY_LIST(200, "ST001", "성공적으로 해당 국가의 시 / 도를 조회했습니다."),
    ;
    private final int status;
    private final String code;
    private final String message;
}
