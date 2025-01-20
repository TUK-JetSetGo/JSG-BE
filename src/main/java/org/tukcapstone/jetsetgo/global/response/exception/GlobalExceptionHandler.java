package org.tukcapstone.jetsetgo.global.response.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tukcapstone.jetsetgo.global.response.ResultResponse;
import org.tukcapstone.jetsetgo.global.response.exception.code.CommonErrorCode;

import java.util.List;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ResultResponse<Object>> handleGeneralException(GeneralException ex) {
        log.error("Error Code: {}, Message: {}, Data: {}",
                ex.getErrorCode().getCode(),
                ex.getErrorCode().getMessage(),
                ex.getData() != null ? ex.getData() : "No additional data",
                ex
        );

        return ResponseEntity
                .status(HttpStatus.valueOf(ex.getErrorCode().getStatus()))
                .body(ResultResponse.onFailure(
                        ex.getErrorCode(),
                        ex.getData()
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //@Valid 검증 실패시 사용
    public ResponseEntity<ResultResponse<Object>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        List<String> errorMessages = getValidationErrorMessages(ex);
        log.error("Validation errors: {}", errorMessages, ex);

        return ResponseEntity
                .status(HttpStatus.valueOf(CommonErrorCode.BAD_REQUEST.getStatus()))
                .body(ResultResponse.onFailure(
                        CommonErrorCode.BAD_REQUEST,
                        errorMessages
                ));
    }

    @ExceptionHandler(ConstraintViolationException.class) //URL 파라미터나 경로 변수의 유효성 검사 실패
    public ResponseEntity<ResultResponse<Object>> handleConstraintViolationException(
            ConstraintViolationException ex
    ) {
        List<String> errorMessages = getValidationErrorMessages(ex);
        log.error("Validation errors: {}", errorMessages, ex);

        return ResponseEntity
                .status(HttpStatus.valueOf(CommonErrorCode.BAD_REQUEST.getStatus()))
                .body(ResultResponse.onFailure(
                        CommonErrorCode.BAD_REQUEST,
                        errorMessages
                ));
    }

    private List<String> getValidationErrorMessages(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    String field = fieldError.getField();
                    String message = fieldError.getDefaultMessage();
                    return field + ": " + message;
                })
                .toList();
    }

    private List<String> getValidationErrorMessages(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
                .map(violation -> {
                    String field = violation.getPropertyPath().toString();
                    String message = violation.getMessage();
                    return field + ": " + message;
                })
                .toList();
    }
}
