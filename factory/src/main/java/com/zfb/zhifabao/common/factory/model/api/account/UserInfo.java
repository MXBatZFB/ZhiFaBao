package com.zfb.zhifabao.common.factory.model.api.account;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 * 用户信息的封装类
 */
public class UserInfo {
    //用户唯一登录的账号（即注册的手机号码）
    private String phone;
    //用户昵称
    private String nickname;
    //用户的唯一识别Id
    private String userId;
    //头像链接
    private String portrait;
    //是否是会员//0:非会员 1:会员
    private int memberType;
    //登陆返回的token
    private String token;


    public UserInfo() {

    }

    public UserInfo(String phone, String nickname, String userId, String portrait, int memberType, String token) {
        this.phone = phone;
        this.nickname = nickname;
        this.userId = userId;
        this.portrait = portrait;
        this.memberType = memberType;
        this.token = token;
    }

    public UserInfo(String userName, String portrait, int memberType) {
        this.nickname = userName;
        this.portrait = portrait;
        this.memberType = memberType;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "phone='" + phone + '\'' +
                ", userName='" + nickname + '\'' +
                ", userId='" + userId + '\'' +
                ", portrait='" + portrait + '\'' +
                ", memberType=" + memberType +
                ", token='" + token + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getMemberType() {
        return memberType;
    }

    public void setMemberType(int memberType) {
        this.memberType = memberType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
