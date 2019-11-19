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
import com.zfb.zhifabao.common.app.PresenterActivity;
import com.zfb.zhifabao.common.factory.model.api.consultation.TestBean;
import com.zfb.zhifabao.common.factory.presenter.assess.AssessContract;
import com.zfb.zhifabao.common.factory.presenter.assess.AssessPresenter;

import butterknife.OnClick;

public class CareerQuizActivity extends PresenterActivity<AssessContract.Presenter> implements AssessContract.View, Common.Constance {

    private int mType;

    public static void show(Context context) {
        context.startActivity(new Intent(context, CareerQuizActivity.class));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_career_quiz;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        setStatuTrans();
    }

    @Override
    protected AssessContract.Presenter initPresenter() {
        return new AssessPresenter(this);
    }

    @OnClick(R.id.im_law)
    void onDoLawAssess() {
        mType = ASSESS_TYPE_LAW;
        mPresenter.getAssessQuestion("JiBenFaCeShi");
    }

    @OnClick(R.id.im_function)
    void onDoFunctionAssess() {
        mType = ASSESS_TYPE_FUNCTION;
        mPresenter.getAssessQuestion("BaoXianCongYeRenYuanCeShi");
    }

    @OnClick(R.id.im_psychology)
    void onDoPsychologyAssess() {
        mType = ASSESS_TYPE_PSYCHOLOGY;
        mPresenter.getAssessQuestion("XinLiCeShi");
    }


    @OnClick(R.id.im_back)
    void onBack() {
        finish();
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
    public void doAssess(TestBean testBean) {
        if (testBean != null) {
            AssessActivity.show(this, testBean, mType);
        }

    }
}
