package com.zfb.zhifabao.common.factory.presenter;

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


}
