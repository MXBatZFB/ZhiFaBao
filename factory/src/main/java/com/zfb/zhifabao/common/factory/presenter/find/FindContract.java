package com.zfb.zhifabao.common.factory.presenter.find;

import com.zfb.zhifabao.common.factory.model.api.find.FindContent;
import com.zfb.zhifabao.common.factory.presenter.BaseContract;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public interface FindContract extends BaseContract {

    interface View<InitModel> extends BaseContract.RecyclerView<Presenter, FindContent> {
        void onInit(InitModel model);
    }

    interface Presenter extends BaseContract.Presenter {
        void getFindList(String token);
    }
}
