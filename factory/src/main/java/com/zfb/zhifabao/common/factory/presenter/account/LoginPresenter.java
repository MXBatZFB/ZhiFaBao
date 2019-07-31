package com.zfb.zhifabao.common.factory.presenter.account;

import android.text.TextUtils;

import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.data.AccountHelper;
import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.model.api.LoginModel;
import com.zfb.zhifabao.common.factory.model.api.ResultForLogin;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;
import com.zfb.zhifabao.common.factory.utils.AESCBCUtils;

import java.util.regex.Pattern;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter , Common.Constance, DataSource.Callback<ResultForLogin> {

    public LoginPresenter(LoginContract.View mView) {
        super(mView);
    }

    @Override
    public void login( String numberPhone, String password) {
        start();
        LoginContract.View view = getmView();
        if (view == null) {
            return;
        }
        if (!checkMoblie(numberPhone)) {
            //提示手机号码不合法
            view.showError(Application.getInstance().getString(R.string.data_account_invalid_parameter_mobile));
        } else if (password.length() < 6) {
            //提示密码，或者验证码格式不对
            view.showError(Application.getInstance().getString(R.string.data_account_invalid_parameter_password));
        } else if (password.length() > 6) {//大于6是密码,说明是密码 需要AECCBC加密
            //对密码进行加密操作
            password = AESCBCUtils.encrypt(password);
            //加密后直接登录
        }
        //创建Login请求的参数model
        LoginModel model = new LoginModel(GRANT_TYPE, numberPhone, password, CLIENT_ID, CLIENT_SECRET);
        //发起请求
        AccountHelper.login(model, this);
    }

    /**
     * 检查用户输入的号码是否合法
     *
     * @param phone 用户传入的号码
     * @return boolean true 表示合法 false不合法
     */
    @Override
    public boolean checkMoblie(String phone) {
        //合法为true
        return !TextUtils.isEmpty(phone) &&
                Pattern.matches(Common.Constance.REGEX_MOBILE, phone);
    }

    @Override
    public void onDataLoaded(ResultForLogin result) {
        LoginContract.View view = getmView();
        if (view == null) {
            return;
        }
        view.loginSuccess();
    }

    @Override
    public void onDtaNotAvailable(String msg) {
        LoginContract.View view = getmView();
        if (view == null) {
            return;
        }
        view.showError(msg);
    }
}
