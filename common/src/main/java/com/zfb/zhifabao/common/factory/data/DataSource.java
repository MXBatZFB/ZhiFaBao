package com.zfb.zhifabao.common.factory.data;


public interface DataSource {

    /**
     * 关注成功和失败的总接口
     * @param <T>
     */
    interface Callback<T> extends SucceedCallback<T> ,FailedCallback{
        void onDataLoaded(T result);
        void onDtaNotAvailable(String msg);
    }

    interface ValidCallback{
        void isValid(String phone ,String password);
        void isInvalid(String str);
    }


    /**
     * 只关注成功的回调接口
     * @param <T> 处理的数据类型
     */
    interface  SucceedCallback<T>{
        void onDataLoaded(T result);
    }

    /**
     * 只关注失败的回调接口
     */
    interface FailedCallback{
        void onDtaNotAvailable(String strRes);
    }


}
