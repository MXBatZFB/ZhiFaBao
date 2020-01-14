package com.zfb.zhifabao.common.factory.model.api.account;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class RegisterModel {
    private String phone;
    private String passwd;
    private String code;

    public RegisterModel(String phone, String passwd, String code) {
        this.phone = phone;
        this.passwd = passwd;
        this.code = code;
    }


    @Override
    public String toString() {
        return "RegisterModel{" +
                "phone='" + phone + '\'' +
                ", passwd='" + passwd + '\'' +
                ", code='" + code + '\'' +
                '}';
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
