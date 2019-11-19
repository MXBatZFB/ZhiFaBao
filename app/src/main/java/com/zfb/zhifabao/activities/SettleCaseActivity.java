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
import com.zfb.zhifabao.flags.law.LookFragment;
import com.zfb.zhifabao.flags.law.SettleCasesFragment;
import com.zfb.zhifabao.helper.NavHelper;

public class SettleCaseActivity extends Activity implements AccountTrigger, Common.Constance {
    private NavHelper mNavHelper;
    public static void show(Context context) {
        context.startActivity(new Intent(context, SettleCaseActivity.class));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_settle_case;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        setStatuTrans();

        mNavHelper = new NavHelper(this, getSupportFragmentManager(), R.id.lay_settle_cases_container);
        mNavHelper.add(TO_LOOK_LAW_FRAGMENT, new NavHelper.Tab(LookFragment.class, LookFragment.class.getName()))
                .add(Common.Constance.TO_SETTLE_CASES_FRAGMENT, new NavHelper.Tab(SettleCasesFragment.class, SettleCasesFragment.class.getName()));
        mNavHelper.performanceTab(Common.Constance.TO_SETTLE_CASES_FRAGMENT);
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
    public void triggerView(int flags) {
        mNavHelper.performanceTab(flags);
    }
}
