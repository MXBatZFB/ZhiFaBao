package com.zfb.zhifabao.common.factory.presenter.contract;

import com.zfb.zhifabao.common.factory.data.ContractHelper;
import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.contract.ContractResultMode;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class ReviewPresenter extends BasePresenter<ReviewContract.View> implements ReviewContract.Presenter,
        DataSource.Callback<ResModel<ContractResultMode>> {
    public ReviewPresenter(ReviewContract.View mView) {
        super(mView);
    }

    @Override
    public void getContractType() {
        ContractHelper.getContractType(this);
    }

    @Override
    public void onDataLoaded(ResModel<ContractResultMode> result) {
        getmView().onGetTypeSuccess(result.getResult());
    }

    @Override
    public void onDtaNotAvailable(String msg) {
        getmView().showError(msg);
    }
}
