package com.zfb.zhifabao.common.factory.presenter.account;

import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.data.AccountHelper;
import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.model.api.LoginModel;
import com.zfb.zhifabao.common.factory.model.api.ResModel;
import com.zfb.zhifabao.common.factory.model.api.ResultForLogin;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;
import com.zfb.zhifabao.common.factory.presenter.account.RegisterContract;
import com.zfb.zhifabao.common.factory.utils.AESCBCUtils;

public class RegisterPresenter extends BasePresenter<RegisterContract.View>
        implements RegisterContract.Presenter,
        DataSource.Callback<ResModel>, DataSource.ValidCallback,
        Common.Constance {

    public RegisterPresenter(RegisterContract.View mView) {
        super(mView);
    }


    /**
     * 用户注册的请求入口
     * @param code
     * @param phone
     * @param first
     * @param second
     */
    @Override
    public void register(String code, String phone, String first, String second) {
        start();
        RegisterContract.View view = getmView();
        if (view == null)
            return;
        if (checkPassword(first, second)) {
            AccountHelper.chekCode(phone, first, code, this);
        }
    }

    /**
     * @param first  用户第一次输入的密码
     * @param second 用户第二次输入的密码
     * @return true 表示密码合法 false 表示不合法
     */
    @Override
    public boolean checkPassword(String first, String second) {
        if (first.length() <= 6 || second.length() <= 6) {
            getmView().showError("密码必须大于6位");
            return false;
        }
        if (!first.equals(second)) {
            getmView().showError("两次密码不一致");
            return false;
        }
        return first.length() > 6 & second.length() > 6 & first.equals(second) ? true : false;
    }

    /**
     * 注册成功的回调
     * @param result 返回的数据
     */
    @Override
    public void onDataLoaded(ResModel result) {
        LoginModel model = new LoginModel(
                GRANT_TYPE,
                result.getMessage(),
                AESCBCUtils.encrypt((String) result.getData()),
                CLIENT_ID,
                CLIENT_SECRET);
        AccountHelper.login(model, new DataSource.Callback<ResultForLogin>() {
            @Override
            public void onDataLoaded(ResultForLogin result) {
                getmView().loginSuccess();
            }

            @Override
            public void onDtaNotAvailable(String msg) {
               getmView().showError(msg);
            }
        });
    }

    /**
     * 注册失败的回调
     * @param msg 失败详情
     */
    @Override
    public void onDtaNotAvailable(String msg) {
        getmView().showError(msg);
    }

    /**
     * 校验验证码有效的回调，成功的话表示可以注册
     * @param phone 返回用户的电话
     * @param password 返回用户设置的密码
     */
    @Override
    public void isValid(String phone, String password) {
        AccountHelper.register(phone, password, this);
    }

    /**
     * 校验验证码无效的回调
     * @param str 提示用户验证码无效的详情
     */
    @Override
    public void isInvalid(String str) {
        getmView().showError(str);
    }
}
