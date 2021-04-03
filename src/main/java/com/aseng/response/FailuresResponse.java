package com.aseng.response;

import java.util.List;

public class FailuresResponse {
    private List<FailureResponse> errors;

    public FailuresResponse(List<FailureResponse> errors) {
        this.errors = errors;
    }

    public List<FailureResponse> getErrors() {
        return errors;
    }

    public void setErrors(List<FailureResponse> errors) {
        this.errors = errors;
    }
}
