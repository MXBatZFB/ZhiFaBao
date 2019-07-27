package com.zfb.zhifabao.activities;

import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.view.WindowManager;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.app.Activity;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class AccountActivity extends Activity {
    @BindView(R.id.bg_login)
    CircleImageView circleImageView;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_account;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initWidget() {
        super.initWidget();
        setStatuTrans();
        circleImageView.setClipToOutline(true);
        circleImageView.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(new Rect(0, 0, view.getHeight(), view.getHeight()));
            }
        });
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
