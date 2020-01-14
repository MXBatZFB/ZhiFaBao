package com.zfb.zhifabao.common.factory.model.api.member;

public class OrderResultModel {
    /**
     * appid : wx8b79994b9e69ab4c
     * noncestr : uRY8bzIL15NbsWxP
     * package : Sign=WXPay
     * partnerid : 1568144531
     * prepayid : wx31114204140707ced8b100c21635529700
     * sign : 3746B5C9F3EA902DD1F666839B600DF8
     * timestamp : 1577763696
     */

    private String appid;
    private String noncestr;
    private String packageX;
    private String partnerid;
    private String prepayid;
    private String sign;
    private String timestamp;


    @Override
    public String toString() {
        return "OrderResultModel{" +
                "appid='" + appid + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", packageX='" + packageX + '\'' +
                ", partnerid='" + partnerid + '\'' +
                ", prepayid='" + prepayid + '\'' +
                ", sign='" + sign + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
