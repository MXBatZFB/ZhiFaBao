package com.zfb.zhifabao.common.factory.presenter.account;

import com.zfb.zhifabao.common.factory.presenter.BaseContract;

public interface RegisterContract {
    interface  View extends BaseContract.View<Presenter>{
        //注册成功
        void loginSuccess();


    }

    interface Presenter extends BaseContract.Presenter {
        //发起注册
        void register(String code,String phone,String first,String second);

        //检查密码
        boolean checkPassword(String first ,String second);

    }
}
