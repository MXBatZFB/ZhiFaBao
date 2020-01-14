package com.zfb.zhifabao.common.factory.presenter.consultation;

import com.zfb.zhifabao.common.factory.data.ConsultationHelper;
import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetCaseListWithTypeResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetCategoryOfLabourLawResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetControversyTypeListResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLabourLawListWithRegionIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLabourLawListWithTypeResultModel;
import com.zfb.zhifabao.common.factory.model.api.law.GetCityWithProvinceIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.law.GetRegionResultModel;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class ConsultationPresenter extends BasePresenter<ConsultationContract.View>
        implements ConsultationContract.Presenter, DataSource.Callback<ResModel> {
    private int loadType;

    public ConsultationPresenter(ConsultationContract.View mView) {
        super(mView);
    }

    @Override
    public void onDataLoaded(ResModel result) {
        if (result.getData() instanceof GetRegionResultModel){
            getmView().onLoadProvinceSuccess( result);
        }else if (result.getData() instanceof GetCityWithProvinceIdResultModel){
            getmView().onLoadCitySuccess( result);
        }else if (result.getData() instanceof GetCategoryOfLabourLawResultModel){
            getmView().onLoadCategoryOfLabourLawSuccess(result);
        }else if (result.getData() instanceof GetLabourLawListWithTypeResultModel ||result.getData() instanceof GetLabourLawListWithRegionIdResultModel){
            getmView().onLoadLawListWithTypeSuccess(result);
        }else if (result.getData() instanceof GetControversyTypeListResultModel){
            getmView().onLoadControversyTypeListSuccess(result);
        }else if (result.getData() instanceof GetCaseListWithTypeResultModel){
            getmView().onLoadCaseListByTypeSuccess(result);
        }
    }

    @Override
    public void onDtaNotAvailable(String msg) {
        getmView().showError(msg);
    }

    /**
     * 获取省份/地区列表
     */
    @Override
    public void loadProvinceList() {
        ConsultationHelper.getProvinceList(this);
    }

    /**
     * 获取城市列表
     * @param id
     */
    @Override
    public void loadCityList(String id) {
        ConsultationHelper.getCityList(id,this);
    }

    /**
     * 获取劳动法种类
     */
    @Override
    public void loadCategoryOfLabourLawList() {
        ConsultationHelper.getCategoryOfLabourLawList(this);
    }

    @Override
    public void loadLawListByType(String lawType) {
        ConsultationHelper.getLabourLawListByType(lawType,this);
    }


    @Override
    public void loadControversyTypeList() {
        ConsultationHelper.getControversyTypeList(this);
    }

    @Override
    public void loadLawListByRegionId(String mCityId) {
        ConsultationHelper.loadLawListByRegionId(mCityId,this);
    }

    @Override
    public void loadCaseListByType(String caseType) {
        ConsultationHelper.loadCaseListByType(caseType,this);
    }
}
