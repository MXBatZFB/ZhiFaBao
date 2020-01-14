package com.zfb.zhifabao.common;

public class Common {
    public interface Constance {
        String GRANT_TYPE = "password";
        String CLIENT_ID ="sm46401EQr5Z1pYq6";
        String CLIENT_SECRET = "4l0Wgk0hI6P18qnwDP7t24AkG1279b5L";
        String REGEX_MOBILE = "[1][3,4,5,6,7,8][0-9]{9}$";
        String API_URL = "https://www.58zfb.net/api/";
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
        int TO_LOOK_RESULT_FRAGMENT = 0X7489;
        int TO_LOOK_FRAGMENT = 0x4698;
        int TO_LOOK_CONTRACT_FILE_FREGMENT=0x9865;

        int TO_SETTLE_CASES_FRAGMENT = 0x4568;
        int ASSESS_TYPE_LAW = 0x8759;
        int ASSESS_TYPE_PSYCHOLOGY = 0X5644;
        int ASSESS_TYPE_FUNCTION = 0X5321;
        int TO_CONTRACT_FORMULATION_SET_PARTY_A_INFO = 0x9565;
        int TO_CONTRACT_FORMULATION_SET_PARTY_B_INFO = 0x8564;
        int TO_SET_CONTRACT_TIME_LIMIT = 0x1554;
        int TO_SET_WORKING_HOURS_SYSTEM = 0x3245;
        int TO_SET_LABOR_REMUNERATION = 0x5478;
        int TO_SET_WORK_TIME_AND_PLACE=0x9484;

        /**
         * 微软提供的在线预览接口Url
         */
        String BASE_TO_LOOK_URL ="https://view.officeapps.live.com/op/view.aspx?src=";

        String LOOK_CONTRACT_FILE_URL = "LOOK_CONTRACT_FILE_URL";
        //Competitive
        String EXTRA_CLAUSES_COMPETITIVE = "竞业条款";

        //training
        String EXTRA_CLAUSES_TRAINING = "培训期条款";

        //default
        String EXTRA_CLAUSES_DEFAULT = "违约责任条款";

        //Ban part-time
        String EXTRA_CLAUSES_BAN_PART_TIME   ="禁止兼职条款";

        String EXTRA_CLAUSES_CONTRACT_CHANGE  ="劳动合同变更条款";

        //Matters agreed by both parties
        String EXTRA_CLAUSES_MATTERS_AGREED_BY_BOTH_PARTIES="双方约定事项";

        //controversy
        String EXTRA_CLAUSES_CONTROVERSY="劳动争议条款";

        //Type of service
        String EXTRA_CLAUSES_TYPE_OF_SERVICE  = "送达方式条款";

        //固定期限
        String TERM_TYPE_ONE ="1";

        //无固定期限
        String TERM_TYPE_TWO ="2";

        //以完成一定工作任务为期限
        String TERM_TYPE_THREE ="3";

        //标准工时工作制
        String WORK_TIME_TYPE_ONE ="1";

        //依法实行以一个时间段为周期的综合计算工时工作制
        String WORK_TIME_TYPE_TOW ="2";

        //依法实行不定时工作制
        String WORK_TIME_TYPE_THREE ="3";

        //按月发放工资
        String SALARY_TYPE_ONE ="1";

        //记件工资
        String SALARY_TYPE_TOW ="2";

        //基本工资和绩效工资相结合的工资
        String SALARY_TYPE_THREE ="3";

        // 双方约定的其他方式
        String SALARY_TYPE_FOUR ="4";

       //
        int TO_OPEN_MEMBER_FRAGMENT = 0X85344;
        int FIND_MESSAGE = 0x467643 ;
        int TO_UP_USER_INFO_FRAGMENT = 0x64565;
        int TO_SHOW_LAW_LIST_FRAGMENT = 0x9455 ;
        int TO_LOOK_CONTENT_LAW_FRAGMENT = 0x12245;
        int TO_WORK_FRAGMENT_FLAGS =0x94561 ;
    }
}
