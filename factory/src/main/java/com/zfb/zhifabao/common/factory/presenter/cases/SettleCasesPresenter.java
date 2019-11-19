package com.zfb.zhifabao.common.factory.presenter.cases;

import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.data.SettleCasesHelper;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseTypeResultModel;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class SettleCasesPresenter extends BasePresenter<SettleCasesContract.View> implements
        SettleCasesContract.Presenter,
        DataSource.Callback<GetCaseTypeResultModel> {
    public SettleCasesPresenter(SettleCasesContract.View mView) {
        super(mView);
    }

    @Override
    public void loadCaseType() {
        SettleCasesHelper.loadCaseType(this);
    }

    @Override
    public void onDataLoaded(GetCaseTypeResultModel result) {
        getmView().onLoadCaseTypeSuccess(result);
    }

    @Override
    public void onDtaNotAvailable(String msg) {
        getmView().showError(msg);
    }
}
