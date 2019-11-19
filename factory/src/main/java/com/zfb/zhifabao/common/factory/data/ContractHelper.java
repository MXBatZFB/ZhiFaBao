package com.zfb.zhifabao.common.factory.data;

import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.contract.ContractResultMode;
import com.zfb.zhifabao.common.factory.net.NetWork;
import com.zfb.zhifabao.common.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class ContractHelper {
    public static void getContractType(final DataSource.Callback callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel<ContractResultMode>> call = service.getContractType();
        call.enqueue(new Callback<ResModel<ContractResultMode>>() {
            @Override
            public void onResponse(Call<ResModel<ContractResultMode>> call, Response<ResModel<ContractResultMode>> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel<ContractResultMode>> call, Throwable t) {
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });
    }
}
