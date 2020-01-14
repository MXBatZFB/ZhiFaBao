package com.zfb.zhifabao.common.factory.presenter.cases;

import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.data.SettleCasesHelper;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetPlanForIdResultModel;
import com.zfb.zhifabao.common.factory.presenter.BaseContract;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

public class ShowCasePresenter extends BasePresenter<ShowCaseContract.View> implements ShowCaseContract.Presenter , DataSource.Callback<ResModel> {
    public ShowCasePresenter(ShowCaseContract.View mView) {
        super(mView);
    }


    @Override
    public void loadTextTemPlan(String id) {
        SettleCasesHelper.loadPlan(id ,this);
    }


    @Override
    public void onDataLoaded(ResModel result) {
        getmView().onLoadPlanPlanSuccess((GetPlanForIdResultModel) result.getData());
    }

    @Override
    public void onDtaNotAvailable(String msg) {
           getmView().showError(msg);
    }
}
