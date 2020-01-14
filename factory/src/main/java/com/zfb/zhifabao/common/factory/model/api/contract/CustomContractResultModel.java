package com.zfb.zhifabao.common.factory.model.api.contract;

public class CustomContractResultModel {


    /**
     * id : 48
     * filename : XXX的定制合同7.doc
     * fileurl : https://zhifabao.oss-cn-shenzhen.aliyuncs.com/userInfo/741751c925ce49ff95ee3929e4ba9b17/customContract/jce的定制合同7.doc
     */

    private int id;
    private String filename;
    private String fileurl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    @Override
    public String toString() {
        return "CustomContractResultModel{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", fileurl='" + fileurl + '\'' +
                '}';
    }
}
