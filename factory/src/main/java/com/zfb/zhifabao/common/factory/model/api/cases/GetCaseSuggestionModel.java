package com.zfb.zhifabao.common.factory.model.api.cases;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class GetCaseSuggestionModel {
    private String caseType;
    private String eleCaseType;

    public GetCaseSuggestionModel(String caseType, String eleCaseType) {
        this.caseType = caseType;
        this.eleCaseType = eleCaseType;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getEleCaseType() {
        return eleCaseType;
    }

    public void setEleCaseType(String eleCaseType) {
        this.eleCaseType = eleCaseType;
    }
}
