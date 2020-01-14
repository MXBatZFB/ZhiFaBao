package com.zfb.zhifabao.common.factory.model.api.consultation;

public class GetLabourLawContentByIdResultModel {

    /**
     * id : 1
     * regionId : 440000
     * type: 公司法
     * title : 广东关于审理劳动人事争议案件若干问题的座谈会纪要
     * content : 关于印发《广东省高级人民法院 广东省劳动人事............
     */
    private String id;
    private String regionId;
    private String title;
    private String content;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
