package com.zfb.zhifabao.common.factory.model.api.account;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class ModifyPasswordModel {
    private String phone;
    private String code;
    private  String passwd;

    public ModifyPasswordModel(String phone, String code, String passwd) {
        this.phone = phone;
        this.code = code;
        this.passwd = passwd;
    }

    public String getPhone() {
        return phone;
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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "ModifyPasswordModel{" +
                "phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
