package com.zfb.zhifabao.common.factory.model.api.account;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class MsgLoginModel {
    private String phone;
    private String code;
    private String jPushRegisterId;

    public MsgLoginModel(String phone, String code,String jPushRegisterId) {
        this.phone = phone;
        this.code = code;
        this.jPushRegisterId = jPushRegisterId;
    }

    @Override
    public String toString() {
        return "MsgLoginModel{" +
                "phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", jPushRegisterId='" + jPushRegisterId + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public String getjPushRegisterId() {
        return jPushRegisterId;
    }

    public void setjPushRegisterId(String jPushRegisterId) {
        this.jPushRegisterId = jPushRegisterId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
