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
import com.zfb.zhifabao.flags.main.CommonTrigger;
import com.zfb.zhifabao.flags.message.MessageCenterFragment;
import com.zfb.zhifabao.helper.NavHelper;

public class ZFBMessageActivity extends Activity {
    private NavHelper mHelper;

    public static void show(Context context) {
        context.startActivity(new Intent(context, ZFBMessageActivity.class));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_zfbmessage;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        setStatusTrans();
        mHelper = new NavHelper(this, getSupportFragmentManager(), R.id.message_container);
        mHelper.add(Common.Constance.FIND_MESSAGE,new NavHelper.Tab(MessageCenterFragment.class,MessageCenterFragment.class.getName()));
        mHelper.performanceTab(Common.Constance.FIND_MESSAGE);
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
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }


}
