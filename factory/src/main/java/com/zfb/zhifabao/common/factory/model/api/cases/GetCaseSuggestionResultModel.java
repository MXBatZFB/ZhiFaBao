package com.zfb.zhifabao.common.factory.model.api.cases;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class GetCaseSuggestionResultModel {
    private String suggestion;

    public GetCaseSuggestionResultModel(String suggestion) {
        this.suggestion = suggestion;
    }

    @Override
    public String toString() {
        return "GetCaseSuggestionResultModel{" +
                "suggestion='" + suggestion + '\'' +
                '}';
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }


}
