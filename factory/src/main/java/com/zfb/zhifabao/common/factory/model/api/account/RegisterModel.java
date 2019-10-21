package com.zfb.zhifabao.common.factory.model.api.account;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class RegisterModel {
    private String phonenumber;
    private String passwd;
    private String smscode;

    public RegisterModel(String phonenumber, String username, String passwd, String age, String smscode) {
        this.phonenumber = phonenumber;
        this.passwd = passwd;
        this.smscode = smscode;
    }

    @Override
    public String toString() {
        return "RegisterModel{" +
                "phonenumber='" + phonenumber + '\'' +
                ", smscode='" + smscode + '\'' +
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

    public String getSmscode() {
        return smscode;
    }

    public void setSmscode(String smscode) {
        this.smscode = smscode;
    }
}
