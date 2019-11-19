package com.zfb.zhifabao.common.factory.presenter.contract;

import com.zfb.zhifabao.common.factory.model.api.contract.ContractResultMode;
import com.zfb.zhifabao.common.factory.presenter.BaseContract;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public interface ReviewContract extends BaseContract {
    interface Presenter extends BaseContract.Presenter {
        void getContractType();
    }

    interface View extends BaseContract.View<Presenter> {
        void onGetTypeSuccess(ContractResultMode mode);
    }

}
