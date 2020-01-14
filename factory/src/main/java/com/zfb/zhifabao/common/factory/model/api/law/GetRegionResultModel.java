package com.zfb.zhifabao.common.factory.model.api.law;

import java.util.List;

/**
 * 获取全国省市列表结果的封装类
 */
public class GetRegionResultModel {

    private List<ProvinceListBean> provinceList;

    public List<ProvinceListBean> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<ProvinceListBean> provinceList) {
        this.provinceList = provinceList;
    }

    @Override
    public String toString() {
        return "GetRegionResultModel{" +
                "provinceList=" + provinceList +
                '}';
    }

    public static class ProvinceListBean {
        /**
         * id : 110000
         * name : 北京
         */

        private String id;
        private String name;

        public ProvinceListBean(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "ProvinceListBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
