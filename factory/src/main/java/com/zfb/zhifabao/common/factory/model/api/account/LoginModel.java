package com.zfb.zhifabao.common.factory.model.api.account;

public class LoginModel {
    private String phone;
    private String passwd;
    private String jPushRegisterId;

    public LoginModel(String phonenumber, String passwd, String jPushRegisterId) {
        this.phone = phonenumber;
        this.passwd = passwd;
        this.jPushRegisterId = jPushRegisterId;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "phonenumber='" + phone + '\'' +
                ", passwd='" + passwd + '\'' +
                ", jPushRegisterId='" + jPushRegisterId + '\'' +
                '}';
    }

    public String getjPushRegisterId() {
        return jPushRegisterId;
    }

    public void setjPushRegisterId(String jPushRegisterId) {
        this.jPushRegisterId = jPushRegisterId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

}
