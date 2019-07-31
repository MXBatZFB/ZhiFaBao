package com.zfb.zhifabao.common.factory.net;

import com.zfb.zhifabao.common.factory.model.api.ResModel;
import com.zfb.zhifabao.common.factory.model.api.ResultForLogin;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RemoteService {

    @FormUrlEncoded
    @POST("um/oauth/token")
    Call<ResultForLogin> accountLogin(@Field(value = "grant_type",encoded = true) String grant_type,
                                      @Field(value = "username",encoded = true)String username,
                                      @Field(value = "password",encoded = true)String password,
                                      @Field(value = "client_id",encoded = true)String client_id,
                                      @Field(value = "client_secret",encoded = true)String client_secret);
    @FormUrlEncoded
    @POST("mns/v1/sms/getIdentifyingCode")
     Call<ResModel> accountGetCode(@Field("mobileCode") String phone);


    @FormUrlEncoded
    @POST("mns/v1/sms/validateIdentifyingCode")
    Call<ResModel> accountCheckCode(@Field("mobileCode") String phone,
                                    @Field("validatedCode")String validatedCode);


    @FormUrlEncoded
    @POST("um/v1/users/register")
    Call<ResModel> accountRegister(@Field("mobileCode") String phone,
                                    @Field("password")String password);
}
