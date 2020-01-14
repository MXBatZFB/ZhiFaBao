package com.zfb.zhifabao.common.factory.model.api.contract;

import com.zfb.zhifabao.common.Common;

import java.util.List;
import java.util.Map;

public class CustomContractModel {
    /**
     * necessaryClauses : {"partyAInfo":{"companyName":"甲方（用人单位）","unicode":"统一社会信用代码","legalPersonName":"法定代表人","registration":"注 册 地","placeOfBusiness":"经 营 地","contactNumber":"联系电话"},"partyBInfo":{"name":"乙方（劳动者）","idNumber":"证件号","idType":"其他有效证件名称","householdRegister":"户籍地址","residentialAddress":"经常居住地","contactNumber":"联系电话"},"termType":"2","startDate":"2018-09-01","endDate":"2018-09-02","endDate2":"2018-09-03","tasks":"工作任务","post":"岗位","duties":"职责","workPlace":"工作地点","workTimeType":"3","cycle":"工时周期","salaryType":"4","salaryDate":"11","salary":"工资","salaryMethod":"计算工资方法","probationarySalary1":"试用期工资计发标准1","probationarySalary2":"试用期工资计发标准2"}
     * extraClausesList : [{"type":"竞业条款","values":{}},{"type":"竞业条款","values":{}},{"type":"培训期条款","values":{}},{"type":"违约责任条款","values":{"$(method)":"赔偿金数额的确定办法"}},{"type":"禁止兼职条款","values":{}},{"type":"劳动合同变更条款","values":{}},{"type":"双方约定事项","values":{"$(matters)":"双方约定事项"}},{"type":"劳动争议条款","values":{}},{"type":"送达方式条款","values":{}}]
     */
    private NecessaryClausesBean necessaryClauses;
    private List<ExtraClausesListBean> extraClausesList;
    private static CustomContractModel customContractModel = null;

    private CustomContractModel() {
    }

    public static synchronized CustomContractModel getInstance(){
        if (customContractModel==null){
            customContractModel = new CustomContractModel();
        }
        return  customContractModel;
    }

    public NecessaryClausesBean getNecessaryClauses() {
        return necessaryClauses;
    }

    public void setNecessaryClauses(NecessaryClausesBean necessaryClauses) {
        this.necessaryClauses = necessaryClauses;
    }

    public List<ExtraClausesListBean> getExtraClausesList() {
        return extraClausesList;
    }

    public void setExtraClausesList(List<ExtraClausesListBean> extraClausesList) {
        this.extraClausesList = extraClausesList;
    }

    @Override
    public String toString() {
        return "CustomContractModel{" +
                "necessaryClauses=" + necessaryClauses +
                ", extraClausesList=" + extraClausesList +
                '}';
    }

    public static class NecessaryClausesBean {
        private PartyAInfoBean partyAInfo;//甲方信息
        private PartyBInfoBean partyBInfo;//乙方信息

        //以下是工作内容和地点的参数
        private String post;//岗位
        private String duties;//职责
        private String workPlace;//工作地点

        //合同期限类型：1.固定期限 2.无固定期限 3.以完成一定工作任务为期限
        private String termType;
        private String tasks;//workTimeType为3需要提供的参数
        private String startDate;//合同开始日期
        private String endDate;//合同结束日期
        private String endProbationaryDate;//试用期结束日期

        //工时制度类型：
        // 1.标准工时工作制
        // 2.依法实行以一个时间段为周期的综合计算工时工作制
        // 3.依法实行不定时工作制
        private String workTimeType;
        private String customTime;
        private String cycle;//只有在workTimeType为2时需要提供

        //报酬方式：
        // 1.按月发放工资
        // 2.记件工资
        // 3.基本工资和绩效工资相结合的工资
        // 4.双方约定的其他方式
        private String salaryType;
        private String salary;//salaryType为1，2，3时，需要提供的参数为1时表示每月的工作金额；为2时表示每一件的报酬，3时是基本工资
        private String salaryDate;//工资发放日，为每月固定的哪一天
        private String salaryMethod;//salaryType为3，4，需要提供的参数
        private String probationarySalary;//试用期工资

        @Override
        public String toString() {
            return "NecessaryClausesBean{" +
                    "partyAInfo=" + partyAInfo +
                    ", partyBInfo=" + partyBInfo +
                    ", post='" + post + '\'' +
                    ", duties='" + duties + '\'' +
                    ", workPlace='" + workPlace + '\'' +
                    ", termType='" + termType + '\'' +
                    ", tasks='" + tasks + '\'' +
                    ", startDate='" + startDate + '\'' +
                    ", endDate='" + endDate + '\'' +
                    ", endProbationaryDate='" + endProbationaryDate + '\'' +
                    ", workTimeType='" + workTimeType + '\'' +
                    ", cycle='" + cycle + '\'' +
                    ", salaryType='" + salaryType + '\'' +
                    ", salary='" + salary + '\'' +
                    ", salaryDate='" + salaryDate + '\'' +
                    ", salaryMethod='" + salaryMethod + '\'' +
                    ", probationarySalary='" + probationarySalary + '\'' +
                    '}';
        }

        public String getCustomTime() {
            return customTime;
        }

        public void setCustomTime(String customTime) {
            this.customTime = customTime;
        }

        public PartyAInfoBean getPartyAInfo() {
            return partyAInfo;
        }

        public void setPartyAInfo(PartyAInfoBean partyAInfo) {
            this.partyAInfo = partyAInfo;
        }

        public PartyBInfoBean getPartyBInfo() {
            return partyBInfo;
        }

        public void setPartyBInfo(PartyBInfoBean partyBInfo) {
            this.partyBInfo = partyBInfo;
        }

        public String getTermType() {
            return termType;
        }

        public void setTermType(String termType) {
            this.termType = termType;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getEndProbationaryDate() {
            return endProbationaryDate;
        }

        public void setEndProbationaryDate(String endProbationaryDate) {
            this.endProbationaryDate = endProbationaryDate;
        }

        public String getTasks() {
            return tasks;
        }

        public void setTasks(String tasks) {
            this.tasks = tasks;
        }

        public String getPost() {
            return post;
        }

        public void setPost(String post) {
            this.post = post;
        }

        public String getDuties() {
            return duties;
        }

        public void setDuties(String duties) {
            this.duties = duties;
        }

        public String getWorkPlace() {
            return workPlace;
        }

        public void setWorkPlace(String workPlace) {
            this.workPlace = workPlace;
        }

        public String getWorkTimeType() {
            return workTimeType;
        }

        public void setWorkTimeType(String workTimeType) {
            this.workTimeType = workTimeType;
        }

        public String getCycle() {
            return cycle;
        }

        public void setCycle(String cycle) {
            this.cycle = cycle;
        }

        public String getSalaryType() {
            return salaryType;
        }

        public void setSalaryType(String salaryType) {
            this.salaryType = salaryType;
        }

        public String getSalaryDate() {
            return salaryDate;
        }

        public void setSalaryDate(String salaryDate) {
            this.salaryDate = salaryDate;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getSalaryMethod() {
            return salaryMethod;
        }

        public void setSalaryMethod(String salaryMethod) {
            this.salaryMethod = salaryMethod;
        }

        public String getProbationarySalary() {
            return probationarySalary;
        }

        public void setProbationarySalary(String probationarySalary) {
            this.probationarySalary = probationarySalary;
        }

        public static class PartyAInfoBean {
            /**
             * companyName : 甲方（用人单位）
             * unicode : 统一社会信用代码
             * legalPersonName : 法定代表人
             * registration : 注 册 地
             * placeOfBusiness : 经 营 地
             * contactNumber : 联系电话
             */
            private String companyName;
            private String unicode;
            private String legalPersonName;
            private String registration;
            private String placeOfBusiness;
            private String contactNumber;

            @Override
            public String toString() {
                return "PartyAInfoBean{" +
                        "companyName='" + companyName + '\'' +
                        ", unicode='" + unicode + '\'' +
                        ", legalPersonName='" + legalPersonName + '\'' +
                        ", registration='" + registration + '\'' +
                        ", placeOfBusiness='" + placeOfBusiness + '\'' +
                        ", contactNumber='" + contactNumber + '\'' +
                        '}';
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getUnicode() {
                return unicode;
            }

            public void setUnicode(String unicode) {
                this.unicode = unicode;
            }

            public String getLegalPersonName() {
                return legalPersonName;
            }

            public void setLegalPersonName(String legalPersonName) {
                this.legalPersonName = legalPersonName;
            }

            public String getRegistration() {
                return registration;
            }

            public void setRegistration(String registration) {
                this.registration = registration;
            }

            public String getPlaceOfBusiness() {
                return placeOfBusiness;
            }

            public void setPlaceOfBusiness(String placeOfBusiness) {
                this.placeOfBusiness = placeOfBusiness;
            }

            public String getContactNumber() {
                return contactNumber;
            }

            public void setContactNumber(String contactNumber) {
                this.contactNumber = contactNumber;
            }
        }

        public static class PartyBInfoBean {
            /**
             * name : 乙方（劳动者）
             * idNumber : 证件号
             * householdRegister : 户籍地址
             * residentialAddress : 经常居住地
             * contactNumber : 联系电话
             */

            private String name;
            private String idNumber;
            private String householdRegister;
            private String residentialAddress;
            private String contactNumber;

            @Override
            public String toString() {
                return "PartyBInfoBean{" +
                        "name='" + name + '\'' +
                        ", idNumber='" + idNumber + '\'' +
                        ", householdRegister='" + householdRegister + '\'' +
                        ", residentialAddress='" + residentialAddress + '\'' +
                        ", contactNumber='" + contactNumber + '\'' +
                        '}';
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIdNumber() {
                return idNumber;
            }

            public void setIdNumber(String idNumber) {
                this.idNumber = idNumber;
            }

            public String getHouseholdRegister() {
                return householdRegister;
            }

            public void setHouseholdRegister(String householdRegister) {
                this.householdRegister = householdRegister;
            }

            public String getResidentialAddress() {
                return residentialAddress;
            }

            public void setResidentialAddress(String residentialAddress) {
                this.residentialAddress = residentialAddress;
            }

            public String getContactNumber() {
                return contactNumber;
            }

            public void setContactNumber(String contactNumber) {
                this.contactNumber = contactNumber;
            }
        }
    }

    public class ExtraClausesListBean {
        private String type;
        private Map<String,String> values;

        public ExtraClausesListBean(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "ExtraClausesListBean{" +
                    "type='" + type + '\'' +
                    ", values=" + values +
                    '}';
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
