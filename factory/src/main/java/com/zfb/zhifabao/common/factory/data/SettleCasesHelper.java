package com.zfb.zhifabao.common.factory.data;

import android.util.Log;

import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseSuggestionModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseSuggestionResultModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseTypeResultModel;
import com.zfb.zhifabao.common.factory.net.NetWork;
import com.zfb.zhifabao.common.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class SettleCasesHelper {
    public static void loadCaseType(final DataSource.Callback<GetCaseTypeResultModel> callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel<GetCaseTypeResultModel>> call = service.loadCaseType();
        call.enqueue(new Callback<ResModel<GetCaseTypeResultModel>>() {
            @Override
            public void onResponse(Call<ResModel<GetCaseTypeResultModel>> call, Response<ResModel<GetCaseTypeResultModel>> response) {
                callback.onDataLoaded(response.body().getResult());
            }

            @Override
            public void onFailure(Call<ResModel<GetCaseTypeResultModel>> call, Throwable t) {
                Log.e("delong", "Throwable>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + t.getMessage());
                //提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
            }
        });
    }

    public static void loadCaseSuggestion(GetCaseSuggestionModel model, final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel<GetCaseSuggestionResultModel>> call = service.loadCaseSuggestion(model);
        call.enqueue(new Callback<ResModel<GetCaseSuggestionResultModel>>() {
            @Override
            public void onResponse(Call<ResModel<GetCaseSuggestionResultModel>> call, Response<ResModel<GetCaseSuggestionResultModel>> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel<GetCaseSuggestionResultModel>> call, Throwable t) {
                Log.e("delong", "Throwable>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + t.getMessage());
                //提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });

    }
}
