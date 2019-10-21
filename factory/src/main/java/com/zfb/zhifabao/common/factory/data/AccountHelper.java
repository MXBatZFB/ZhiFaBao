package com.zfb.zhifabao.common.factory.data;

import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.model.api.account.GetCodeModel;
import com.zfb.zhifabao.common.factory.model.api.account.LoginModel;
import com.zfb.zhifabao.common.factory.model.api.account.MsgLoginModel;
import com.zfb.zhifabao.common.factory.model.api.account.RegisterModel;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.account.UserInfo;
import com.zfb.zhifabao.common.factory.net.NetWork;
import com.zfb.zhifabao.common.factory.net.RemoteService;
import com.zfb.zhifabao.common.factory.persistence.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountHelper {
    /**
     * 用户注册
     * @param phone 用户电话号码
     * @param password 用户在设置的密码
     * @param callback 用于处理请求结果的回调
     */
    public static void register(final String phone, final String password, String code, final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel> call = service.accountRegister(new RegisterModel(phone, "", password, "", code));
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                ResModel model = response.body();
                callback.onDataLoaded(model);
            }
            @Override
            public void onFailure(Call<ResModel> call, Throwable t) {
                //  提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
            }
        });
    }


    /**
     * 用户使用短信验证码登录
     * @param model       传递一个登陆的model
     * @param callback   回调接口
     */
    public static void byMsglogin(MsgLoginModel model, final DataSource.Callback<ResModel<UserInfo>> callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel<UserInfo>> call = service.accountMsgLogin(model);
        call.enqueue(new Callback<ResModel<UserInfo>>() {

            @Override
            public void onResponse(Call<ResModel<UserInfo>> call, Response<ResModel<UserInfo>> response) {
                //请求成功
                callback.onDataLoaded(response.body());
                Account.login(response.body().getResult());
            }

            @Override
            public void onFailure(Call<ResModel<UserInfo>> call, Throwable t) {
                //  提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
            }
        });
    }


    /**
     * 用户使用密码登录
     *
     * @param model    传递一个登陆的model
     * @param callback 回调接口
     */
    public static void login(LoginModel model, final DataSource.Callback<ResModel<UserInfo>> callback) {
        RemoteService sevicw = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel<UserInfo>> call = sevicw.accountLogin(model);
        call.enqueue(new Callback<ResModel<UserInfo>>() {

            @Override
            public void onResponse(Call<ResModel<UserInfo>> call, Response<ResModel<UserInfo>> response) {
                UserInfo userInfo = response.body().getResult();
                Account.login(userInfo);
                //请求成功
                callback.onDataLoaded(response.body());

            }

            @Override
            public void onFailure(Call<ResModel<UserInfo>> call, Throwable t) {
                //  提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
            }
        });
    }

    /**
     * 获取验证码
     * @param phone 用户电话号码
     * @param callback 用于处理请求结果的回调
     */
    public static void getLoginCode(String phone, final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel> call = service.accountGetLoginCode(new GetCodeModel(phone));
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                       //请求成功
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel> call, Throwable t) {
                //  提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
            }
        });
    }

    public static void getRegisterCode(String phone, final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel> call = service.accountGetRegisterCode(new GetCodeModel(phone));
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                //请求成功
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel> call, Throwable t) {
                //  提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
            }
        });
    }
}
