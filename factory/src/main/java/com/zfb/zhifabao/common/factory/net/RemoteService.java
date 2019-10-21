package com.zfb.zhifabao.common.factory.net;

import com.zfb.zhifabao.common.factory.model.api.account.GetCodeModel;
import com.zfb.zhifabao.common.factory.model.api.account.LoginModel;
import com.zfb.zhifabao.common.factory.model.api.account.MsgLoginModel;
import com.zfb.zhifabao.common.factory.model.api.account.RegisterModel;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.account.UserInfo;
import com.zfb.zhifabao.common.factory.model.api.find.FindContent;
import com.zfb.zhifabao.common.factory.model.api.find.GetFindContentModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RemoteService {

    @POST("phonenumberpassword")
    Call<ResModel<UserInfo>> accountLogin(@Body LoginModel model);

    @POST("smslogin")
    Call<ResModel<UserInfo>> accountMsgLogin(@Body MsgLoginModel model);

    @POST("login/smscode")
    Call<ResModel> accountGetLoginCode(@Body GetCodeModel model);

    @POST("register/smscode")
    Call<ResModel> accountGetRegisterCode(@Body GetCodeModel model);

    @POST("register/userinfo ")
    Call<ResModel> accountRegister(@Body RegisterModel model);

    @POST("getRecommendFileList")
    Call<ResModel<List<FindContent>>> findGetFindcontent(@Body GetFindContentModel model);


    @Multipart
    @POST("maintainUserInformation")
    Call<ResModel<UserInfo>> upUserInfo(
            @Part("token") RequestBody token,
            @Part("phonenumber") RequestBody phonenumber,
            @Part("username") RequestBody username,
            @Part("company") RequestBody company,
            @Part MultipartBody.Part portraitFile);
}
