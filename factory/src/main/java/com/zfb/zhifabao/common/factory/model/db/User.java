//package com.zfb.zhifabao.common.factory.model.db;
//
//import com.raizlabs.android.dbflow.annotation.Column;
//import com.raizlabs.android.dbflow.annotation.Table;
//
///**
// * 作者：Maodelong
// * 邮箱：mdl_android@163.com
// */
//@Table(database = AppDataBase.class)
//public class User {
//    @Column
//    private String userId;
//    @Column
//    private String account;
//    @Column
//    private String portrait;
//    @Column
//    private String userName;
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "account='" + account + '\'' +
//                ", portrait='" + portrait + '\'' +
//                ", userName='" + userName + '\'' +
//                '}';
//    }
//
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getAccount() {
//        return account;
//    }
//
//    public void setAccount(String account) {
//        this.account = account;
//    }
//
//    public String getPortrait() {
//        return portrait;
//    }
//
//    public void setPortrait(String portrait) {
//        this.portrait = portrait;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public User() {
//    }
//
//    public User(String account, String portrait, String userName) {
//        this.account = account;
//        this.portrait = portrait;
//        this.userName = userName;
//    }
//}
