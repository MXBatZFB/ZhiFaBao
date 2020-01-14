package com.zfb.zhifabao.common.factory.presenter.cases;

import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseTypeResultModel;
import com.zfb.zhifabao.common.factory.model.api.cases.ResolveLaborCasesModel;
import com.zfb.zhifabao.common.factory.model.api.cases.ResolveLaborCasesResultModel;
import com.zfb.zhifabao.common.factory.presenter.BaseContract;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public interface SettleCasesContract extends BaseContract {
    interface View extends BaseContract.View<Presenter> {
        void onLoadCaseTypeSuccess(GetCaseTypeResultModel result);

        void onResolveLaborCasesComplete(ResolveLaborCasesResultModel result);

    }

    interface Presenter extends BaseContract.Presenter {
        void loadCaseType(String identity);

        void doCase(ResolveLaborCasesModel model);
    }
}
