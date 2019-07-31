package com.zfb.zhifabao.common.factory.presenter.account;

import com.zfb.zhifabao.common.factory.presenter.BaseContract;

public interface LoginContract {
    interface  View extends BaseContract.View<Presenter>{
        //登录成功
        void loginSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        //发起deng
        void login(String numberPhone,String password);

        boolean checkMoblie(String phone);
    }
}
