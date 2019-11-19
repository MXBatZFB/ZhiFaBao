package com.zfb.zhifabao.common.factory.net;

import android.text.TextUtils;
import android.util.Log;

import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.persistence.Account;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
//        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
//                .Builder();
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder builder = request.newBuilder();
                Log.e("delong", "OkHttp====Token:" + Account.getToken());
                if (!TextUtils.isEmpty(Account.getToken())) {
                    builder.addHeader("token", Account.getToken());
                }
                builder.addHeader("Content-Typle", "application/json");
                builder.addHeader("Connection", "Keep-Alive");
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        });
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }
}
