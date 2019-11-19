package com.zfb.zhifabao.common.factory.model.api.consultation;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class LoadLookFileModel {
    private String downloadOrcontent;
    private String filename;

    public LoadLookFileModel(String downloadOrcontent, String filename) {
        this.downloadOrcontent = downloadOrcontent;
        this.filename = filename;
    }

    public String getDownloadOrcontent() {
        return downloadOrcontent;
    }

    public void setDownloadOrcontent(String downloadOrcontent) {
        this.downloadOrcontent = downloadOrcontent;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "LoadLookFileModel{" +
                "downloadOrcontent='" + downloadOrcontent + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}

