package com.zfb.zhifabao.common.factory.presenter.contract;

import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.factory.data.ContractHelper;
import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.contract.CustomContractModel;
import com.zfb.zhifabao.common.factory.model.api.contract.CustomContractResultModel;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

public class GeneratePresenter extends BasePresenter<GenerateContract.View> implements GenerateContract.Presenter,DataSource.Callback<ResModel> {
    public GeneratePresenter(GenerateContract.View mView) {
        super(mView);
    }

    @Override
    public void generateContract(CustomContractModel model) {
        ContractHelper.generateContract(model,this);
    }


    @Override
    public void onDataLoaded(ResModel result) {
        int code = result.getCode();
        //200
        //1018：试用期期限错误
        //1019：身份证号码错误
        //1020：统一社会信用代码错误
        //1021：参数错误（参数为空或参数值不合法）
         if (code==200){
             getmView().onGenerateSuccess((CustomContractResultModel) result.getData());
             Application.showToast(result.getMsg());
          }else {
             getmView().onParameterError(code,result.getMsg());
         }
    }

    @Override
    public void onDtaNotAvailable(String msg) {

    }
}
