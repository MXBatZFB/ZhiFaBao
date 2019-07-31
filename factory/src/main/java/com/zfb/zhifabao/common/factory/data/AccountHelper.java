package com.zfb.zhifabao.common.factory.data;

import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.model.api.ErrorResultForLogin;
import com.zfb.zhifabao.common.factory.model.api.LoginModel;
import com.zfb.zhifabao.common.factory.model.api.ResModel;
import com.zfb.zhifabao.common.factory.model.api.ResultForLogin;
import com.zfb.zhifabao.common.factory.net.NetWork;
import com.zfb.zhifabao.common.factory.net.RemoteService;
import okhttp3.ResponseBody;
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
    public static  void  register(final String phone , final String password, final DataSource.Callback<ResModel> callback){
        RemoteService service = NetWork.getRetrofit()
                                    .create(RemoteService.class) ;
        Call<ResModel> call = service.accountRegister(phone,password);
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                ResModel model = response.body();
                model.setData(password);
                model.setMessage(phone);
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
     * 用户登录
     * @param model       传递一个登陆的model
     * @param callback   回调接口
     */
    public static void login(LoginModel model, final DataSource.Callback<ResultForLogin> callback) {
        RemoteService sevicw = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResultForLogin> call = sevicw.accountLogin(model.getGrant_type(),
                model.getUsername(),
                model.getPassword(),
                model.getClient_id(),
                model.getClient_secret());

        call.enqueue(new Callback<ResultForLogin>() {
            @Override
            public void onResponse(Call<ResultForLogin> call, Response<ResultForLogin> response) {
                //成功
                ResultForLogin result = response.body();
                if (result != null) {
                    callback.onDataLoaded(result);
                } else {
                    ResponseBody body = response.errorBody();
                    try {
                        //得到返回的具体json
                        String json = body.string();
                        //得到具体对象
                        ErrorResultForLogin errorResultForLogin = Factory.getGson().fromJson(json, ErrorResultForLogin.class);
                        callback.onDtaNotAvailable(errorResultForLogin.getError_description());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResultForLogin> call, Throwable t) {
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
    public static void getCode(String phone, final DataSource.Callback<ResModel> callback) {
        RemoteService sevice = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel> call = sevice.accountGetCode(phone);
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


    /**
     * 校验验证码
     * @param phone 哦用户电话号码
     * @param validatedCode 得到的验证码
     * @param callback 用于处理请求结果的回调
     */
    public static void chekCode(final String phone, final String password,final String validatedCode, final DataSource.ValidCallback callback) {
        RemoteService service = NetWork.getRetrofit()
                                      .create(RemoteService.class);
         Call<ResModel> call = service.accountCheckCode(phone,validatedCode);
         call.enqueue(new Callback<ResModel>() {
             @Override
             public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                 final ResModel model = response.body();
                 if (model.getCode()==200){
                     callback.isValid(phone,password);
                 }else {
                     callback.isInvalid(model.getMessage());
                 }
             }
             @Override
             public void onFailure(Call<ResModel> call, Throwable t) {
                //  提示网络请求失败
                 callback.isInvalid(Factory.app().getString(R.string.data_network_error));
             }
         });
    }
}
