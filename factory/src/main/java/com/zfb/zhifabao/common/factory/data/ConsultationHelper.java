package com.zfb.zhifabao.common.factory.data;

import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetCaseListWithTypeResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetCategoryOfLabourLawResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetControversyTypeListResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLabourLawContentByIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLabourLawListWithRegionIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLabourLawListWithTypeResultModel;
import com.zfb.zhifabao.common.factory.model.api.law.GetCityWithProvinceIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.law.GetRegionResultModel;
import com.zfb.zhifabao.common.factory.net.NetWork;
import com.zfb.zhifabao.common.factory.net.RemoteService;
import com.zfb.zhifabao.common.factory.presenter.consultation.ConsultationPresenter;
import com.zfb.zhifabao.common.factory.presenter.consultation.ShowLawListPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class ConsultationHelper {
    public static void getProvinceList(final DataSource.Callback<ResModel> callback){
        RemoteService service = NetWork.getRetrofit().create(RemoteService.class);
        Call<ResModel<GetRegionResultModel>> call = service.getProvinceList();
        call.enqueue(new Callback<ResModel<GetRegionResultModel>>() {
            @Override
            public void onResponse(Call<ResModel<GetRegionResultModel>> call, Response<ResModel<GetRegionResultModel>> response) {
                if (response!=null){
                    callback.onDataLoaded(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResModel<GetRegionResultModel>> call, Throwable t) {
                //  提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });
    }

    public static void getCityList(String id, final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit().create(RemoteService.class);
        Call<ResModel<GetCityWithProvinceIdResultModel>> call = service.getCityList(id);
        call.enqueue(new Callback<ResModel<GetCityWithProvinceIdResultModel>>() {
            @Override
            public void onResponse(Call<ResModel<GetCityWithProvinceIdResultModel>> call, Response<ResModel<GetCityWithProvinceIdResultModel>> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel<GetCityWithProvinceIdResultModel>> call, Throwable t) {
                //  提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });

    }

    public static void getCategoryOfLabourLawList(final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit().create(RemoteService.class);
        Call<ResModel<GetCategoryOfLabourLawResultModel>> call = service.getCategoryOfLabourLawList();
        call.enqueue(new Callback<ResModel<GetCategoryOfLabourLawResultModel>>() {
            @Override
            public void onResponse(Call<ResModel<GetCategoryOfLabourLawResultModel>> call, Response<ResModel<GetCategoryOfLabourLawResultModel>> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel<GetCategoryOfLabourLawResultModel>> call, Throwable t) {
                //  提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });

    }

    public static void getLabourLawListByType(String lawType, final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit().create(RemoteService.class);
        Call<ResModel<GetLabourLawListWithTypeResultModel>> call = service.getLabourLawList(lawType);
        call.enqueue(new Callback<ResModel<GetLabourLawListWithTypeResultModel>>() {
            @Override
            public void onResponse(Call<ResModel<GetLabourLawListWithTypeResultModel>> call, Response<ResModel<GetLabourLawListWithTypeResultModel>> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel<GetLabourLawListWithTypeResultModel>> call, Throwable t) {
                //  提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });
    }

    public static void getControversyTypeList(final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit().create(RemoteService.class);
        Call<ResModel<GetControversyTypeListResultModel>> call = service.getControversyTypeList();
        call.enqueue(new Callback<ResModel<GetControversyTypeListResultModel>>() {
            @Override
            public void onResponse(Call<ResModel<GetControversyTypeListResultModel>> call, Response<ResModel<GetControversyTypeListResultModel>> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel<GetControversyTypeListResultModel>> call, Throwable t) {
                //  提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });
    }

    public static void getContentWithLawById(String id,final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit().create(RemoteService.class);
        Call<ResModel<GetLabourLawContentByIdResultModel>> call = service.getLabourLawContent(id);

        call.enqueue(new Callback<ResModel<GetLabourLawContentByIdResultModel>>() {
            @Override
            public void onResponse(Call<ResModel<GetLabourLawContentByIdResultModel>> call, Response<ResModel<GetLabourLawContentByIdResultModel>> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel<GetLabourLawContentByIdResultModel>> call, Throwable t) {
                //  提示网络请求失败
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });


    }

    public static void getLocalContentWithLawById(String id, final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit().create(RemoteService.class);
        Call<ResModel<GetLabourLawContentByIdResultModel>> call = service.getLocalContentWithLawById(id);
        call.enqueue(new Callback<ResModel<GetLabourLawContentByIdResultModel>>() {
            @Override
            public void onResponse(Call<ResModel<GetLabourLawContentByIdResultModel>> call, Response<ResModel<GetLabourLawContentByIdResultModel>> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel<GetLabourLawContentByIdResultModel>> call, Throwable t) {
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });

    }

    public static void loadLawListByRegionId(String mCityId, final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit().create(RemoteService.class);
        Call<ResModel<GetLabourLawListWithRegionIdResultModel>> call = service.loadLawListByRegionId(mCityId);
         call.enqueue(new Callback<ResModel<GetLabourLawListWithRegionIdResultModel>>() {
             @Override
             public void onResponse(Call<ResModel<GetLabourLawListWithRegionIdResultModel>> call, Response<ResModel<GetLabourLawListWithRegionIdResultModel>> response) {
                 callback.onDataLoaded(response.body());
             }

             @Override
             public void onFailure(Call<ResModel<GetLabourLawListWithRegionIdResultModel>> call, Throwable t) {
                 callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                 call.cancel();
             }
         });


    }

    public static void loadCaseListByType(String caseType,final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit().create(RemoteService.class);
        Call<ResModel<GetCaseListWithTypeResultModel>> call = service.loadCaseListByType(caseType);
        call.enqueue(new Callback<ResModel<GetCaseListWithTypeResultModel>>() {
            @Override
            public void onResponse(Call<ResModel<GetCaseListWithTypeResultModel>> call, Response<ResModel<GetCaseListWithTypeResultModel>> response) {
                callback.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<ResModel<GetCaseListWithTypeResultModel>> call, Throwable t) {
                callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
                call.cancel();
            }
        });

    }

    public static void loadCaseContentById(String id,final DataSource.Callback<ResModel> callback) {
        RemoteService service = NetWork.getRetrofit().create(RemoteService.class);
        Call<ResModel<GetLabourLawContentByIdResultModel>> call = service.loadCaseContentById(id);
       call.enqueue(new Callback<ResModel<GetLabourLawContentByIdResultModel>>() {
           @Override
           public void onResponse(Call<ResModel<GetLabourLawContentByIdResultModel>> call, Response<ResModel<GetLabourLawContentByIdResultModel>> response) {
               callback.onDataLoaded(response.body());
           }

           @Override
           public void onFailure(Call<ResModel<GetLabourLawContentByIdResultModel>> call, Throwable t) {
               callback.onDtaNotAvailable(Factory.app().getString(R.string.data_network_error));
               call.cancel();
           }
       });
    }
}