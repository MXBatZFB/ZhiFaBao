package com.zfb.zhifabao.common.factory.presenter.account;

import com.zfb.zhifabao.common.factory.presenter.BaseContract;

public interface MsgGetCodeContract {
    interface  View extends BaseContract.View<Presenter>{
        void getCodeSuccess();
    }

    interface Presenter extends BaseContract.Presenter{
        //检查电话号码
        boolean checkMoblie(String phone);

        //获取验证码
        void  getCode(String phone);
    }
}
