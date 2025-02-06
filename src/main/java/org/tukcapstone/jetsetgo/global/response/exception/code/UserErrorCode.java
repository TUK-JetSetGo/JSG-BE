package org.tukcapstone.jetsetgo.global.response.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.tukcapstone.jetsetgo.global.response.exception.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    KAKAO_USER_INFO_NOT_FOUND(404, "ECU003", "카카오 유저 정보를 찾을 수 없습니다.")
    ;
    private final int status;
    private final String code;
    private final String message;
}
