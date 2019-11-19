package com.zfb.zhifabao.common.app;

import com.zfb.zhifabao.common.factory.presenter.BaseContract;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public abstract class PresenterActivity<Presenter extends BaseContract.Presenter> extends Activity implements BaseContract.View<Presenter> {
    protected Presenter mPresenter;

    @Override
    protected void initWidget() {
        super.initWidget();
        initPresenter();
    }

    protected abstract Presenter initPresenter();

    @Override
    public void showError(String str) {
        Application.showToast(str);
    }

    @Override
    public void showLoding() {
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.mPresenter = presenter;
    }
}
