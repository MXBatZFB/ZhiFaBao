package com.zfb.zhifabao.common.factory.model.api.consultation;

import java.util.List;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class GetLaborLawResultModel {


    private String city;
    private List<LawListsBean> LawLists;

    public List<LawListsBean> getLawLists() {
        return LawLists;
    }

    public void setLawLists(List<LawListsBean> LawLists) {
        this.LawLists = LawLists;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static class LawListsBean {
        /**
         * lawName : 深圳市中级人民法院关于劳动争议案件审理流程管理规程
         */

        private String lawName;

        public String getLawName() {
            return lawName;
        }

        public void setLawName(String lawName) {
            this.lawName = lawName;
        }
    }
}
