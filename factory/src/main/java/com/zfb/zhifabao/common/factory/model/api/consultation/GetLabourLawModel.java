package com.zfb.zhifabao.common.factory.model.api.consultation;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class GetLabourLawModel {

    private String city;

    public GetLabourLawModel(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "GetLabourLawModel{" +
                "city='" + city + '\'' +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
