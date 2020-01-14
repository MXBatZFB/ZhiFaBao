package com.zfb.zhifabao.common.factory.presenter.contract;

import com.zfb.zhifabao.common.factory.data.ContractHelper;
import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.model.db.ContractFileInfo;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

import java.util.List;

public class MyContractPresenter extends BasePresenter<MyContractContract.View> implements MyContractContract.Presenter, DataSource.Callback<List<ContractFileInfo>> {

    public MyContractPresenter(MyContractContract.View mView) {
        super(mView);
    }

    @Override
    public void onDataLoaded(List<ContractFileInfo> result) {
            getmView().onCheckLocalContractSuccess(result);
    }

    @Override
    public void onDtaNotAvailable(String msg) {

    }

    @Override
    public void checkLocalContract() {
       ContractHelper.getLocalContractFile(this);
    }
}
