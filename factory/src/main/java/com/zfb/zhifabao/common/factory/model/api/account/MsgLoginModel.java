package com.zfb.zhifabao.common.factory.model.api.account;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class MsgLoginModel {
    private String phonenumber;
    private String smscode;

    public MsgLoginModel(String phonenumber, String smscode) {
        this.phonenumber = phonenumber;
        this.smscode = smscode;
    }

    @Override
    public String toString() {
        return "MsgLoginModel{" +
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

    public String getSmscode() {
        return smscode;
    }

    public void setSmscode(String smscode) {
        this.smscode = smscode;
    }
}
