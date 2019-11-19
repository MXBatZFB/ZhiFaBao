package com.zfb.zhifabao.common.factory.model.api.cases;

import java.util.List;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class GetCaseTypeResultModel {


    private List<CaseTypesBean> caseTypes;

    public List<CaseTypesBean> getCaseTypes() {
        return caseTypes;
    }

    public void setCaseTypes(List<CaseTypesBean> caseTypes) {
        this.caseTypes = caseTypes;
    }

    public static class CaseTypesBean {
        /**
         * casesTypeName : 劳资双方存在合同关系
         * lawFileNames : ["违法解除劳动合同赔偿金额","补偿加班工资/节假日工资/休假工资","对劳动者名誉的赔偿","确认解除（终止）劳动合同或者请求继续履行"]
         */

        private boolean isSelected;
        private String casesTypeName;
        private List<String> lawFileNames;


        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getCasesTypeName() {
            return casesTypeName;
        }

        public void setCasesTypeName(String casesTypeName) {
            this.casesTypeName = casesTypeName;
        }

        public List<String> getLawFileNames() {
            return lawFileNames;
        }

        public void setLawFileNames(List<String> lawFileNames) {
            this.lawFileNames = lawFileNames;
        }
    }
}
