package org.tukcapstone.jetsetgo.global.response;

import lombok.Builder;
import lombok.Getter;
import org.tukcapstone.jetsetgo.global.response.exception.ErrorCode;
import org.tukcapstone.jetsetgo.global.response.result.ResultCode;

@Getter
@Builder
public class ResultResponse<T> {
    private final int status;
    private final String code;
    private final String message;
    private final T data;

    public static final ResultResponse<Void> OK = new ResultResponse<>(200, "SC000", "성공입니다.", null);

    public static <T> ResultResponse<T> onSuccess(ResultCode resultCode, T data) {
        return ResultResponse.<T>builder()
                .status(resultCode.getStatus())
                .code(resultCode.getCode())
                .message(resultCode.getMessage())
                .data(data)
                .build();
    }

    public static <T> ResultResponse<T> onSuccess(ResultCode resultCode) {
        return ResultResponse.<T>builder()
                .status(resultCode.getStatus())
                .code(resultCode.getCode())
                .message(resultCode.getMessage())
                .data(null)
                .build();
    }

    public static <T> ResultResponse<T> onSuccess(T data) {
        return ResultResponse.<T>builder()
                .status(OK.getStatus())
                .code(OK.getCode())
                .message(OK.getMessage())
                .data(null)
                .build();
    }

    public static <T> ResultResponse<T> onFailure(ErrorCode errorCode, T data) {
        return ResultResponse.<T>builder()
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .data(data)
                .build();
    }

    public static <T> ResultResponse<T> onFailure(ErrorCode errorCode) {
        return ResultResponse.<T>builder()
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .data(null)
                .build();
    }
}
