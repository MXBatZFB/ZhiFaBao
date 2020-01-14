package com.zfb.zhifabao.common.factory.model.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
@Table(database = AppDataBase.class)
public class ContractFileInfo extends BaseModel {
    @PrimaryKey
    private long id;
    @Column
    private String ContractId;
    @Column
    private String url;
    @Column
    private String FileName;
    @Column
    private String localUrl;
    @Column
    private String time;

    @Override
    public String toString() {
        return "ContractFileInfo{" +
                "id=" + id +
                ", ContractId='" + ContractId + '\'' +
                ", url='" + url + '\'' +
                ", FileName='" + FileName + '\'' +
                ", localUrl='" + localUrl + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public String getContractId() {
        return ContractId;
    }

    public void setContractId(String contractId) {
        ContractId = contractId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
