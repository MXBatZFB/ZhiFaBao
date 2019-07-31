package com.zfb.zhifabao.common.factory.presenter.account;

import android.text.TextUtils;

import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.data.AccountHelper;
import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.model.api.LoginModel;
import com.zfb.zhifabao.common.factory.model.api.ResModel;
import com.zfb.zhifabao.common.factory.model.api.ResultForLogin;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

import java.util.regex.Pattern;

public class MsgLoginPresenter extends BasePresenter<MsgLoginContract.View>
        implements MsgLoginContract.Presenter,
        Common.Constance,
        DataSource.Callback<ResultForLogin> {

    public MsgLoginPresenter(MsgLoginContract.View mView) {
        super(mView);
    }

    /**
     * 用户登录
     *
     * @param numberPhone 用户名
     * @param code        密码
     */
    @Override
    public void login(String numberPhone, String code) {
        start();
        MsgLoginContract.View view = getmView();
        if (view == null) {
            return;
        }
        if (!checkMoblie(numberPhone)) {
            //提示手机号码不合法
            view.showError(Application.getInstance().getString(R.string.data_account_invalid_parameter_mobile));
        } else if (code == null || code.length() != 6) {
            //提示输入正确的验证码
            view.showError(Application.getInstance().getString(R.string.data_account_code_error));
        } else {
            //说明是验证码，首先校验验证码
            //如果校验成功直接登录 失败则提示
            goLogin(numberPhone, code);
        }
    }

    /**
     * 开始网络登录请求
     *
     * @param numberPhone 登录账号
     * @param code    用户的得到的验证码
     */
    private void goLogin(String numberPhone, String code) {
        //创建Login请求的参数model
        LoginModel model = new LoginModel(GRANT_TYPE, numberPhone, code, CLIENT_ID, CLIENT_SECRET);
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

    /**
     * 获取验证码
     *
     * @param phone 用户传入的电话号码
     */
    @Override
    public void getCode(String phone) {
        final MsgLoginContract.View view = getmView();
        if (view == null)
            return;
        //检查手机号码是否合法
        if (checkMoblie(phone)) {
            AccountHelper.getCode(phone, new DataSource.Callback<ResModel>() {
                @Override
                public void onDtaNotAvailable(String strRes) {  //验证码获取失败时
                    //提示具体失败信息
                    view.showError(strRes);
                }

                @Override
                public void onDataLoaded(ResModel result) {//验证码获取成功时
                    Application.showToast(String.format("发送%s", result.getMessage()));
                    //可重新获取的计时器开始计时
                    view.showTimer();
                }
            });
        } else {
            //提示错误
            view.showError(Application.getInstance().getString(R.string.data_account_invalid_parameter_mobile));
        }
    }

//    private void chekCode(String phone, final String validatedCode) {
//        AccountHelper.chekCode(phone, validatedCode, this);
//    }


    /**
     * 登录请求成功的回调具体实现方法
     *
     * @param result 请求成功得到的数据
     */
    @Override
    public void onDataLoaded(ResultForLogin result) {
        //通知界面登陆成功
        final MsgLoginContract.View view = getmView();
        if (view == null)
            return;
        //强制执行在主线程，避免阻塞主线程
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                view.loginSuccess();
            }
        });
    }

    /**
     * 登录请求失败的回调具体实现方法
     *
     * @param strRes 失败的原因描述
     */
    @Override
    public void onDtaNotAvailable(String strRes) {
        //网络请求告知登录失败,在主界面显示错误消息
        final MsgLoginContract.View view = getmView();
        if (view == null)
            return;
        if (strRes.contains(Factory.app().getString(R.string.account_data_user_not_exist))) {
            view.toRegister();
            strRes = strRes + ",请设置密码登录！";
        }
        //强制执行在主线程，避免阻塞主线程
        final String finalStrRes = strRes;
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                view.showError(finalStrRes);
            }
        });
    }

}