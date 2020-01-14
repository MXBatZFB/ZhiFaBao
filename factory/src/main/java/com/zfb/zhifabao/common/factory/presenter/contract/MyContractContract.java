package com.zfb.zhifabao.common.factory.presenter.contract;

import com.zfb.zhifabao.common.factory.model.db.ContractFileInfo;
import com.zfb.zhifabao.common.factory.presenter.BaseContract;

import java.util.List;

public interface MyContractContract extends BaseContract {

    interface View extends BaseContract.View<Presenter>{
      void onCheckLocalContractSuccess(List<ContractFileInfo> lists);
    }

    interface Presenter extends BaseContract.Presenter{
     void checkLocalContract();
    }

}
