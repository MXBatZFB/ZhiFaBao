package com.zfb.zhifabao.common.factory.presenter.account;

import android.text.TextUtils;

import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.data.AccountHelper;
import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.model.api.account.LoginModel;
import com.zfb.zhifabao.common.factory.model.api.account.MsgLoginModel;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.account.UserInfo;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

import java.util.regex.Pattern;

import static com.zfb.zhifabao.common.app.Application.getInstance;

public class LoginPresenter extends BasePresenter<LoginContract.View>
        implements LoginContract.Presenter, Common.Constance, DataSource.Callback<ResModel<UserInfo>> {

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

        if (checkPhone(numberPhone)) {
            //创建Login请求的参数model
            LoginModel model = new LoginModel(numberPhone, password);
            //发起请求
            AccountHelper.login(model, this);
        }
    }

    @Override
    public void msgLogin(String numberPhone, String code) {
        start();
        if (checkPhone(numberPhone)) {
            AccountHelper.byMsglogin(new MsgLoginModel(numberPhone, code), this);
        }
    }

    @Override
    public boolean checkPhone(String phone) {
        if (TextUtils.isEmpty(phone) || !Pattern.matches(Common.Constance.REGEX_MOBILE, phone)) {
            getmView().showError(getInstance().getString(R.string.data_account_invalid_parameter_mobile));
            return false;
        }
        return !TextUtils.isEmpty(phone) &&
                Pattern.matches(Common.Constance.REGEX_MOBILE, phone);
    }

    @Override
    public void onDataLoaded(ResModel<UserInfo> result) {

        LoginContract.View view = getmView();
        if (view == null) {
            return;
        }
        if (result.getResult().getToken() != null) {
            view.loginSuccess();
        } else {
            view.showError(result.getMessage());
        }

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
