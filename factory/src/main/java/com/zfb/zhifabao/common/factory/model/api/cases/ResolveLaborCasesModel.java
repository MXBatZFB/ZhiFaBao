package com.zfb.zhifabao.common.factory.model.api.cases;


/**
 * 解决劳动案件的需要的参数封装类
 * identity	string	是	0：被告 1：原告	0
 * type	string	是	案件类型
 */
public class ResolveLaborCasesModel {
    private  String type; //案件类型
    private  String identity;//原被告
    private  String process;//流程

    public ResolveLaborCasesModel() {
    }

    public ResolveLaborCasesModel(String type, String identity, String process) {
        this.type = type;
        this.identity = identity;
        this.process = process;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ResolveLaborCasesModel{" +
                "type='" + type + '\'' +
                ", identity='" + identity + '\'' +
                ", process='" + process + '\'' +
                '}';
    }

}
