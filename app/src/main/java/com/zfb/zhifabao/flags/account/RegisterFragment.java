package com.zfb.zhifabao.flags.account;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.presenter.account.RegisterContract;
import com.zfb.zhifabao.common.factory.presenter.account.RegisterPresenter;
import com.zfb.zhifabao.common.widget.app.TimCount;

import net.qiujuer.genius.ui.widget.Loading;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterFragment extends PresenterFragment<RegisterContract.Presenter>
        implements RegisterContract.View,
        Common.Constance, TextWatcher {
    private AccountTrigger mAccountTrigger;
    @BindView(R.id.et_mobile)
    EditText et_phone;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.loading)
    Loading loading;
    @BindView(R.id.im_show_pwd)
    ImageView im_show_pwd;
    @BindView(R.id.btn_get_code)
    Button btn_get_code;
    @BindView(R.id.btn_submit_register)
    Button btn_submit_register;
    private TimCount mTimCount;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    public void showError(String str) {
        super.showError(str);
        loading.stop();
    }


    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        im_show_pwd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        im_show_pwd.setImageResource(R.drawable.ic_off);
                        et_password.setSelection(et_password.getText().length());
                        break;
                    case MotionEvent.ACTION_DOWN:
                        et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        im_show_pwd.setImageResource(R.drawable.ic_on);
                        et_password.setSelection(et_password.getText().length());
                        break;
                }
                return false;
            }
        });

        et_phone.addTextChangedListener(this);
        et_password.addTextChangedListener(this);
        et_code.addTextChangedListener(this);

        mTimCount = new TimCount(60000, 1000);
        mTimCount.setView(btn_get_code);
    }

    public RegisterFragment() {
    }

    @OnClick(R.id.btn_get_code)
    void getCode() {
        String phone = et_phone.getText().toString().trim();
        mPresenter.getRegisterCode(phone);
    }

    @OnClick(R.id.go_register)
    void onRegister() {
        mAccountTrigger.triggerView(TO_REGISTER_FLAGS);
    }

    @OnClick(R.id.go_login)
    void onLogin() {
        mAccountTrigger.triggerView(TO_LOGIN_FLAGS);
    }


    @OnClick(R.id.btn_submit_register)
    void onToRegister() {
        String code = et_code.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String phone = et_phone.getText().toString().trim();
        mPresenter.register(code, phone, password);
    }

    @OnClick(R.id.im_back)
    void onBack() {
        getActivity().finish();
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected RegisterContract.Presenter initPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    public void registerSuccess() {
        mAccountTrigger.triggerView(TO_LOGIN_FLAGS);
    }

    @Override
    public void getCodeSuccess() {
        Application.showToast("验证码获取成功！");
    }

    @Override
    public void starTimer() {
        mTimCount.start();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (et_phone.getText().toString().length() == 11
                && et_code.getText().toString().length() == 6
                && et_password.getText().toString().length() > 0) {
            btn_submit_register.setEnabled(true);
        } else {
            btn_submit_register.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
