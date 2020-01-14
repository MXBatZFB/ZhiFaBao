package com.zfb.zhifabao;

import android.util.Log;

import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.factory.Factory;
import cn.jpush.android.api.JPushInterface;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String registerId = JPushInterface.getRegistrationID(Factory.app());
        Log.e("delong","registerId=========================>"+registerId);
         Factory.setup();
    }
}
