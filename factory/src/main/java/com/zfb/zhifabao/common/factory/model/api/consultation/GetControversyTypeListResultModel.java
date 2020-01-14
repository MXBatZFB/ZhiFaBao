package com.zfb.zhifabao.common.factory.model.api.consultation;

import java.util.List;

public class GetControversyTypeListResultModel {


    private List<CaseTypeListBean> caseTypeList;

    public List<CaseTypeListBean> getCaseTypeList() {
        return caseTypeList;
    }

    public void setCaseTypeList(List<CaseTypeListBean> caseTypeList) {
        this.caseTypeList = caseTypeList;
    }

    public static class CaseTypeListBean {
        /**
         * type : 双倍工资
         */

        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
