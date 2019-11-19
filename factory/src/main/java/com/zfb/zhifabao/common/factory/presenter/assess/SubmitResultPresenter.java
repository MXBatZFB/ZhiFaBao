package com.zfb.zhifabao.common.factory.presenter.assess;

import com.zfb.zhifabao.common.factory.data.AssessHelper;
import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.model.api.account.FractionResultModel;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.assess.SubmitResultModel;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class SubmitResultPresenter extends BasePresenter<SubmitResultContract.View> implements SubmitResultContract.Presenter,
        DataSource.Callback<ResModel<FractionResultModel>> {


    public SubmitResultPresenter(SubmitResultContract.View mView) {
        super(mView);
    }

    @Override
    public void submitResult(SubmitResultModel model) {
        AssessHelper.submitAssessResult(model, this);
    }

    @Override
    public void onDataLoaded(ResModel<FractionResultModel> result) {
        getmView().onSubmitComplete(result.getResult());
    }

    @Override
    public void onDtaNotAvailable(String msg) {

    }
}
