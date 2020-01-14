package com.zfb.zhifabao.common.factory.model.api.assess;

import java.util.List;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class SubmitResultModel {

    /**
     * type : 2
     * paperId : 1
     * employee : {"idCard":"44058219960309665X","name":"陈杰"}
     * optionIdList : [1,7]
     */

    private String type;
    private String paperId;
    private EmployeeBean employee;
    private List<String> optionIdList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public EmployeeBean getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeBean employee) {
        this.employee = employee;
    }

    public List<String> getOptionIdList() {
        return optionIdList;
    }

    public void setOptionIdList(List<String> optionIdList) {
        this.optionIdList = optionIdList;
    }

    public static class EmployeeBean {
        /**
         * idCard : 44058219960309665X
         * name : 陈杰
         */

        private String idCard;
        private String name;

        public EmployeeBean(String idCard, String name) {
            this.idCard = idCard;
            this.name = name;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
