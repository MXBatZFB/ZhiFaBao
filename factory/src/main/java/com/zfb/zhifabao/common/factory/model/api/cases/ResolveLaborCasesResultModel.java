package com.zfb.zhifabao.common.factory.model.api.cases;


import java.util.List;

/**
 *
 * 该类封装了解决案件返回的数据结果，data是数据解决方案的list集合,
 */
public class ResolveLaborCasesResultModel {

    /**
     * caseSolutionList : [{"id":1,"title":"《中华人民共和国劳动合同法》第三十七条"},{"id":2,"title":"《中华人民共和国劳动合同法》第三十八条"},{"id":3,"title":"《中华人民共和国劳动合同法》第四十七条"},{"id":4,"title":"《中华人民共和国劳动合同法》第四十八条"}]
     * process : 0
     * identity : 0
     */

    private int process;
    private int identity;
    private List<CaseSolutionListBean> caseSolutionList;

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public List<CaseSolutionListBean> getCaseSolutionList() {
        return caseSolutionList;
    }

    public void setCaseSolutionList(List<CaseSolutionListBean> caseSolutionList) {
        this.caseSolutionList = caseSolutionList;
    }

    @Override
    public String toString() {
        return "ResolveLaborCasesResultModel{" +
                "process=" + process +
                ", identity=" + identity +
                ", caseSolutionList=" + caseSolutionList +
                '}';
    }

    public static class CaseSolutionListBean {
        /**
         * id : 1
         * title : 《中华人民共和国劳动合同法》第三十七条
         */

        private int id;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "CaseSolutionListBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
