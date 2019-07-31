package com.zfb.zhifabao.common.factory.net;

import android.util.Log;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.factory.Factory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWork implements Common.Constance {

    public static Retrofit getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        return builder.baseUrl(API_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(Factory.getGson()))
                .build();
    }

    private static OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("delong","OkHttp====Message:"+message);
            }
        });
        loggingInterceptor.setLevel(level);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }

}
