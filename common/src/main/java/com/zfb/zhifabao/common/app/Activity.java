package com.zfb.zhifabao.common.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在界面初始化之前初始化窗口
        initWindows();

        if (inintArgs(getIntent().getExtras())){
            //设置当前Activity的视图
            setContentView(getContentLayoutId());
            //初始化控件
            inintWidget();
            //初始化数据
            inintData();
        }else{
            finish();
        }

    }

    /**
     * 初始化窗口
     */
    protected  void initWindows(){

    }

    /**
     * 初始化数据
     */
    protected void inintData() {

    }

    /**
     * 得到当前界面的资源ID
     * @return 资源id
     */
    protected abstract int  getContentLayoutId();

    /**
     * 初始化控件
     */
    protected  void inintWidget(){


    }

    /**
     * 初始化参数
     * @param Bundle
     * @return
     */
    protected boolean inintArgs(Bundle Bundle){
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        //当界面导航返回时，finsh界面
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();
    }



}
