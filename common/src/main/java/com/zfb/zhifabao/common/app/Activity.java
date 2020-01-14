package com.zfb.zhifabao.common.app;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import butterknife.ButterKnife;
import androidx.fragment.app.Fragment;

public abstract class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在界面初始化之前初始化窗口
        initWindows();

        if (initArgs(getIntent().getExtras())){
            //设置当前Activity的视图
            setContentView(getContentLayoutId());
            //初始化控件
            initWidget();
            //初始化数据
            initData();
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
    protected void initData() {

    }

    /**
     * 得到当前界面的资源ID
     * @return 资源id
     */
    protected abstract int  getContentLayoutId();

    /**
     * 初始化控件
     */
    protected  void initWidget(){
        ButterKnife.bind(this);
      //setStatuTrans();

    }

    /**
     * 初始化参数
     * @param Bundle Bundle
     * @return boolean
     */
    @SuppressWarnings("unused")
    protected boolean initArgs(Bundle Bundle) {
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
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments.size() > 0){
            for (Fragment fragment:fragments){
                if (fragment instanceof com.zfb.zhifabao.common.app.Fragment){
                    Log.e("DELONG","LAILE");
                    if (((com.zfb.zhifabao.common.app.Fragment) fragment).onBackPressed()){
                        return;
                    }
                }
            }
        }
        super.onBackPressed();
        finish();
    }
}
