package com.zfb.zhifabao.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.app.PresenterActivity;
import com.zfb.zhifabao.common.factory.model.api.assess.SubmitResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.TestBean;
import com.zfb.zhifabao.common.factory.presenter.assess.AssessContract;
import com.zfb.zhifabao.common.factory.presenter.assess.AssessPresenter;
import com.zfb.zhifabao.common.utils.CheckUtils;

import androidx.annotation.RequiresApi;
import butterknife.BindView;
import butterknife.OnClick;

public class CareerQuizActivity extends PresenterActivity<AssessContract.Presenter> implements AssessContract.View, Common.Constance {

    private int mType;
    @BindView(R.id.et_epName)
    EditText epName;
    @BindView(R.id.ep_id)
    EditText epId;
    private String name;
    private String id;


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


    //测试题类型 0:职能 1:法律 2:心理
    @OnClick(R.id.im_law)
    void onDoLawAssess() {
        if (checkEmployeeInfo()){
            mType = ASSESS_TYPE_LAW;
            mPresenter.getAssessQuestion("1");
        }

    }

    //测试题类型 0:职能 1:法律 2:心理
    @OnClick(R.id.im_function)
    void onDoFunctionAssess() {
        if (checkEmployeeInfo()){
            mType = ASSESS_TYPE_FUNCTION;
            mPresenter.getAssessQuestion("0");
        }

    }

    //测试题类型 0:职能 1:法律 2:心理
    @OnClick(R.id.im_psychology)
    void onDoPsychologyAssess() {
        if (checkEmployeeInfo()){
            mType = ASSESS_TYPE_PSYCHOLOGY;
            mPresenter.getAssessQuestion("2");
        }

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

    private boolean checkEmployeeInfo(){
         name = epName.getText().toString().trim();
         id  = epId.getText().toString().trim();
        if (!(name.length()>0&&id.length()>0)){
            Application.showToast("请先输入测试者的信息！");
            return false;
        }
        if(!CheckUtils.isIDNumber(id)){
            Application.showToast("身份证号码不正确！");
            return false;
        }
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void doAssess(TestBean testBean) {
        Log.e("delong.","TestBean》》》》》》"+testBean);
        if (testBean != null) {
            testBean.setEmployeeBean(new SubmitResultModel.EmployeeBean(id,name));
            AssessActivity.show(this, testBean, mType);
        }

    }
}
