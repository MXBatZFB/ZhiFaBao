package com.zfb.zhifabao.common.factory.presenter.assess;

import com.zfb.zhifabao.common.factory.model.api.account.FractionResultModel;
import com.zfb.zhifabao.common.factory.model.api.assess.SubmitResultModel;
import com.zfb.zhifabao.common.factory.presenter.BaseContract;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public interface SubmitResultContract {

    interface View extends BaseContract.View<SubmitResultContract.Presenter> {
        //发起deng
        void onSubmitComplete(FractionResultModel model);
    }

    interface Presenter extends BaseContract.Presenter {

        //普法测试
        void submitResult(SubmitResultModel model);
    }
}
