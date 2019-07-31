package com.zfb.zhifabao.common.factory.model.api;

public class ResModel<T> {
    /**
     * code : 101
     * message : 请输入正确的手机号
     * data :返回的数据
     */
    private int code;
    private String message;
    private T data ;

    public ResModel() {
    }

    @Override
    public String toString() {
        return "ResModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
}
