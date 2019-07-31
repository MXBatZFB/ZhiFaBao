package com.zfb.zhifabao.common.factory.model.api;

public class ErrorResultForLogin {
    private String error;
    private String error_description;

    public ErrorResultForLogin(String error, String error_description) {
        this.error = error;
        this.error_description = error_description;
    }

    @Override
    public String toString() {
        return "ErrorResultForLogin{" +
                "error='" + error + '\'' +
                ", error_description='" + error_description + '\'' +
                '}';
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }
}
