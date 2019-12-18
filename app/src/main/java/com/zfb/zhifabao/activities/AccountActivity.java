package com.zfb.zhifabao.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Activity;
import com.zfb.zhifabao.flags.account.AccountTrigger;
import com.zfb.zhifabao.flags.account.LoginFragment;
import com.zfb.zhifabao.flags.account.MsgGetCodeFragment;
import com.zfb.zhifabao.flags.account.NextFragment;
import com.zfb.zhifabao.flags.account.RegisterFragment;
import com.zfb.zhifabao.helper.NavHelper;

@SuppressWarnings("unchecked")
public class AccountActivity extends Activity implements AccountTrigger, Common.Constance {
    private NavHelper mHelper;

    public static void show(Context context) {
        context.startActivity(new Intent(context, AccountActivity.class));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_account;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initWidget() {
        super.initWidget();
        setStatuTrans();
        mHelper = new NavHelper(this, getSupportFragmentManager(), R.id.account_container);
        mHelper.add(TO_LOGIN_FLAGS, new NavHelper.Tab(LoginFragment.class, "LoginFragment"))
                .add(TO_MSG_LOGIN_FLAGS, new NavHelper.Tab(MsgGetCodeFragment.class, "MsgLoginFragment"))
                .add(TO_REGISTER_FLAGS, new NavHelper.Tab(RegisterFragment.class, "RegisterFragment"))
                .add(TO_NEXT_MSG_LOGIN, new NavHelper.Tab(NextFragment.class, "NextFragment"));
        mHelper.performanceTab(Common.Constance.TO_LOGIN_FLAGS);
    }

    @Override
    public void triggerView(int flags) {
        mHelper.performanceTab(flags);
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
}
