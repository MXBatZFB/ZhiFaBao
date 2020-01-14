package com.zfb.zhifabao.common.factory.data;

import android.util.Log;

import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.model.api.account.FractionResultModel;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.assess.AssessModel;
import com.zfb.zhifabao.common.factory.model.api.assess.SubmitResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.TestBean;
import com.zfb.zhifabao.common.factory.net.NetWork;
import com.zfb.zhifabao.common.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class AssessHelper {

    public static void getAssessQuestion(String type, final DataSource.Callback callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel<TestBean>> call = service.assessGet(type);

        call.enqueue(new Callback<ResModel<TestBean>>() {
            @Override
            public void onResponse(Call<ResModel<TestBean>> call, Response<ResModel<TestBean>> response) {
                TestBean testBean = response.body().getData();
                callback.onDataLoaded(testBean);
            }

            @Override
            public void onFailure(Call<ResModel<TestBean>> call, Throwable t) {
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });
    }


    public static void submitAssessResult(SubmitResultModel model, final DataSource.Callback callback) {
        RemoteService service = NetWork.getRetrofit()
                .create(RemoteService.class);
        Call<ResModel<FractionResultModel>> call = service.submitAssessResult(model);
        call.enqueue(new Callback<ResModel<FractionResultModel>>() {
            @Override
            public void onResponse(Call<ResModel<FractionResultModel>> call, Response<ResModel<FractionResultModel>> response) {
                Log.e("delong", "submitAssessResult>>>>>>>>>>" + response.isSuccessful());
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel<FractionResultModel>> call, Throwable t) {
                Log.e("delong", "onFailure>>>>>>>>>>" + t.getMessage());
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });
    }
}