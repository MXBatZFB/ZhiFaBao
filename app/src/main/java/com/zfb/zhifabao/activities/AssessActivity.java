package com.zfb.zhifabao.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.app.Activity;
import com.zfb.zhifabao.common.app.Fragment;
import com.zfb.zhifabao.common.factory.model.api.consultation.TestBean;
import com.zfb.zhifabao.flags.assess.AssessFragment;
import com.zfb.zhifabao.flags.main.CommonTrigger;

@RequiresApi(api = Build.VERSION_CODES.M)
public class AssessActivity extends Activity implements CommonTrigger {
    private static TestBean mTestBean;
    private static int mAssessType;
    private Fragment curFragment;

    public static void show(Context context, TestBean testBean, int mType) {
        mTestBean = testBean;
        mAssessType = mType;
        context.startActivity(new Intent(context, AssessActivity.class));
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_assess;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        setStatuTrans();
        curFragment = new AssessFragment(mTestBean, mAssessType);
        getSupportFragmentManager().beginTransaction().add(R.id.lay_assess_container, curFragment).commit();
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
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

    @Override
    public void triggerView(int flags) {

    }
}
