package com.zfb.zhifabao.common.factory.model.api.consultation;

import java.util.List;

public class GetLabourLawListWithRegionIdResultModel{
    private List<LocalLawTitleListBean> localLawTitleList;

    public List<LocalLawTitleListBean> getLocalLawTitleList() {
        return localLawTitleList;
    }

    public void setLocalLawTitleList(List<LocalLawTitleListBean> localLawTitleList) {
        this.localLawTitleList = localLawTitleList;
    }

    public static class LocalLawTitleListBean {
        /**
         * id : 1
         * regionId : 440000
         * title : 广东关于审理劳动人事争议案件若干问题的座谈会纪要
         */

        private String id;
        private String regionId;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRegionId() {
            return regionId;
        }

        public void setRegionId(String regionId) {
            this.regionId = regionId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
