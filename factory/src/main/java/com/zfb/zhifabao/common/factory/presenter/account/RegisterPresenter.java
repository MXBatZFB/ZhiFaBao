package com.zfb.zhifabao.common.factory.presenter.account;

import android.text.TextUtils;

import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.data.AccountHelper;
import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

import java.util.regex.Pattern;

import static com.zfb.zhifabao.common.app.Application.getInstance;

public class RegisterPresenter extends BasePresenter<RegisterContract.View>
        implements RegisterContract.Presenter,
        DataSource.Callback<ResModel>,
        Common.Constance {

    public RegisterPresenter(RegisterContract.View mView) {
        super(mView);
    }

    /**
     * 用户注册的请求入口
     * @param code
     * @param phone
     * @param password
     */
    @Override
    public void register(String code, String phone, String password) {
        start();
        RegisterContract.View view = getmView();
        if (view == null)
            return;
        if (checkPassword(password)) {
            if (checkPhone(phone)) {
                AccountHelper.register(phone, password, code, this);
            }
        }
    }

    @Override
    public void getRegisterCode(String phone) {
        if (checkPhone(phone)) {
            getmView().starTimer();
            AccountHelper.getRegisterCode(phone, new DataSource.Callback<ResModel>() {
                @Override
                public void onDataLoaded(ResModel result) {
                    getmView().registerSuccess();
                }

                @Override
                public void onDtaNotAvailable(String msg) {
                    getmView().showError(msg);
                }
            });
        }
    }

    /**
     * @param password 密码
     * @return true 表示密码合法 false 表示不合法
     */
    @Override
    public boolean checkPassword(String password) {
        if (password.length() <= 9) {
            getmView().showError("密码必须大于9位");
            return false;
        }
        return password.length() > 9;
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

    /**
     * 注册成功的回调
     * @param result 返回的数据
     */
    @Override
    public void onDataLoaded(ResModel result) {

        if (result.getCode() == 6205) {
            getmView().registerSuccess();
            getmView().showError(result.getMsg());
        } else {
            getmView().showError(result.getMsg());
        }

    }

    /**
     * 注册失败的回调
     * @param msg 失败详情
     */
    @Override
    public void onDtaNotAvailable(String msg) {
        getmView().showError(msg);
    }
}
