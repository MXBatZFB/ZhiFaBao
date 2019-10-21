package com.zfb.zhifabao.common.factory;

import android.support.annotation.StringRes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.factory.data.DataSource;
import com.zfb.zhifabao.common.factory.persistence.Account;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
public class Factory {
    // 单例模式
    private static final Factory instance;
    // 全局的线程池
    private final Executor executor;
    // 全局的Gson
    private final Gson gson;


    static {
        instance = new Factory();
    }

    private Factory() {
        // 新建一个4个线程的线程池
        executor = Executors.newFixedThreadPool(4);
        gson = new GsonBuilder()
                // 设置时间格式
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                // 设置一个过滤器，数据库级别的Model不进行Json转换
              //  .setExclusionStrategies(new DBFlowExclusionStrategy())
                .create();
    }

    public static void setup() {
        //FlowManager.init(new FlowConfig.Builder(app()).openDatabasesOnInit(true).build());
        Account.load(app());
    }

    /**
     * 返回全局的Application
     *
     * @return Application
     */
    public static Application app() {
        return Application.getInstance();
    }


    /**
     * 异步运行的方法
     *
     * @param runnable Runnable
     */
    @SuppressWarnings("unused")
    public static void runOnAsync(Runnable runnable) {
        // 拿到单例，拿到线程池，然后异步执行
        instance.executor.execute(runnable);
    }

    /**
     * 返回一个全局的Gson，在这可以进行Gson的一些全局的初始化
     *
     * @return Gson
     */
    public static Gson getGson() {
        return instance.gson;
    }


    @SuppressWarnings("unused")
    private static void decodeRspCode(@StringRes final int resId,
                                      final DataSource.FailedCallback callback) {
        if (callback != null)
            callback.onDtaNotAvailable(app().getString(resId));
    }

}
