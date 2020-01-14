package com.zfb.zhifabao.common.factory.presenter.cases;

import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseTypeResultModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetPlanForIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.cases.ResolveLaborCasesModel;
import com.zfb.zhifabao.common.factory.model.api.cases.ResolveLaborCasesResultModel;
import com.zfb.zhifabao.common.factory.presenter.BaseContract;

public interface ShowCaseContract extends BaseContract {
    interface View extends BaseContract.View<Presenter> {
        void onLoadPlanPlanSuccess(GetPlanForIdResultModel model);
    }

    interface Presenter extends BaseContract.Presenter {
        void loadTextTemPlan(String id);
    }

}
