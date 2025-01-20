package org.tukcapstone.jetsetgo.global.response.result.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.tukcapstone.jetsetgo.global.response.result.ResultCode;

@Getter
@RequiredArgsConstructor
public enum TravelThemeResultCode implements ResultCode {
    GET_THEME_LIST(200, "STT000", "성공적으로 여행 테마를 조회했습니다."),
    ;
    private final int status;
    private final String code;
    private final String message;
}
