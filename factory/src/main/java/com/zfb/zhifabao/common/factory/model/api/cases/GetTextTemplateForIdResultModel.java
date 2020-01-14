package com.zfb.zhifabao.common.factory.model.api.cases;

public class GetTextTemplateForIdResultModel {
    /**
     * id : 1
     * identity : 0
     * process : 0
     * title : 一审答辩状格式
     * type : doc
     * content : 民事答辩状  一、民事答辩状说明：

     民事答辩状，是民事被告、被上诉人针对原告或上诉人的起诉或上诉，阐述自己认定的事实和理由，予以答复和辩驳的一种书状。依照我国《民事诉讼法》的规定，人民法院应当在立案之日起5日内将起诉状副本发送被告或被上诉人，被告或被上诉人在收到之日起15日内提出答辩状。提出答辩状是当事人的一项诉讼权利，不是诉讼义务；但被告人或被上诉人逾期不提出答辩状，不影响人民法院审理。

     民事答辩状由首部、正文、尾部三部分组成。
     * url :
     */
    private int id;
    private int identity;
    private int process;
    private String title;
    private String type;
    private String content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
