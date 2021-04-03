package com.aseng.response;

import com.aseng.exception.ErrorCode;
import com.aseng.exception.ServerException;

public class FailureResponse {
    private ErrorCode errorCode;
    private String field;
    private String message;

    public FailureResponse(ServerException ex) {
        this.errorCode = ex.getErrorCode();
        this.field = ex.getErrorCode().getField();
        this.message = ex.getErrorCode().getMessage();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
