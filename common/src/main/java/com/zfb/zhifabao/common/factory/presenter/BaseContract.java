package com.zfb.zhifabao.common.factory.presenter;

import com.zfb.zhifabao.common.widget.cyclerview.RecyclerAdapter;

public interface BaseContract {

    interface  View<T extends Presenter>{
        //显示错误消息
        void showError(String str);
        //显示加载进度条
        void showLoding();
        //设置一个presenter
        void setPresenter(T presenter);
    }

    interface Presenter{
        //公共的开始触发
        void  start();
        //公用的销毁触发
        void destroy();
    }

    interface RecyclerView<T extends Presenter, ViewModel> extends View<T> {
        RecyclerAdapter<ViewModel> getAdapter();

        void onAdapterDataChange();
    }

}
