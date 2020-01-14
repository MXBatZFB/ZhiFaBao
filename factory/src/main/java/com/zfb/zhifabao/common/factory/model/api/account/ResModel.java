package com.zfb.zhifabao.common.factory.model.api.account;

public class ResModel<T> {
    /**
     * code : 101
     * message : 请输入正确的手机号
     * data :返回的数据
     */
    private int code;
    private String msg;
    private T data;

    public ResModel() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

