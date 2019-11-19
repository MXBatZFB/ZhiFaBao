package com.zfb.zhifabao.common.factory.model.api.consultation;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class LaborLawBean {

    private String lawName;

    public LaborLawBean(String lawName) {
        this.lawName = lawName;
    }

    @Override
    public String toString() {
        return "LaborLawBean{" +
                "lawName='" + lawName + '\'' +
                '}';
    }

    public String getLawName() {
        return lawName;
    }

    public void setLawName(String lawName) {
        this.lawName = lawName;
    }
}
