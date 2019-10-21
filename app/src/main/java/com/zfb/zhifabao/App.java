package com.zfb.zhifabao;

import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.factory.Factory;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Factory.setup();
    }
}
