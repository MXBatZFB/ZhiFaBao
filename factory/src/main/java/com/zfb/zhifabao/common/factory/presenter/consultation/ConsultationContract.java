package com.zfb.zhifabao.common.factory.presenter.consultation;

import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.presenter.BaseContract;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public interface ConsultationContract extends BaseContract {

    interface View extends BaseContract.View<Presenter> {
        void onLoadLawSuccess(ResModel result);

        void onLoadCaseSuccess(ResModel result);

        void onLoadProcessDocumentsSuccess(ResModel result);
    }

    interface Presenter extends BaseContract.Presenter {
        void loadLaw(String city);

        void loadCase();

        void loadProcessDocuments();
    }
}
