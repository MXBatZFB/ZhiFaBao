package com.zfb.zhifabao.common.factory.data;

import android.util.Log;

import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLabourLawModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLookLawResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.LoadLookFileModel;
import com.zfb.zhifabao.common.factory.net.NetWork;
import com.zfb.zhifabao.common.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class ConsultationHelper {

    public static void loadLaw(String city, final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        GetLabourLawModel model = new GetLabourLawModel(city);
        Call<ResModel> call = service.loadLawByCity(model);
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel> call, Throwable t) {
                Log.e("delong", "Throwable>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + t.getMessage());
                //提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });
    }


    public static void loadCase(final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel> call = service.loadCase();
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                Log.e("delong", response.message());
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel> call, Throwable t) {
                Log.e("delong", "Throwable>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + t.getMessage());
                //提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });
    }


    public static void loadLookFile(String fileName, String doType, final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        LoadLookFileModel model = new LoadLookFileModel(doType, fileName);
        Call<ResModel<GetLookLawResultModel>> call = service.loadLookFile(model);

        call.enqueue(new Callback<ResModel<GetLookLawResultModel>>() {
            @Override
            public void onResponse(Call<ResModel<GetLookLawResultModel>> call, Response<ResModel<GetLookLawResultModel>> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel<GetLookLawResultModel>> call, Throwable t) {
                Log.e("delong", "Throwable>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + t.getMessage());
                //提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
            }
        });
    }

    public static void loadProcessDocuments(final DataSource.Callback callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel> call = service.loadProcessDocuments();
        call.enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel> call, Throwable t) {
                Log.e("delong", "Throwable>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + t.getMessage());
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
            }
        });
    }

}