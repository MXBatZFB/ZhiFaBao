package com.zfb.zhifabao.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Activity;
import com.zfb.zhifabao.flags.account.AccountTrigger;
import com.zfb.zhifabao.flags.law.consultation.ConsultationFragment;
import com.zfb.zhifabao.flags.law.consultation.ShowLawListFragment;
import com.zfb.zhifabao.helper.NavHelper;

public class ConsultationActivity extends Activity implements AccountTrigger, Common.Constance {
    private NavHelper mHelper;

    public static void show(Context context) {
        context.startActivity(new Intent(context, ConsultationActivity.class));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_consultation;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        setStatuTrans();
        mHelper = new NavHelper(this, getSupportFragmentManager(), R.id.lay_consultation_container);
        mHelper.add(TO_CONSULTATION_FRAGMENT, new NavHelper.Tab(ConsultationFragment.class, "ConsultationFragment"));
        mHelper.performanceTab(Common.Constance.TO_CONSULTATION_FRAGMENT);
    }


    /**
     * 这是状态栏透明的方法
     */
    private void setStatuTrans() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void triggerView(int flags) {
        mHelper.performanceTab(flags);
    }
}
