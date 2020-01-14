package com.zfb.zhifabao.common.factory.model.api.law;

import java.util.List;

public class LaborLawTypeResultModel {


    private List<LawTypeListBean> lawTypeList;

    public List<LawTypeListBean> getLawTypeList() {
        return lawTypeList;
    }

    @Override
    public String toString() {
        return "LaborLawTypeResultModel{" +
                "lawTypeList=" + lawTypeList +
                '}';
    }

    public void setLawTypeList(List<LawTypeListBean> lawTypeList) {
        this.lawTypeList = lawTypeList;
    }

    public static class LawTypeListBean {
        /**
         * type : 公司法
         */

        private String type;

        @Override
        public String toString() {
            return "LawTypeListBean{" +
                    "type='" + type + '\'' +
                    '}';
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
