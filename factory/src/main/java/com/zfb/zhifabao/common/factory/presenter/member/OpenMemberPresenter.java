package com.zfb.zhifabao.common.factory.presenter.member;

import android.util.Log;

import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.data.MemberHelper;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.member.OrderResultModel;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

public class OpenMemberPresenter extends BasePresenter<OpenMemberContract.View> implements OpenMemberContract.Presenter, DataSource.Callback<ResModel> {
    public OpenMemberPresenter(OpenMemberContract.View mView) {
        super(mView);
    }

    @Override
    public void createOrder(String price) {
        Log.e("delong","price>>>>>>>>>>>"+price);
        MemberHelper.createOrder(price,this);
    }

    @Override
    public void onDataLoaded(ResModel result) {
           getmView().createOrderSucceed((OrderResultModel) result.getData());
    }

    @Override
    public void onDtaNotAvailable(String msg) {

    }
}
