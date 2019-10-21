package com.zfb.zhifabao.common.factory.model.api.find;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class GetFindContentModel {
    private String token;

    public GetFindContentModel(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "GetFindContentModel{" +
                "token='" + token + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
