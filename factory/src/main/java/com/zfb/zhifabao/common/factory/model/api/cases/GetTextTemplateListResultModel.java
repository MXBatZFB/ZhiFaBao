package com.zfb.zhifabao.common.factory.model.api.cases;

import java.util.List;

public class GetTextTemplateListResultModel {

    private List<CaseSolvedListBean> caseSolvedList;

    public List<CaseSolvedListBean> getCaseSolvedList() {
        return caseSolvedList;
    }

    public void setCaseSolvedList(List<CaseSolvedListBean> caseSolvedList) {
        this.caseSolvedList = caseSolvedList;
    }

    public static class CaseSolvedListBean {
        /**
         * id : 1
         * identity : 0
         * process : 0
         * title : 一审答辩状格式
         * type : doc
         * url :
         */

        private int id;
        private int identity;
        private int process;
        private String title;
        private String type;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIdentity() {
            return identity;
        }

        public void setIdentity(int identity) {
            this.identity = identity;
        }

        public int getProcess() {
            return process;
        }

        public void setProcess(int process) {
            this.process = process;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}


