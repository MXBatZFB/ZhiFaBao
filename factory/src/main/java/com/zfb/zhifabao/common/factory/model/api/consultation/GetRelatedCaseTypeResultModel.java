package com.zfb.zhifabao.common.factory.model.api.consultation;

import java.util.List;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class GetRelatedCaseTypeResultModel {


    private List<CaseTypeBean> caseType;

    @Override
    public String toString() {
        return "GetCaseTypeResultModel{" +
                "caseType=" + caseType +
                '}';
    }

    public List<CaseTypeBean> getCaseType() {
        return caseType;
    }

    public void setCaseType(List<CaseTypeBean> caseType) {
        this.caseType = caseType;
    }

    public static class CaseTypeBean {
        /**
         * firstType : 对劳动者名誉的赔偿
         * TypeNames : [{"secondTypeName":"曹先立与韶关市第二技师学院名誉权纠纷一审民事判决书"},{"secondTypeName":"梁卫凛与徐闻县粮食企业集团公司名誉权纠纷一审民事判决书"},{"secondTypeName":"梁永就与广州市黄埔建筑工程总公司名誉权纠纷2016民终9036二审民事判决书"}]
         */

        private String firstType;
        private boolean isSelected;
        private List<TypeNamesBean> TypeNames;

        @Override
        public String toString() {
            return "CaseTypeBean{" +
                    ", isSelected=" + isSelected +
                    '}';
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getFirstType() {
            return firstType;
        }

        public void setFirstType(String firstType) {
            this.firstType = firstType;
        }

        public List<TypeNamesBean> getTypeNames() {
            return TypeNames;
        }

        public void setTypeNames(List<TypeNamesBean> TypeNames) {
            this.TypeNames = TypeNames;
        }

        public static class TypeNamesBean {
            /**
             * secondTypeName : 曹先立与韶关市第二技师学院名誉权纠纷一审民事判决书
             */

            private String secondTypeName;

            public String getSecondTypeName() {
                return secondTypeName;
            }

            public void setSecondTypeName(String secondTypeName) {
                this.secondTypeName = secondTypeName;
            }

            @Override
            public String toString() {
                return "TypeNamesBean{" +
                        "secondTypeName='" + secondTypeName + '\'' +
                        '}';
            }
        }
    }
}
