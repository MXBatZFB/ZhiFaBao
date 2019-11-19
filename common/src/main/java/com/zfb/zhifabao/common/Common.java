package com.zfb.zhifabao.common;

public class Common {
    public interface Constance {
        String GRANT_TYPE = "password";
        String CLIENT_ID ="sm46401EQr5Z1pYq6";
        String CLIENT_SECRET = "4l0Wgk0hI6P18qnwDP7t24AkG1279b5L";
        String REGEX_MOBILE = "[1][3,4,5,6,7,8][0-9]{9}$";
        String API_URL = "http://119.23.205.68:8080/";
        String CASE_LAW = "CASE_LAW";
        String LOCAL_LAW = "LOCAL_LAW";
        String CASE_SUGGESTION = "CASE_SUGGESTION";
        String RELATED_CASE = "RELATED_CASE";
        String PROCESS_DOCUMENTS = "PROCESS_DOCUMENTS";
        int REQUEST_PORTRAIT = 0X100;
        int TO_CONTRACT_REVIEW_FRAGMENT = 0x9841;
        int TO_CONTRACT_FORMULATION_FRAGMENT = 0x4252;
        int TO_MY_CONTRACT_FRAGMENT = 0x4242;
        int TO_LOGIN_FLAGS =0x4646;
        int TO_REGISTER_FLAGS =0x5666;
        int TO_MSG_LOGIN_FLAGS =0x8784;
        int TO_NEXT_MSG_LOGIN = 0X6456;

        int TO_CONSULTATION_FRAGMENT = 0x46468;
        int TO_SHOW_LAW_FRAGMENT = 0x8478;
        int TO_LOOK_LAW_FRAGMENT = 0X7489;

        int TO_SETTLE_CASES_FRAGMENT = 0x4568;
        int ASSESS_TYPE_LAW = 0x8759;
        int ASSESS_TYPE_PSYCHOLOGY = 0X5644;
        int ASSESS_TYPE_FUNCTION = 0X5321;

    }
}
