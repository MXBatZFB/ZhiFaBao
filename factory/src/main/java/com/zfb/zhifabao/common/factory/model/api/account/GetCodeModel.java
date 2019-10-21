package com.zfb.zhifabao.common.factory.model.api.account;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class GetCodeModel {
    private String phonenumber;

    public GetCodeModel(String phone) {
        this.phonenumber = phone;
    }

    public String getPhone() {
        return phonenumber;
    }

    public void setPhone(String phone) {
        this.phonenumber = phone;
    }

    @Override
    public String toString() {
        return "GetCodeModel{" +
                "phone='" + phonenumber + '\'' +
                '}';
    }
}
