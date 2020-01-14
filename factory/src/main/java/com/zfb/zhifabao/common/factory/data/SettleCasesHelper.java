package com.zfb.zhifabao.common.factory.data;

import android.util.Log;

import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseSuggestionModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseSuggestionResultModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseTypeResultModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetPlanForIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.cases.ResolveLaborCasesModel;
import com.zfb.zhifabao.common.factory.model.api.cases.ResolveLaborCasesResultModel;
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
    public static void loadCaseType(String identity,final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel<GetCaseTypeResultModel>> call = service.getCaseType(identity);
        call.enqueue(new Callback<ResModel<GetCaseTypeResultModel>>() {
            @Override
            public void onResponse(Call<ResModel<GetCaseTypeResultModel>> call, Response<ResModel<GetCaseTypeResultModel>> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel<GetCaseTypeResultModel>> call, Throwable t) {
                Log.e("delong", "Throwable>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + t.getMessage());
                //提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
            }
        });
    }

    public static void resolveLaborCase(ResolveLaborCasesModel model, final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel<ResolveLaborCasesResultModel>> call = service.resolveLaborCase(model);
        call.enqueue(new Callback<ResModel<ResolveLaborCasesResultModel>>() {
            @Override
            public void onResponse(Call<ResModel<ResolveLaborCasesResultModel>> call, Response<ResModel<ResolveLaborCasesResultModel>> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel<ResolveLaborCasesResultModel>> call, Throwable t) {
                Log.e("delong", "Throwable>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + t.getMessage());
                //提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });
    }


    public static void loadPlan(String id,final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel<GetPlanForIdResultModel>> call = service.getPlanForId(id);
        call.enqueue(new Callback<ResModel<GetPlanForIdResultModel>>() {
            @Override
            public void onResponse(Call<ResModel<GetPlanForIdResultModel>> call, Response<ResModel<GetPlanForIdResultModel>> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel<GetPlanForIdResultModel>> call, Throwable t) {
                //提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });
    }
}
