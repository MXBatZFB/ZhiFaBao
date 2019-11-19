package com.zfb.zhifabao.common.factory.presenter.assess;

import com.zfb.zhifabao.common.factory.model.api.consultation.TestBean;
import com.zfb.zhifabao.common.factory.presenter.BaseContract;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public interface AssessContract {
    interface View extends BaseContract.View<Presenter> {
        //普法测试
        void doAssess(TestBean testBean);
    }

    interface Presenter extends BaseContract.Presenter {
        //发起deng
        void getAssessQuestion(String testType);
    }


}
