package com.zfb.zhifabao.common.factory.data;

import android.util.Log;

import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.find.FindContent;
import com.zfb.zhifabao.common.factory.model.api.find.GetFindContentModel;
import com.zfb.zhifabao.common.factory.net.NetWork;
import com.zfb.zhifabao.common.factory.net.RemoteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class FindHelper {

    public static void getFindList(String token, final DataSource.Callback<List<FindContent>> callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel<List<FindContent>>> call = service.findGetFindcontent(new GetFindContentModel(token));
        call.enqueue(new Callback<ResModel<List<FindContent>>>() {
            @Override
            public void onResponse(Call<ResModel<List<FindContent>>> call, Response<ResModel<List<FindContent>>> response) {
                List<FindContent> list = response.body().getResult();
                Log.e("delong", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + list.toString());
                callback.onDataLoaded(list);
            }

            @Override
            public void onFailure(Call<ResModel<List<FindContent>>> call, Throwable t) {
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
            }
        });
    }


}
