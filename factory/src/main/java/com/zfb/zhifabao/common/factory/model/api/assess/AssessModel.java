package com.zfb.zhifabao.common.factory.model.api.assess;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class AssessModel {
    private String ceshitype;

    public AssessModel(String ceshitype) {
        this.ceshitype = ceshitype;
    }

    @Override
    public String toString() {
        return "AssessModel{" +
                "ceshitype='" + ceshitype + '\'' +
                '}';
    }

    public String getCeshitype() {
        return ceshitype;
    }

    public void setCeshitype(String ceshitype) {
        this.ceshitype = ceshitype;
    }
}
