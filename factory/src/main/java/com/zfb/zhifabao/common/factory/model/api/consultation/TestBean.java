package com.zfb.zhifabao.common.factory.model.api.consultation;

import java.util.List;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class TestBean {

    private List<QuestionsBean> questions;

    public List<QuestionsBean> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsBean> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "questions=" + questions +
                '}';
    }

    public static class QuestionsBean {
        /**
         * question : {"
         * questionNumber":"1",
         * "questionBody":"你平时休闲经常去的地方?",
         * "answers":[
         * {"answerName":"A","answerContent":"郊外"},
         * {"answerName":"B","answerContent":"电影院"},
         * {"answerName":"C","answerContent":"公园"},
         * {"answerName":"D","answerContent":"商场"},
         * {"answerName":"E","answerContent":"酒吧"},
         * {"answerName":"F","answerContent":"练歌房"}
         * ]}
         */

        private QuestionBean question;

        public QuestionBean getQuestion() {
            return question;
        }

        public void setQuestion(QuestionBean question) {
            this.question = question;
        }

        public static class QuestionBean {
            /**
             * questionNumber : 1
             * questionBody : 你平时休闲经常去的地方?
             * answers : [
             * {"answerName":"A","answerContent":"郊外"},
             * {"answerName":"B","answerContent":"电影院"},
             * {"answerName":"C","answerContent":"公园"},
             * {"answerName":"D","answerContent":"商场"},
             * {"answerName":"E","answerContent":"酒吧"},
             * {"answerName":"F","answerContent":"练歌房"}]
             */
            private String questionNumber;
            private String questionBody;
            private List<AnswersBean> answers;

            @Override
            public String toString() {
                return "QuestionBean{" +
                        "questionNumber='" + questionNumber + '\'' +
                        ", questionBody='" + questionBody + '\'' +
                        ", answers=" + answers +
                        '}';
            }

            public String getQuestionNumber() {
                return questionNumber;
            }

            public void setQuestionNumber(String questionNumber) {
                this.questionNumber = questionNumber;
            }

            public String getQuestionBody() {
                return questionBody;
            }

            public void setQuestionBody(String questionBody) {
                this.questionBody = questionBody;
            }

            public List<AnswersBean> getAnswers() {
                return answers;
            }

            public void setAnswers(List<AnswersBean> answers) {
                this.answers = answers;
            }

            public static class AnswersBean {
                /**
                 * answerName : A
                 * answerContent : 郊外
                 */
                private boolean isSelected;
                private String answerName;
                private String answerContent;

                @Override
                public String toString() {
                    return "AnswersBean{" +
                            "isSelected=" + isSelected +
                            ", answerName='" + answerName + '\'' +
                            ", answerContent='" + answerContent + '\'' +
                            '}';
                }

                public boolean isSelected() {
                    return isSelected;
                }

                public void setSelected(boolean selected) {
                    isSelected = selected;
                }

                public String getAnswerName() {
                    return answerName;
                }

                public void setAnswerName(String answerName) {
                    this.answerName = answerName;
                }

                public String getAnswerContent() {
                    return answerContent;
                }

                public void setAnswerContent(String answerContent) {
                    this.answerContent = answerContent;
                }
            }
        }
    }
}
