package com.zfb.zhifabao.common.factory.presenter.assess;

import android.util.Log;

import com.zfb.zhifabao.common.factory.data.AssessHelper;
import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.model.api.consultation.TestBean;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class AssessPresenter extends BasePresenter<AssessContract.View>
        implements AssessContract.Presenter, DataSource.Callback<TestBean> {

    public AssessPresenter(AssessContract.View mView) {
        super(mView);
    }

    @Override
    public void getAssessQuestion(String testType) {
        AssessHelper.getAssessQuestion(testType, this);
    }

    @Override
    public void onDataLoaded(TestBean result) {
        Log.e("AssessPresenter", result.toString());

        getmView().doAssess(result);
    }

    @Override
    public void onDtaNotAvailable(String msg) {
        getmView().showError(msg);
    }
}
