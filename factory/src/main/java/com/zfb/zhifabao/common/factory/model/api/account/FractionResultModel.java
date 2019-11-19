package com.zfb.zhifabao.common.factory.model.api.account;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class FractionResultModel {

    private String sum;

    private String describe;

    public FractionResultModel(String sum, String describe) {
        this.sum = sum;
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "FractionResultModel{" +
                "sum='" + sum + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
