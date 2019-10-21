package com.zfb.zhifabao.common.factory.presenter.account;

import com.zfb.zhifabao.common.factory.presenter.BaseContract;

public interface RegisterContract {
    interface  View extends BaseContract.View<Presenter>{
        //注册成功
        void registerSuccess();

        //验证码获取成功
        void getCodeSuccess();

        //重新获取验证码倒计时
        void starTimer();
    }

    interface Presenter extends BaseContract.Presenter {
        //发起注册
        void register(String code, String phone, String pwd);

        //获取注册验证码
        void getRegisterCode(String phone);

        //检查密码
        boolean checkPassword(String pwd);

        //检查手机号码格式是否正确
        boolean checkPhone(String phone);
    }
}
