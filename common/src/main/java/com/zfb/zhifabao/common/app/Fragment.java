package com.zfb.zhifabao.common.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class Fragment extends android.support.v4.app.Fragment {
    protected Unbinder mUnbinder;
    protected View mRootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView ==null){
            int layId = getContentLayoutId();
            View rootView = inflater.inflate(layId,container,false);
            initWidget(rootView);
            mRootView = rootView;
        }else{
            if (mRootView.getParent()!=null){
                ((ViewGroup)(mRootView.getParent())).removeView(mRootView);
            }
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //当View创建完成时初始化数据
        initData();
    }

    /**
     * 初始化参数
     * @param Bundle Bundle
     *
     */
    @SuppressWarnings("unused")
    protected void initArgs(Bundle Bundle){

    }

    /**
     * 得到当前界面的资源ID
     * @return 资源id
     */
    protected abstract int  getContentLayoutId();

    /**
     * 初始化数据
     */
    private void initData() {

    }

    /**
     * 初始化控件
     */
    protected  void initWidget(View root){
        //绑定ButterKnife 注解框架
       mUnbinder = ButterKnife.bind(this,root);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder!=null){
            mUnbinder.unbind();
        }
    }

    /**
     * 返回键出发时调用
     * @return 返回True 表示已经处理返回逻辑，Activity  不用自己finsh
     * false 代表我没有处理 Activity走自己的逻辑
     */
    public boolean onBackPressed(){
        return false;
    }
}
