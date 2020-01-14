package com.zfb.zhifabao.common.factory.presenter.consultation;

import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.law.GetRegionResultModel;
import com.zfb.zhifabao.common.factory.presenter.BaseContract;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public interface ConsultationContract extends BaseContract {

    interface View extends BaseContract.View<Presenter> {
        void onLoadProvinceSuccess(ResModel result);

        void onLoadCitySuccess(ResModel resModel);

        void onLoadCategoryOfLabourLawSuccess(ResModel resModel);

        void onLoadLawListWithTypeSuccess(ResModel resModel);

        void onLoadControversyTypeListSuccess(ResModel resModel);

        void onLoadLawListByRegionIdSuccess(ResModel resModel);

        void onLoadCaseListByTypeSuccess(ResModel resModel);
    }

    interface Presenter extends BaseContract.Presenter {
        //获取省份/地区信息
        void loadProvinceList();
        //获取对应省或者地区的市区或者区域
        void loadCityList(String id);
        //获取相关劳动法的种类
        void loadCategoryOfLabourLawList();
        //根据相关法律的类型获取对应的具体数据
        void loadLawListByType(String lawType);
        //获取争议类型列表
        void loadControversyTypeList();
        //根据地域Id获取法律列表
        void loadLawListByRegionId(String mCityId);
         //根据类型获取相关案例列表
        void loadCaseListByType(String caseType);
    }
}
