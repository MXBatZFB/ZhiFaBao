package com.zfb.zhifabao.common.factory.presenter.consultation;

import com.zfb.zhifabao.common.factory.data.ConsultationHelper;
import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLabourLawListWithRegionIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLabourLawListWithTypeResultModel;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

public class ShowLawListPresenter extends BasePresenter<ShowLawListContract.View>
                                         implements ShowLawListContract.Presenter , DataSource.Callback<ResModel> {


    public ShowLawListPresenter(ShowLawListContract.View mView) {
        super(mView);
    }


    @Override
    public void loadLawContentById(String id) {
        ConsultationHelper.getContentWithLawById(id,this);
    }

    @Override
    public void loadLocalLawContentById(String id) {
        ConsultationHelper.getLocalContentWithLawById(id,this);
    }

    @Override
    public void loadCaseContentById(String id) {
        ConsultationHelper.loadCaseContentById(id,this);
    }

    @Override
    public void onDataLoaded(ResModel result) {
         getmView().onContentSucceed(result);
    }

    @Override
    public void onDtaNotAvailable(String msg) {
           getmView().showError(msg);
    }


}
