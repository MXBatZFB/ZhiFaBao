package com.zfb.zhifabao.common.factory.presenter;

public class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter{
    protected T mView;

    public BasePresenter(T mView) {
        setmView(mView);
    }

    protected final T getmView() {
        return mView;
    }

    protected void setmView(T mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }


    @Override
    public void start() {
        T view = mView;
        if (view!=null){
            view.showLoding();
        }

    }

    @Override
    public void destroy() {
        T view = mView;
        mView = null;
        if (view!=null) {
        view.setPresenter(null);
        }
    }
}
