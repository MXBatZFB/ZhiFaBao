package com.zfb.zhifabao.common.factory.model.api.law;


import java.util.List;

/**
 * 根据区域Id获取相应城市的列表封装类
 */
public class GetCityWithProvinceIdResultModel {


    private List<CityListBean> cityList;

    public List<CityListBean> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityListBean> cityList) {
        this.cityList = cityList;
    }

    public static class CityListBean {
        /**
         * id : 440100
         * name : 广州市
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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
