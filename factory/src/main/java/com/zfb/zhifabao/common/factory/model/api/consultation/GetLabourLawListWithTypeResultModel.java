package com.zfb.zhifabao.common.factory.model.api.consultation;

import java.util.List;


public class GetLabourLawListWithTypeResultModel {
    private List<LawListBean> lawList;

    public List<LawListBean> getLawList() {
        return lawList;
    }

    public void setLawList(List<LawListBean> lawList) {
        this.lawList = lawList;
    }

    public static class LawListBean {
        /**
         * id : 1
         * title : 中华人民共和国公司法
         */

        private String id;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
