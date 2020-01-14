package com.zfb.zhifabao.common.factory.presenter.law;

import com.zfb.zhifabao.common.factory.data.ConsultationHelper;
import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

public class ConsultationLaborLawPresenter extends BasePresenter<ConsultationLaborLawContract.View> implements ConsultationLaborLawContract.Presenter, DataSource.Callback<ResModel> {
    public ConsultationLaborLawPresenter(ConsultationLaborLawContract.View mView) {
        super(mView);
    }

    @Override
    public void onDataLoaded(ResModel result) {
             getmView().onSucceed(result);
             getmView().showError(result.getMsg());
    }

    @Override
    public void onDtaNotAvailable(String msg) {
            getmView().showError(msg);
    }

    @Override
    public void loadLaborLawTypes() {

    }

    @Override
    public void getLocalLawByCityId(String id) {
       
    }

    @Override
    public void getCaseTypes() {

    }
}
