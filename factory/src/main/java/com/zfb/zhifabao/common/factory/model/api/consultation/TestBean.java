package com.zfb.zhifabao.common.factory.model.api.consultation;

import com.zfb.zhifabao.common.factory.model.api.assess.SubmitResultModel;

import java.util.List;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class TestBean {


    /**
     * paperId : 1
     * type : 2
     * titleList : [{"titleId":1,"qid":1,"content":"你平时休闲经常去的地方","optionList":[{"optionId":1,"content":"郊外"},{"optionId":2,"content":"电影院"},{"optionId":3,"content":"公园"},{"optionId":4,"content":"商场"},{"optionId":5,"content":"酒吧"},{"optionId":6,"content":"练歌房"}]},{"titleId":2,"qid":2,"content":"你认为容易吸引你的人是？","optionList":[{"optionId":7,"content":"有才气的人"},{"optionId":8,"content":"依赖你的人"},{"optionId":9,"content":"优雅的人"},{"optionId":10,"content":"善良的人"},{"optionId":11,"content":"性情豪放的人"}]},{"titleId":3,"qid":3,"content":"如果你可以成为一种动物，你希望自己是哪种？","optionList":[{"optionId":12,"content":"猫"},{"optionId":13,"content":"马"},{"optionId":14,"content":"大象"},{"optionId":15,"content":"猴子"},{"optionId":16,"content":"狗"},{"optionId":17,"content":"狮子"}]},{"titleId":4,"qid":4,"content":"你更喜欢吃那种水果？","optionList":[{"optionId":18,"content":"草莓"},{"optionId":19,"content":"苹果"},{"optionId":20,"content":"西瓜"},{"optionId":21,"content":"菠萝"},{"optionId":22,"content":"橘子"}]},{"titleId":5,"qid":5,"content":"天气很热，你更愿意选择什么方式解暑？","optionList":[{"optionId":23,"content":"游泳"},{"optionId":24,"content":"喝冷饮"},{"optionId":25,"content":"开空调"}]},{"titleId":6,"qid":6,"content":"你喜欢看哪类电影、电视剧？","optionList":[{"optionId":26,"content":"悬疑推理类"},{"optionId":27,"content":"童话神话类"},{"optionId":28,"content":"自然科学类"},{"optionId":29,"content":"伦理道德类"},{"optionId":30,"content":"战争枪战类"}]},{"titleId":7,"qid":7,"content":"以下哪个是你身边必带的物品？","optionList":[{"optionId":31,"content":"打火机"},{"optionId":32,"content":"口红"},{"optionId":33,"content":"记事本"},{"optionId":34,"content":"纸巾"},{"optionId":35,"content":"手机"}]},{"titleId":8,"qid":8,"content":"你出行时喜欢坐什么交通工具？","optionList":[{"optionId":36,"content":"火车"},{"optionId":37,"content":"自行车"},{"optionId":38,"content":"汽车"},{"optionId":39,"content":"飞机"},{"optionId":40,"content":"步行"}]},{"titleId":9,"qid":9,"content":"如果必须与一个你讨厌的动物或昆虫在一起生活，你能容忍哪一个？","optionList":[{"optionId":41,"content":"蛇"},{"optionId":42,"content":"猪"},{"optionId":43,"content":"老鼠"},{"optionId":44,"content":"苍蝇"}]},{"titleId":10,"qid":10,"content":"以下颜色你更喜欢哪种？","optionList":[{"optionId":45,"content":"紫"},{"optionId":46,"content":"黑"},{"optionId":47,"content":"蓝"},{"optionId":48,"content":"白"},{"optionId":49,"content":"黄"},{"optionId":50,"content":"红"}]},{"titleId":11,"qid":11,"content":"下列运动中挑选一个你最喜欢的(不一定擅长)？","optionList":[{"optionId":51,"content":"瑜珈"},{"optionId":52,"content":"自行车"},{"optionId":53,"content":"乒乓球"},{"optionId":54,"content":"拳击"},{"optionId":55,"content":"足球"},{"optionId":56,"content":"蹦极"}]},{"titleId":12,"qid":12,"content":"你更喜欢以下哪种天气现象？","optionList":[{"optionId":57,"content":"雪"},{"optionId":58,"content":"风"},{"optionId":59,"content":"雨"},{"optionId":60,"content":"雾"},{"optionId":61,"content":"雷电"}]},{"titleId":13,"qid":13,"content":"你希望自己的窗口在一座30层大楼的第几层？","optionList":[{"optionId":62,"content":"七层"},{"optionId":63,"content":"一层"},{"optionId":64,"content":"二十三层"},{"optionId":65,"content":"十八层"},{"optionId":66,"content":"三十层"}]},{"titleId":14,"qid":14,"content":"你认为自己更喜欢在以下哪一个城市中生活？","optionList":[{"optionId":67,"content":"丽江"},{"optionId":68,"content":"拉萨"},{"optionId":69,"content":"昆明"},{"optionId":70,"content":"西安"},{"optionId":71,"content":"杭州"},{"optionId":72,"content":"北京"}]},{"titleId":15,"qid":15,"content":"如果你拥有一座别墅，你认为它应当建立在哪里？","optionList":[{"optionId":73,"content":"湖边"},{"optionId":74,"content":"草原"},{"optionId":75,"content":"海边"},{"optionId":76,"content":"森林"},{"optionId":77,"content":"城中区"}]}]
     */

    private String paperId;
    private String type;
    private List<TitleListBean> titleList;
    private SubmitResultModel.EmployeeBean employeeBean;

    public SubmitResultModel.EmployeeBean getEmployeeBean() {
        return employeeBean;
    }

    public void setEmployeeBean(SubmitResultModel.EmployeeBean employeeBean) {
        this.employeeBean = employeeBean;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TitleListBean> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<TitleListBean> titleList) {
        this.titleList = titleList;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "paperId='" + paperId + '\'' +
                ", type='" + type + '\'' +
                ", titleList=" + titleList +
                ", employeeBean=" + employeeBean +
                '}';
    }

    public static class TitleListBean {
        /**
         * titleId : 1
         * qid : 1
         * content : 你平时休闲经常去的地方
         * optionList : [{"optionId":1,"content":"郊外"},{"optionId":2,"content":"电影院"},{"optionId":3,"content":"公园"},{"optionId":4,"content":"商场"},{"optionId":5,"content":"酒吧"},{"optionId":6,"content":"练歌房"}]
         */

        private int titleId;
        private int qid;
        private String content;
        private List<OptionListBean> optionList;


        @Override
        public String toString() {
            return "TitleListBean{" +
                    "titleId=" + titleId +
                    ", qid=" + qid +
                    ", content='" + content + '\'' +
                    ", optionList=" + optionList +
                    '}';
        }

        public int getTitleId() {
            return titleId;
        }

        public void setTitleId(int titleId) {
            this.titleId = titleId;
        }

        public int getQid() {
            return qid;
        }

        public void setQid(int qid) {
            this.qid = qid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<OptionListBean> getOptionList() {
            return optionList;
        }

        public void setOptionList(List<OptionListBean> optionList) {
            this.optionList = optionList;
        }

        public static class OptionListBean {
            /**
             * optionId : 1
             * content : 郊外
             */
            private String answer;
            private String optionId;
            private String content;
            private boolean  isSelected;


            @Override
            public String toString() {
                return "OptionListBean{" +
                        "answer='" + answer + '\'' +
                        ", optionId='" + optionId + '\'' +
                        ", content='" + content + '\'' +
                        ", isSelected=" + isSelected +
                        '}';
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public String getOptionId() {
                return optionId;
            }

            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

            public void setOptionId(String optionId) {
                this.optionId = optionId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }


        }
    }
}
