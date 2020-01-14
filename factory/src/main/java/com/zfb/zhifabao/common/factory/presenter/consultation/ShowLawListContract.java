package com.zfb.zhifabao.common.factory.presenter.consultation;

import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.presenter.BaseContract;

public interface ShowLawListContract  extends BaseContract {

    interface View extends BaseContract.View<Presenter> {
         void onContentSucceed(ResModel model);
    }

    interface Presenter extends BaseContract.Presenter {
        void loadLawContentById(String id);

        void loadLocalLawContentById(String id);

        void loadCaseContentById(String id);
    }

}
