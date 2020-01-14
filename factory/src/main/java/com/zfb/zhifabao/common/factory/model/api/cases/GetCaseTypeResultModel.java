package com.zfb.zhifabao.common.factory.model.api.cases;

import java.util.List;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 * 根据原被告获取相应案件类型结果的封装类
 */
public class GetCaseTypeResultModel {


    private List<CaseTypeListBean> caseTypeList;

    public List<CaseTypeListBean> getCaseTypeList() {
        return caseTypeList;
    }

    public void setCaseTypeList(List<CaseTypeListBean> caseTypeList) {
        this.caseTypeList = caseTypeList;
    }

    public static class CaseTypeListBean {
        /**
         * type : 变更劳动合同
         */

        private boolean isSelected;
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }


    }
}
