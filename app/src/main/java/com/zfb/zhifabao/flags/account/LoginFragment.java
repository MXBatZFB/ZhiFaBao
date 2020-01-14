package com.zfb.zhifabao.flags.account;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.zfb.zhifabao.MainActivity;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.presenter.account.LoginContract;
import com.zfb.zhifabao.common.factory.presenter.account.LoginPresenter;

import net.qiujuer.genius.ui.widget.Loading;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends PresenterFragment<LoginContract.Presenter>
                                           implements LoginContract.View,
                                            Common.Constance {
    private AccountTrigger mAccountTrigger;
    @BindView(R.id.btn_submit_login)
    Button btn_submit_login;

    @BindView(R.id.et_password)
    EditText et_password;

    @BindView(R.id.et_mobile)
    EditText et_mobile;

    @BindView(R.id.im_show_pwd)
    ImageView im_show_pwd;

    @BindView(R.id.fl_by_weiixn_login)
    FrameLayout fl_by_wechat_login;

    @BindView(R.id.btn_by_msg_login)
    Button btn_by_msg_login;

    @BindView(R.id.loading)
    Loading loading;
    private boolean phoneIsNull = true;
    private boolean pwdIsNull = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAccountTrigger = (AccountTrigger) context;
    }

    public LoginFragment() {
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    pwdIsNull = false;
                    if (!phoneIsNull) {
                        btn_submit_login.setEnabled(true);
                    } else {
                        btn_submit_login.setEnabled(false);
                    }
                } else {
                    pwdIsNull = true;
                    btn_submit_login.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    phoneIsNull = false;
                    if (!pwdIsNull) {
                        btn_submit_login.setEnabled(true);
                    } else {
                        btn_submit_login.setEnabled(false);
                    }
                } else {
                    phoneIsNull = true;
                    btn_submit_login.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


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
    }

    @OnClick(R.id.btn_submit_login)
    void login() {
        String phone = et_mobile.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        mPresenter.login(phone, password);
    }

    @OnClick(R.id.go_register)
    void onRegister() {
        mAccountTrigger.triggerView(TO_REGISTER_FLAGS);
    }

    @OnClick(R.id.go_login)
    void onLogin() {
        mAccountTrigger.triggerView(TO_LOGIN_FLAGS);
    }

    @OnClick(R.id.btn_by_msg_login)
    void onByMessageLogin() {
        mAccountTrigger.triggerView(Common.Constance.TO_MSG_LOGIN_FLAGS);
    }

    @OnClick(R.id.fl_by_weiixn_login)
    void onByWeChatLogin() {

    }


    @OnClick(R.id.im_back)
    void onBack() {
        getActivity().finish();
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void loginSuccess() {
        MainActivity.show(getActivity());
        getActivity().finish();
    }

    @Override
    public void showError(String str) {
        super.showError(str);
        loading.stop();
        btn_submit_login.setClickable(true);
        im_show_pwd.setClickable(true);
        btn_by_msg_login.setClickable(true);
        fl_by_wechat_login.setClickable(true);
    }

    @Override
    public void showLoding() {
        super.showLoding();
        loading.start();
        btn_submit_login.setClickable(false);
        im_show_pwd.setClickable(false);
        btn_by_msg_login.setClickable(false);
        fl_by_wechat_login.setClickable(false);
    }
}
