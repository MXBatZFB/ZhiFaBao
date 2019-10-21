package com.zfb.zhifabao.common.factory.model.api.account;

public class ResModel<T> {
    /**
     * code : 101
     * message : 请输入正确的手机号
     * data :返回的数据
     */
    private int code;
    private String message;
    private T result;

    public ResModel() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResModel{" +
                "code=" + code +
                ", messgage='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
