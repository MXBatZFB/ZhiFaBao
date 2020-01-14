package com.zfb.zhifabao.common.factory.model.api.consultation;

import java.util.List;

public class GetCaseListWithTypeResultModel {


    private List<CaseListBean> caseList;

    public List<CaseListBean> getCaseList() {
        return caseList;
    }

    public void setCaseList(List<CaseListBean> caseList) {
        this.caseList = caseList;
    }

    public static class CaseListBean {
        /**
         * id : 1
         * content : ﻿陈某于 2012 年 9 月 1 日入职餐饮公司，担任人事经理一职。2013年 5 月，陈某以个人发展原因，提出与餐饮公司解除劳动关系，餐饮公司批准了陈某的辞职申请，为其结清了工资并办理了离职手续。但陈某离职后不久即要求餐饮公司支付未签订书面劳动合同的二倍工资差额90000 元。案件审理过程中，餐饮公司表示陈某全权负责公司所有员工的劳动合同签订工作。即便陈某未签订劳动合同，原因亦在于其本人意图以此牟利。餐饮公司向法院提交了陈某在入职时签收的岗位职责确认书。陈某认可上述证据材料的真实性，也认可其工作职责包括劳动合同的签订事宜，但坚称是餐饮公司不与其签订书面劳动合同。法院经过审理后认为，根据案件证据情况，需审查劳动合同未能签订的过错在于哪一方。现陈某未能举证证明其曾向公司提出签订劳动合同而公司予以拒绝，而作为公司却举证证明是因陈某个人原因才未签订合同，故陈某应当承担举证不能的法律后果。综合上述理由，法院判决驳回了陈某的全部诉讼请求。
         * review : ﻿生活中因未与劳动者签订劳动合同而产生的劳动纠纷比比皆是，对于因劳动者拒绝签订劳动合同的，事后进行索赔双倍工资的情形，是公司企业需要谨慎注意的。根据《中华人民共和国劳动合同法》第八十二条的规定，用人单位自用工之日起超过一个月不满一年未与劳动者订立书面劳动合同的，应当向劳动者每月支付二倍的工资。第八十七条，用人单位违反本法规定解除或者终止劳动合同的，应当依照《劳动合同法》第47条规定的经济补偿标准的二倍向劳动者支付赔偿金。可以看出两倍工资是对用人单位违反《劳动合同法》规定，在法定期限内不与劳动者签订书面劳动合同所承担的惩罚性赔偿责任。签订书面劳动合同是为了保护劳动者的合法权益，防止用人单位肆意侵害劳动者的权益而无约束，如因劳动者的原因而导致没有签订劳动合同的，那么用人单位在未签劳动合同上并无过错，所以自然就无需承担未签劳动合同的双倍工资。最重要的是，用人单位要举证证明是劳动者的原因导致劳动合同未签订。保存证据是用人单位解决此类纠纷的关键。
         * regulation : ﻿《中华人民共和国劳动合同法》第八十二条，第八十七条；《劳动合同法》第47条

         */

        private String id;
        private String content;
        private String review;
        private String regulation;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getReview() {
            return review;
        }

        public void setReview(String review) {
            this.review = review;
        }

        public String getRegulation() {
            return regulation;
        }

        public void setRegulation(String regulation) {
            this.regulation = regulation;
        }
    }
}
