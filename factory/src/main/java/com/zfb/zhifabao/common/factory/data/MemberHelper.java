package com.zfb.zhifabao.common.factory.data;

import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.member.OrderResultModel;
import com.zfb.zhifabao.common.factory.net.NetWork;
import com.zfb.zhifabao.common.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberHelper {


    public static void createOrder(String price, final DataSource.Callback callback) {
        RemoteService service = NetWork.getRetrofit().create(RemoteService.class);
        Call<ResModel<OrderResultModel>> call = service.createOrder(price);
        call.enqueue(new Callback<ResModel<OrderResultModel>>() {
            @Override
            public void onResponse(Call<ResModel<OrderResultModel>> call, Response<ResModel<OrderResultModel>> response) {
                 callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel<OrderResultModel>> call, Throwable t) {
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });


    }
}
