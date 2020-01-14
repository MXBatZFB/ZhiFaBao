package com.zfb.zhifabao.common.factory.data;

import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.account.UserInfo;
import com.zfb.zhifabao.common.factory.net.NetWork;
import com.zfb.zhifabao.common.factory.net.RemoteService;
import com.zfb.zhifabao.common.factory.persistence.Account;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class UploaderHelper {
    public static void update(final String userName, String sex, String portrait, final DataSource.Callback<ResModel<UserInfo>> callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        File file = new File(portrait);
        if (Account.isLogin()) {

            RequestBody userNameBody = RequestBody.create(MediaType.parse("multipart/form-data"), userName);
            RequestBody sexNameBody = RequestBody.create(MediaType.parse("multipart/form-data"), sex);
            RequestBody portraitBody = RequestBody.create(MediaType.parse("image/jpg"), file);
            MultipartBody.Part portraitMultipartBody = MultipartBody.Part.createFormData("portraitFile", file.getName(), portraitBody);
            Call<ResModel<UserInfo>> call = service.upUserInfo( userNameBody, sexNameBody, portraitMultipartBody);
            call.enqueue(new Callback<ResModel<UserInfo>>() {
                @Override
                public void onResponse(Call<ResModel<UserInfo>> call, Response<ResModel<UserInfo>> response) {
                    UserInfo userInfo = response.body().getData();
                    Account.completeInfo(userInfo);
                    callback.onDataLoaded(response.body());
                }

                @Override
                public void onFailure(Call<ResModel<UserInfo>> call, Throwable t) {
                    //提示网络请求失败
                    callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                    call.cancel();
                }
            });
        }
    }
}
