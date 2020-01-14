package com.zfb.zhifabao.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Activity;
import com.zfb.zhifabao.flags.contract.ContractReviewFragment;
import com.zfb.zhifabao.flags.main.CommonTrigger;
import com.zfb.zhifabao.helper.NavHelper;

public class ContractReviewActivity extends Activity implements CommonTrigger, Common.Constance {
    private NavHelper mHelper;

    public static void show(Context context) {
        context.startActivity(new Intent(context, ContractReviewActivity.class));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_contract_review;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        setStatuTrans();
        mHelper = new NavHelper(this, getSupportFragmentManager(), R.id.lay_contract_container);
        mHelper.add(TO_CONTRACT_REVIEW_FRAGMENT, new NavHelper.Tab(ContractReviewFragment.class, ContractReviewFragment.class.getName()));
        mHelper.performanceTab(Common.Constance.TO_CONTRACT_REVIEW_FRAGMENT);
    }

    /**
     * 这是状态栏透明的方法
     */
    private void setStatuTrans() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public void triggerView(int flags) {
        mHelper.performanceTab(flags);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
