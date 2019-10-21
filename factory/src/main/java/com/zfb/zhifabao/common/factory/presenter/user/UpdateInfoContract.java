package com.zfb.zhifabao.common.factory.presenter.user;

import com.zfb.zhifabao.common.factory.presenter.BaseContract;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public interface UpdateInfoContract {

    interface presenter extends BaseContract.Presenter {
        void update(String userName, String companyName, String photoFilePath);
    }

    interface view extends BaseContract.View<presenter> {
        void updateSuccess();
    }
}
