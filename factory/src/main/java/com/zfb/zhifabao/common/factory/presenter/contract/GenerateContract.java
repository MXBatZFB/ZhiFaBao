package com.zfb.zhifabao.common.factory.presenter.contract;

import com.zfb.zhifabao.common.factory.model.api.contract.CustomContractModel;
import com.zfb.zhifabao.common.factory.model.api.contract.CustomContractResultModel;
import com.zfb.zhifabao.common.factory.presenter.BaseContract;

public interface GenerateContract extends BaseContract {

    interface View extends BaseContract.View<Presenter>{
        void onGenerateSuccess(CustomContractResultModel model);
        void onParameterError(int codeError,String msg);
    }

    interface Presenter extends BaseContract.Presenter{
         void generateContract(CustomContractModel model);
    }

}
