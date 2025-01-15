package com.tukorea.jetsetgo.global.result.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserResultCode {
    SIGNUP(200, "SM000", "성공적으로 회원가입하였습니다."),
    LOGIN(200, "SM001", "성공적으로 로그인하였습니다."),
    USER_INFO(200, "SM002", "회원 정보를 성공적으로 조회하였습니다."),
    ;
    private final int status;
    private final String code;
    private final String message;
}
