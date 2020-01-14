package com.zfb.zhifabao.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Property;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.zfb.zhifabao.MainActivity;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.app.Activity;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.factory.persistence.Account;
import com.zfb.zhifabao.permission.PermissionFragment;
import net.qiujuer.genius.res.Resource;
import net.qiujuer.genius.ui.compat.UiCompat;

public class LauncherActivity extends Activity implements PermissionFragment.OnSubmit {

    private ColorDrawable mBgDrawable;
    private Property<LauncherActivity, Object> property = new Property<LauncherActivity, Object>(Object.class, "color") {
        @Override
        public Object get(LauncherActivity object) {
            return object.mBgDrawable.getColor();
        }

        @Override
        public void set(LauncherActivity object, Object value) {
            object.mBgDrawable.setColor((Integer) value);
        }
    };

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_launcher;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void initWidget() {
        super.initWidget();
        setStatuTrans();
        View root = findViewById(R.id.activity_launch);
        int color = UiCompat.getColor(getResources(), R.color.colorPrimary);
        ColorDrawable drawable = new ColorDrawable(color);
        root.setBackground(drawable);
        mBgDrawable = drawable;
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
    protected void initData() {
        super.initData();
        startAnim(0.5f, new Runnable() {
            @Override
            public void run() {
                checkIsLogin();
            }
        });
    }

    private void checkIsLogin() {
        getWindow().getDecorView()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        skip();
                    }
                }, 1500);
    }

    /**
     * 在跳转之前需要把剩下的动画完成
     */
    private void skip() {
        startAnim(1f, new Runnable() {
            @Override
            public void run() {
                reallySkip();
            }
        });
    }

    private void reallySkip() {
        if (PermissionFragment.hasAll(this, getSupportFragmentManager(), this)) {
            if (Account.isLogin()) {
                MainActivity.show(this);
            } else {
                AccountActivity.show(this);
            }
            finish();
        }
    }

    @SuppressWarnings("unchecked")
    private void startAnim(float endProgress, final Runnable endCallback) {
        int finalColor = Resource.Color.WHITE;
        //运算当前进度的颜色
        ArgbEvaluator evaluator = new ArgbEvaluator();
        int endColor = (int) evaluator.evaluate(endProgress, mBgDrawable.getColor(), finalColor);
        ValueAnimator valueAnimator = ObjectAnimator.ofObject(this, property, evaluator, endColor);
        valueAnimator.setDuration(1500);
        valueAnimator.setIntValues(mBgDrawable.getColor(), endColor);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                endCallback.run();
            }
        });
        valueAnimator.start();
    }

    @Override
    public void onClickSubmit(boolean requestPermIsSucceed) {
        if (requestPermIsSucceed) {
            reallySkip();
        } else {
            Application.showToast("请完成授权，不然无法使用哦！");
        }
    }
}
