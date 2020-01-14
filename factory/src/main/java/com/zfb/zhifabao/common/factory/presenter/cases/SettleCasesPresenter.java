package com.zfb.zhifabao.common.factory.presenter.cases;

import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.data.SettleCasesHelper;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseTypeResultModel;
import com.zfb.zhifabao.common.factory.model.api.cases.ResolveLaborCasesModel;
import com.zfb.zhifabao.common.factory.model.api.cases.ResolveLaborCasesResultModel;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class SettleCasesPresenter extends BasePresenter<SettleCasesContract.View> implements
        SettleCasesContract.Presenter,
        DataSource.Callback<ResModel> {
    public SettleCasesPresenter(SettleCasesContract.View mView) {
        super(mView);
    }

    @Override
    public void loadCaseType(String identity) {
        SettleCasesHelper.loadCaseType(identity,this);
    }

    @Override
    public void doCase(ResolveLaborCasesModel model) {
        SettleCasesHelper.resolveLaborCase(model,this);
    }

    @Override
    public void onDataLoaded(ResModel result) {
        if (result.getData() instanceof GetCaseTypeResultModel){
            getmView().onLoadCaseTypeSuccess((GetCaseTypeResultModel) result.getData());
        }else {
            getmView().onResolveLaborCasesComplete((ResolveLaborCasesResultModel) result.getData());
        }

    }

    @Override
    public void onDtaNotAvailable(String msg) {
        getmView().showError(msg);
    }
}
