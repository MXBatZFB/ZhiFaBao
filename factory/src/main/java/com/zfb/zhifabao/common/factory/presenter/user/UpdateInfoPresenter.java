package com.zfb.zhifabao.common.factory.presenter.user;

import android.text.TextUtils;

import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.data.UploaderHelper;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.account.UserInfo;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;

public class UpdateInfoPresenter extends BasePresenter<UpdateInfoContract.view>
        implements UpdateInfoContract.presenter,
        DataSource.Callback<ResModel<UserInfo>> {
    public UpdateInfoPresenter(UpdateInfoContract.view mView) {
        super(mView);
    }

    @Override
    public void onDataLoaded(ResModel<UserInfo> result) {
        if (result.getCode() == 6234) {
            getmView().updateSuccess();
        } else {
            getmView().showError(result.getMessage());
        }
    }

    @Override
    public void onDtaNotAvailable(String msg) {
        getmView().showError(msg);
    }

    @Override
    public void update(String userName, String companyName, String photoFilePath) {
        start();
        final UpdateInfoContract.view view = getmView();
        if (view == null)
            return;
        if (TextUtils.isEmpty(photoFilePath) || TextUtils.isEmpty(userName) || TextUtils.isEmpty(companyName)) {
            view.showError("请完善信息，基本信息不能为空！");
        } else {
            UploaderHelper.update(userName, companyName, photoFilePath, this);
        }
    }

}
