package com.zfb.zhifabao.common.app;

import android.content.Context;

import com.zfb.zhifabao.common.factory.presenter.BaseContract;

public abstract class PresenterFragment<Presenter extends BaseContract.Presenter> extends Fragment
                                                       implements BaseContract.View<Presenter>  {
   protected  Presenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initPresenter();
    }

    protected  abstract Presenter initPresenter();

    @Override
    public void showError(String str) {
        Application.showToast(str);
    }

    @Override
    public void showLoding() {

    }

    @Override
    public void setPresenter(Presenter presenter) {
             mPresenter = presenter;
    }
}
