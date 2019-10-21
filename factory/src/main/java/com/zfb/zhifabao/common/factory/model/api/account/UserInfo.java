package com.zfb.zhifabao.common.factory.model.api.account;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 * 用户信息的封装类
 */
public class UserInfo {
    //用户唯一登录的账号（即注册的手机号码）
    private String phonenumber;
    //用户昵称
    private String username;
    //用户的唯一识别Id
    private String userid;
    //头像链接
    private String portrait;
    //是否是会员
    private boolean isMember;

    private String companyName;

    private String token;

    public UserInfo() {

    }

    public UserInfo(String username, String portrait, boolean isMember) {
        this.username = username;
        this.portrait = portrait;
        this.isMember = isMember;
    }

    public UserInfo(String phonenumber, String username, String portrait, boolean isMember, String companyName, String token, String userid) {
        this.phonenumber = phonenumber;
        this.username = username;
        this.portrait = portrait;
        this.isMember = isMember;
        this.companyName = companyName;
        this.token = token;
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "phonenumber='" + phonenumber + '\'' +
                ", username='" + username + '\'' +
                ", userid='" + userid + '\'' +
                ", portrait='" + portrait + '\'' +
                ", isMember=" + isMember +
                ", companyName='" + companyName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
