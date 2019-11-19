package com.zfb.zhifabao.common.factory.model.api.assess;

import java.util.List;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class SubmitResultModel {
    /**
     * ceshitype : XinLiCeShi
     * answers : [{"questionNumber":"1","answerName":"A"},{"questionNumber":"2","answerName":"B"},{"questionNumber":"1","answerName":"C"},{"questionNumber":"1","answerName":"D"}]
     */

    private String ceshitype;
    private List<AnswersBean> answers;

    @Override
    public String toString() {
        return "SubmitResultModel{" +
                "ceshitype='" + ceshitype + '\'' +
                ", answers=" + answers +
                '}';
    }

    public String getCeshitype() {
        return ceshitype;
    }

    public void setCeshitype(String ceshitype) {
        this.ceshitype = ceshitype;
    }

    public List<AnswersBean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswersBean> answers) {
        this.answers = answers;
    }

    public static class AnswersBean {
        /**
         * questionNumber : 1
         * answerName : A
         */

        private String questionNumber;
        private String answerName;

        @Override
        public String toString() {
            return "AnswersBean{" +
                    "questionNumber='" + questionNumber + '\'' +
                    ", answerName='" + answerName + '\'' +
                    '}';
        }

        public String getQuestionNumber() {
            return questionNumber;
        }

        public void setQuestionNumber(String questionNumber) {
            this.questionNumber = questionNumber;
        }

        public String getAnswerName() {
            return answerName;
        }

        public void setAnswerName(String answerName) {
            this.answerName = answerName;
        }
    }
}
