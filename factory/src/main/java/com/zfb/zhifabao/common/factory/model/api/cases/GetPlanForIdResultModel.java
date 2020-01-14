package com.zfb.zhifabao.common.factory.model.api.cases;

import android.util.Log;

public class GetPlanForIdResultModel {
    /**
     * id : 1
     * title : 《中华人民共和国劳动合同法》第三十七条
     * regulation : 劳动者提前三十日以书面形式通知用人单位，可以解除劳动合同。劳动者在试用期内提前三日通知用人单位，可以解除劳动合同。
     * caseSolved : {"id":1,"identity":0,"type":"变更劳动合同",
     * "content":"﻿代某1996年与某钢铁公司签订了为期三年的劳动合同,合同中规定其工作岗位为焊接工,工资报酬按照该公司相应岗位的工资标准支付。
     * 工作一段时间后,公司发现其焊接技术不符合客户要求的标准。为此,在公司的安排下,代某经过一段时间的培训,但其焊接的产品仍然达不到客户的要求。
     * 公司决定调离其现在的岗位,让其从事其他工作,代某没有提出异议,但当月发工资时,代某发现其工资待遇与以前相比有差异,公司告之岗位不一样,所以工资会有差异。
     * 代某提出回原工作岗位工作,遭公司拒绝,于是代某向劳动争议仲裁委员会提起申诉。\n处理结果\n仲裁机构经调查情况属实,认为劳动者不胜任工作岗位时,
     * 用人单位有权变更其工作。因此裁定代某的申诉请求不予采纳。\n",
     * "advice":"﻿劳动部办公厅《关于职工岗位变更与企业发生争议等有关问题的复函》(劳办函【1996】100号中指出,\u201c关于用人单位能否变更职工岗位问题\u201d,按照《劳动法》第十七条、第二十六条、第三十一条的规定精神,因劳动合同订立时依据的客观情况发生重大变化,致使原劳动合同无法履行而变更劳动合同,须经双方当事人协商一致,若不能达成协议,则可按法定程序解除劳动合同;因劳动者不能胜任工作而变更、调整职工工作岗位,则属于用人单位的自主权,对于因劳动者岗位变更引起的劳动争议应依据上述规定处理。\u201d\n可见,一般情况下,劳动者岗位变更应分三种情况:一是劳动者本人不能胜任正在从事的工作岗位,用人单位进行必要的调整,这属于用人单位的用工自主权,劳动者应服从单位的安排;二是劳动合同订立时所依据的客观情况发生了重大变化,致使原劳动合同无法履行而变更合同的内容,调整劳动者的工作岗位;三是劳动合同当事人双方通过协商一致,就合同内容进行变更,劳动者变换工作岗位。否则,就构成违约,并承担相应的违约责任。对此，对于劳动者解除合同的情况，有以下几点建议：\n1.劳动者提出解除劳动合同，应遵循法律规定，依程序解除(试用期内，应提前三天提出解除劳动合同；非试用期内，应提前30日书面提出解除劳动合同)；\n2.劳动者提出解除劳动合同若对用人单位造成经济损失，可要求其赔偿用人单位损失；\n3.劳动者与用人单位若签订服务期合同，劳动者解除劳动合同违反服务期约定的，可要求其按约定向用人单位支付违约金；劳动者违反劳动合同中约定的保密义务或者竞业限制，给用人单位造成损失的，可要求其赔偿损失;\n4.当用人单位违法解除劳动合同时，劳动者有权要求继续履行劳动合同。\n\n   注：\u201c劳动合同变更程序如下：(1)提出变更的要约：用人单位或劳动者提出变更劳动合同的要求，说明变更合同的理由、变更的内容以及变更的条件，请求对方在一定期限内给予答复。(2)承诺：合同另一方接到对方的变更请求后，应当及时进行答复，明确告知对方同意或是不同意变更;(3)订立书面变更协议：当事人双方就变更劳动合同的内容经过平等协商，取得一致意见后签订书面变更协议，协议载明变更的具体内容，经双方签字盖章后生效。变更后的劳动合同文本由用人单位和劳动者各执一份。\u201d\n"}
     */
    private int id;
    private String title;
    private String regulation;
    private CaseSolvedBean caseSolved;

    @Override
    public String toString() {
        return "GetPlanForIdResultModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", regulation='" + regulation + '\'' +
                ", caseSolved=" + caseSolved +
                '}';
    }

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

    public String getRegulation() {
        return regulation;
    }

    public void setRegulation(String regulation) {
        this.regulation = regulation;
    }

    public CaseSolvedBean getCaseSolved() {
        return caseSolved;
    }

    public void setCaseSolved(CaseSolvedBean caseSolved) {
        this.caseSolved = caseSolved;
    }

    public static class CaseSolvedBean {
        /**
         * id : 1
         * identity : 0
         * type : 变更劳动合同
         * content : ﻿代某1996年与某钢铁公司签订了为期三年的劳动合同,合同中规定其工作岗位为焊接工,工资报酬按照该公司相应岗位的工资标准支付。工作一段时间后,公司发现其焊接技术不符合客户要求的标准。为此,在公司的安排下,代某经过一段时间的培训,但其焊接的产品仍然达不到客户的要求。公司决定调离其现在的岗位,让其从事其他工作,代某没有提出异议,但当月发工资时,代某发现其工资待遇与以前相比有差异,公司告之岗位不一样,所以工资会有差异。代某提出回原工作岗位工作,遭公司拒绝,于是代某向劳动争议仲裁委员会提起申诉。
         处理结果
         仲裁机构经调查情况属实,认为劳动者不胜任工作岗位时,用人单位有权变更其工作。因此裁定代某的申诉请求不予采纳。
         * advice : ﻿劳动部办公厅《关于职工岗位变更与企业发生争议等有关问题的复函》(劳办函【1996】100号中指出,“关于用人单位能否变更职工岗位问题”,按照《劳动法》第十七条、第二十六条、第三十一条的规定精神,因劳动合同订立时依据的客观情况发生重大变化,致使原劳动合同无法履行而变更劳动合同,须经双方当事人协商一致,若不能达成协议,则可按法定程序解除劳动合同;因劳动者不能胜任工作而变更、调整职工工作岗位,则属于用人单位的自主权,对于因劳动者岗位变更引起的劳动争议应依据上述规定处理。”
         可见,一般情况下,劳动者岗位变更应分三种情况:一是劳动者本人不能胜任正在从事的工作岗位,用人单位进行必要的调整,这属于用人单位的用工自主权,劳动者应服从单位的安排;二是劳动合同订立时所依据的客观情况发生了重大变化,致使原劳动合同无法履行而变更合同的内容,调整劳动者的工作岗位;三是劳动合同当事人双方通过协商一致,就合同内容进行变更,劳动者变换工作岗位。否则,就构成违约,并承担相应的违约责任。对此，对于劳动者解除合同的情况，有以下几点建议：
         1.劳动者提出解除劳动合同，应遵循法律规定，依程序解除(试用期内，应提前三天提出解除劳动合同；非试用期内，应提前30日书面提出解除劳动合同)；
         2.劳动者提出解除劳动合同若对用人单位造成经济损失，可要求其赔偿用人单位损失；
         3.劳动者与用人单位若签订服务期合同，劳动者解除劳动合同违反服务期约定的，可要求其按约定向用人单位支付违约金；劳动者违反劳动合同中约定的保密义务或者竞业限制，给用人单位造成损失的，可要求其赔偿损失;
         4.当用人单位违法解除劳动合同时，劳动者有权要求继续履行劳动合同。

         注：“劳动合同变更程序如下：(1)提出变更的要约：用人单位或劳动者提出变更劳动合同的要求，说明变更合同的理由、变更的内容以及变更的条件，请求对方在一定期限内给予答复。(2)承诺：合同另一方接到对方的变更请求后，应当及时进行答复，明确告知对方同意或是不同意变更;(3)订立书面变更协议：当事人双方就变更劳动合同的内容经过平等协商，取得一致意见后签订书面变更协议，协议载明变更的具体内容，经双方签字盖章后生效。变更后的劳动合同文本由用人单位和劳动者各执一份。”
         */

        private int id;
        private int identity;
        private String type;
        private String content;
        private String advice;

        @Override
        public String toString() {
            return "CaseSolvedBean{" +
                    "id=" + id +
                    ", identity=" + identity +
                    ", type='" + type + '\'' +
                    ", content='" + content + '\'' +
                    ", advice='" + advice + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIdentity() {
            return identity;
        }

        public void setIdentity(int identity) {
            this.identity = identity;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAdvice() {
            return advice;
        }

        public void setAdvice(String advice) {
            this.advice = advice;
        }
    }
}
