package com.zfb.zhifabao.common.factory.model.api.account;

public class LoginModel {
    private String phonenumber;
    private String passwd;

    public LoginModel(String username, String passwd) {
        this.phonenumber = username;
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "username='" + phonenumber + '\'' +
                ", password='" + passwd + '\'' +
                '}';
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
