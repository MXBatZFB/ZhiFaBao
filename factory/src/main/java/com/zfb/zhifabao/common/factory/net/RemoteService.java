package com.zfb.zhifabao.common.factory.net;

import com.zfb.zhifabao.common.factory.model.api.account.FractionResultModel;
import com.zfb.zhifabao.common.factory.model.api.account.LoginModel;
import com.zfb.zhifabao.common.factory.model.api.account.MsgLoginModel;
import com.zfb.zhifabao.common.factory.model.api.account.RegisterModel;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.account.UserInfo;
import com.zfb.zhifabao.common.factory.model.api.assess.SubmitResultModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseTypeResultModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetPlanForIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetTextTemplateForIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetTextTemplateListResultModel;
import com.zfb.zhifabao.common.factory.model.api.cases.ResolveLaborCasesModel;
import com.zfb.zhifabao.common.factory.model.api.cases.ResolveLaborCasesResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetCaseListWithTypeResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetCategoryOfLabourLawResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetControversyTypeListResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLabourLawContentByIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLabourLawListWithRegionIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLabourLawListWithTypeResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.TestBean;
import com.zfb.zhifabao.common.factory.model.api.contract.CustomContractModel;
import com.zfb.zhifabao.common.factory.model.api.contract.CustomContractResultModel;
import com.zfb.zhifabao.common.factory.model.api.law.GetCityWithProvinceIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.law.GetRegionResultModel;
import com.zfb.zhifabao.common.factory.model.api.member.OrderResultModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RemoteService {

    /**
     * 根据原被告参数返回相应的案件类型
     * @param identity
     * @return
     */
    @GET("solved-cases/types")
    Call<ResModel<GetCaseTypeResultModel>> getCaseType(@Query("identity") String identity);


    /**
     * 解决案件的接口
     * @param model
     * @return
     */
    @POST("solved-cases")
    Call<ResModel<ResolveLaborCasesResultModel>> resolveLaborCase(@Body ResolveLaborCasesModel model);


    /**
     * 根据方案ID获取具体方案的数据
     */
    @GET("solved-cases/{id}")
    Call<ResModel<GetPlanForIdResultModel>> getPlanForId(@Path(value = "id",encoded = true) String id);


    /**
     * 根据以下参数获取文本模板列表
     *identity	string	是	0：被告 1：原告	0
     * process	string	是	法律流程 0：一审，1：二审，2：再审，3:劳动仲裁
     */
     @GET("solved-cases/text-templates/{id}")
     Call<ResModel<GetTextTemplateForIdResultModel>> getTextTemplateList(@Path(value = "id",encoded = true) String id);


    /**
     * 获取相应方案对应的模板列表
     * @param identity 0：被告 1：原告	0
     * @param process 0：一审，1：二审，2：再审，3:劳动仲裁
     * @return
     */
    @GET("solved-cases/text-templates")
     Call<GetTextTemplateListResultModel> getTextTemplateList(@Query("identity")String identity,
                                                              @Query("process" )String process);

    /**
     * 用户注册
     * @param model
     * @return
     */
    @POST("user/regist")
    Call<ResModel> accountRegister(@Body RegisterModel model);

    /**
     * 密码登陆
     * @param model
     * @return
     */
    @POST("user/login")
   Call<ResModel<UserInfo>> accountLogin(@Body LoginModel model);


    /**
     * 通过验证码登录
     * @param model
     * @return
     */
    @POST("user/loginByCode")
    Call<ResModel<UserInfo>> accountMsgLogin(@Body MsgLoginModel model);

    /**
     * 获取登陆验证码
     * @param phone
     * @return
     */
    @GET("user/sendLoginCode")
    Call<ResModel> accountGetLoginCode(@Query(value = "phone")String phone);

    /**
     * 获取注册验证码
     * @param phone
     * @return
     */
    @GET("user/sendRegistCode")
    Call<ResModel> accountGetRegisterCode(@Query(value = "phone")String phone);

    /**
     * 获取修改账号密码的验证码
     * @param phone
     * @return
     */
    @POST("user/sendChangeCode")
    Call<ResModel> accountGetModifyCode(@Query(value = "phone")String phone);


    /**
     * 生成合同的接口
     * @param model
     * @return
     */
    @POST("custom-contracts")
    Call<ResModel<CustomContractResultModel>> generateContract(@Body CustomContractModel model);

    /**
     * 完善用户信息的接口
     * @param nickname
     * @param sex
     * @param portraitFile
     * @return
     */
    @Multipart
    @POST("user/userInfo")
    Call<ResModel<UserInfo>> upUserInfo(
            @Part("nickname") RequestBody nickname,
            @Part("sex") RequestBody sex,
            @Part MultipartBody.Part portraitFile);

    /**
     * 开通会员，创建微信支付订单的接口
     * @param price
     * @return
     */
    @GET("memberUser")
    Call<ResModel<OrderResultModel>> createOrder(@Query(value="price")String price);

    @GET("regions")
    Call<ResModel<GetRegionResultModel>> getProvinceList();

    @GET("regions/{id}")
    Call<ResModel<GetCityWithProvinceIdResultModel>> getCityList(@Path(value = "id",encoded = true)String id);

    @GET("law-and-regulations/types")
    Call<ResModel<GetCategoryOfLabourLawResultModel>> getCategoryOfLabourLawList();

    @GET("law-and-regulations")
    Call<ResModel<GetLabourLawListWithTypeResultModel>> getLabourLawList(@Query(value = "type") String type);

    @GET("related-cases/types")
    Call<ResModel<GetControversyTypeListResultModel>> getControversyTypeList();

    @GET("employee/paper")
    Call<ResModel<TestBean>> assessGet(@Query(value = "type") String type);

    @POST("employee/answer")
    Call<ResModel<FractionResultModel>> submitAssessResult(@Body SubmitResultModel model);

    @GET("law-and-regulations/{id}")
    Call<ResModel<GetLabourLawContentByIdResultModel>> getLabourLawContent(@Path(value = "id" ,encoded = true)String id);

    @GET("local-labour-laws/{id}")
    Call<ResModel<GetLabourLawContentByIdResultModel>> getLocalContentWithLawById(@Path(value = "id" ,encoded = true)String id);

    @GET("local-labour-laws")
    Call<ResModel<GetLabourLawListWithRegionIdResultModel>> loadLawListByRegionId(@Query("regionId") String regionId);

    @GET("related-cases")
    Call<ResModel<GetCaseListWithTypeResultModel>> loadCaseListByType(@Query(value = "type") String type);

    @GET("related-cases/{id}")
    Call<ResModel<GetLabourLawContentByIdResultModel>> loadCaseContentById(@Path(value = "id" ,encoded = true)String id);

    @GET("contracts")
    Call<ResponseBody> downContractFile(@Query(value = "contractId" ,encoded = true) String contractId);
}
