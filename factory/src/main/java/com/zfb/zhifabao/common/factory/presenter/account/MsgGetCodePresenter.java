package com.zfb.zhifabao.common.factory.presenter.account;

import android.text.TextUtils;

import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.data.AccountHelper;
import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

import java.util.regex.Pattern;

public class MsgGetCodePresenter extends BasePresenter<MsgGetCodeContract.View>
        implements MsgGetCodeContract.Presenter,
        Common.Constance,
        DataSource.Callback<ResModel> {

    public MsgGetCodePresenter(MsgGetCodeContract.View mView) {
        super(mView);
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
     * @param phone 用户传入的电话号码
     */
    @Override
    public void getCode(String phone) {
        final MsgGetCodeContract.View view = getmView();
        if (view == null)
            return;
        start();
        //检查手机号码是否合法
        if (checkMoblie(phone)) {
            AccountHelper.getLoginCode(phone, this);
        } else {
            //提示错误
            view.showError(Application.getInstance().getString(R.string.data_account_invalid_parameter_mobile));
        }
    }

    /**
     * 登录请求验证码成功的回调具体实现方法
     * @param result 请求成功得到的数据
     */
    @Override
    public void onDataLoaded(final ResModel result) {
        //通知界面验证码发送成功
        final MsgGetCodeContract.View view = getmView();
        if (view == null)
            return;
        //强制执行在主线程，避免阻塞主线程
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                if (result.getCode() == 6208) {
                    view.getCodeSuccess();
                } else {
                    view.showError(result.getMessage());
                }
            }
        });
    }

    /**
     * 登录请求失败的回调具体实现方法
     * @param strRes 失败的原因描述
     */
    @Override
    public void onDtaNotAvailable(String strRes) {
        //网络请求告知登录失败,在主界面显示错误消息
        final MsgGetCodeContract.View view = getmView();
        if (view == null)
            return;
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