package org.tukcapstone.jetsetgo.global.response.exception;

public interface ErrorCode {
    int getStatus();
    String getCode();
    String getMessage();
}
