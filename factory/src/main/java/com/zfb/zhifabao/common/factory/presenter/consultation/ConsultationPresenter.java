package com.zfb.zhifabao.common.factory.presenter.consultation;

import com.zfb.zhifabao.common.factory.data.ConsultationHelper;
import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class ConsultationPresenter extends BasePresenter<ConsultationContract.View>
        implements ConsultationContract.Presenter, DataSource.Callback<ResModel> {
    private int loadType;

    public ConsultationPresenter(ConsultationContract.View mView) {
        super(mView);
    }

    @Override
    public void loadLaw(String city) {
        loadType = 1;
        ConsultationHelper.loadLaw(city, this);
    }

    @Override
    public void loadCase() {
        loadType = 2;
        ConsultationHelper.loadCase(this);
    }

    @Override
    public void loadProcessDocuments() {
        loadType = 3;
        ConsultationHelper.loadProcessDocuments(this);
    }


    @Override
    public void onDataLoaded(ResModel result) {
        if (loadType == 1) {
            getmView().onLoadLawSuccess(result);
        }
        if (loadType == 2) {
            getmView().onLoadCaseSuccess(result);
        }
        if (loadType == 3) {
            getmView().onLoadProcessDocumentsSuccess(result);
        }
    }

    @Override
    public void onDtaNotAvailable(String msg) {
        getmView().showError(msg);
    }
}
