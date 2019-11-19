package com.zfb.zhifabao.common.factory.presenter.look;

import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseSuggestionModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseSuggestionResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLookLawResultModel;
import com.zfb.zhifabao.common.factory.presenter.BaseContract;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public interface LookContract extends BaseContract {

    interface View extends BaseContract.View<Presenter> {
        void onDownSuccess();

        void onLookSuccess(GetLookLawResultModel model);

        void onLookSuggestionSuccess(GetCaseSuggestionResultModel model);
    }

    interface Presenter extends BaseContract.Presenter {
        void lookLaw(String lawName);

        void downLaw(String lawName);

        void lookCaseSuggestion(GetCaseSuggestionModel model);
    }
}
