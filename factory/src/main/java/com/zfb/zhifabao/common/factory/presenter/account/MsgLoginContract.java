package com.zfb.zhifabao.common.factory.presenter.account;

import com.zfb.zhifabao.common.factory.presenter.BaseContract;

public interface MsgLoginContract {
    interface  View extends BaseContract.View<Presenter>{

        void loginSuccess();

        void toRegister();

        void showTimer();

    }

    interface Presenter extends BaseContract.Presenter{
        //发起登录
        void login( String numberPhone,String code);

        //检查电话号码
        boolean checkMoblie(String phone);

        //获取验证码
        void  getCode(String phone);
    }
}
