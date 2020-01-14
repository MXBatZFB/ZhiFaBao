package com.zfb.zhifabao.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Activity;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.presenter.look.LookContract;
import com.zfb.zhifabao.flags.law.consultation.LookContentWithLawFragment;
import com.zfb.zhifabao.flags.law.consultation.ShowLawListFragment;
import com.zfb.zhifabao.flags.main.CommonTrigger;
import com.zfb.zhifabao.helper.NavHelper;

public class LookListActivity extends Activity implements CommonTrigger {
    private NavHelper mHelper;
    public static   ResModel resModel;
    public static void show(Context context, ResModel model) {
        resModel = model;
        context.startActivity(new Intent(context, LookListActivity.class));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_look;
    }


    @Override
    protected void initWidget() {
        super.initWidget();
        setStatusTrans();
        mHelper = new NavHelper(this, getSupportFragmentManager(), R.id.look_list_container);
        mHelper.add(Common.Constance.TO_SHOW_LAW_LIST_FRAGMENT,new NavHelper.Tab(ShowLawListFragment.class,""))
                .add(Common.Constance.TO_LOOK_CONTENT_LAW_FRAGMENT,new NavHelper.Tab(LookContentWithLawFragment.class,""));
        ShowLawListFragment.setResult(resModel);
        mHelper.performanceTab(Common.Constance.TO_SHOW_LAW_LIST_FRAGMENT);

    }

    /**
     * 这是状态栏透明的方法
     */
    private void setStatusTrans() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            //SYSTEM_UI_FLAG_LAYOUT_STABLE   SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public void triggerView(int flags) {
          mHelper.performanceTab(flags);
    }
}
