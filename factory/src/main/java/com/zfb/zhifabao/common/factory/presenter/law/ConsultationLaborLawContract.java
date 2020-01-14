package com.zfb.zhifabao.common.factory.presenter.law;

import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.presenter.BaseContract;

public interface ConsultationLaborLawContract extends BaseContract {
    interface View extends BaseContract.View<Presenter> {
        void onSucceed(ResModel model);
    }

    interface Presenter extends BaseContract.Presenter{
        void loadLaborLawTypes();

        void getLocalLawByCityId(String id);

        void getCaseTypes();
    }

}
