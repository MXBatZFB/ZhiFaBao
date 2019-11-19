package com.zfb.zhifabao.common.factory.presenter.look;

import android.util.Log;

import com.zfb.zhifabao.common.factory.data.ConsultationHelper;
import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.data.SettleCasesHelper;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseSuggestionModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseSuggestionResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLookLawResultModel;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class LookPresenter extends BasePresenter<LookContract.View> implements LookContract.Presenter,
        DataSource.Callback<ResModel> {
    private String doType;

    public LookPresenter(LookContract.View mView) {
        super(mView);
    }

    @Override
    public void lookLaw(String lawName) {
        doType = "content";
        ConsultationHelper.loadLookFile(lawName, doType, this);
    }

    @Override
    public void downLaw(String lawName) {
        doType = "download";
    }

    @Override
    public void lookCaseSuggestion(GetCaseSuggestionModel model) {
        doType = "loadSuggestion";
        SettleCasesHelper.loadCaseSuggestion(model, this);
    }

    @Override
    public void onDataLoaded(ResModel result) {
        Log.e("delong", "doType>>>>>>>>>>>>>>>>>>>>>>>>>>" + doType);
        if (doType.equals("content")) {
            GetLookLawResultModel model = (GetLookLawResultModel) result.getResult();
            getmView().onLookSuccess(model);
        } else if (doType.equals("loadSuggestion")) {
            GetCaseSuggestionResultModel model = (GetCaseSuggestionResultModel) result.getResult();
            getmView().onLookSuggestionSuccess(model);
        }
    }

    @Override
    public void onDtaNotAvailable(String msg) {

    }
}
