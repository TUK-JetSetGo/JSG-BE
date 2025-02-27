package org.tukcapstone.jetsetgo.global.response.result.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.tukcapstone.jetsetgo.global.response.result.ResultCode;

@Getter
@RequiredArgsConstructor
public enum TravelResultCode implements ResultCode {
    // 국가 / 시 & 도
    GET_COUNTRY_LIST(200, "STS000", "성공적으로 국가를 조회했습니다."),
    GET_CITY_LIST(200, "STS001", "성공적으로 해당 국가의 시 & 도를 조회했습니다."),

    // 목적 / 테마
    GET_PURPOSE_LIST(200, "STP000", "성공적으로 여행 목적을 조회했습니다."),
    GET_THEME_LIST(200, "STT000", "성공적으로 여행 테마를 조회했습니다."),

    CREATE_TRAVEL_PLAN(200, "STP000", "성공적으로 여행 일정을 생성했습니다."),

    // 여행지
    GET_TOURIST_SPOT_LIST(200, "STT000", "성공적으로 관광지를 조회했습니다."),
    ;
    private final int status;
    private final String code;
    private final String message;
}
