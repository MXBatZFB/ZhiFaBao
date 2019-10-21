package com.zfb.zhifabao.common.factory.presenter.find;

import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.data.FindHelper;
import com.zfb.zhifabao.common.factory.model.api.find.FindContent;
import com.zfb.zhifabao.common.factory.presenter.BaseRecyclerPresenter;

import java.util.List;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class FindPresenter extends BaseRecyclerPresenter<FindContent, FindContract.View>
        implements FindContract.Presenter,
        DataSource.Callback<List<FindContent>> {
    public FindPresenter(FindContract.View mView) {
        super(mView);
    }

    @Override
    protected void refreshData(List<FindContent> findContents) {
        super.refreshData(findContents);
    }

    @Override
    public void getFindList(String token) {
        FindHelper.getFindList(token, this);
    }

    @Override
    public void onDataLoaded(List<FindContent> result) {
        refreshData(result);
    }

    @Override
    public void onDtaNotAvailable(String msg) {

    }
}
