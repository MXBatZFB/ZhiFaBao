package com.zfb.zhifabao.flags.account;


import android.content.Context;
import android.util.Log;
import android.view.View;

import com.zfb.zhifabao.MainActivity;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.presenter.account.LoginContract;
import com.zfb.zhifabao.common.factory.presenter.account.LoginPresenter;
import com.zfb.zhifabao.common.widget.account.VerificationCodeEditText;

import net.qiujuer.genius.ui.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;

public class NextFragment extends PresenterFragment<LoginContract.Presenter> implements Common.Constance, LoginContract.View {
    private static String mPhone = "";
    @BindView(R.id.et_code)
    VerificationCodeEditText codeEditText;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private AccountTrigger mAccountTrigger;

    public NextFragment() {
    }

    public static void setPhone(String phone) {
        mPhone = phone;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    protected LoginContract.Presenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        Log.e("delong", "mPhone>>>>>>>" + mPhone);
        codeEditText.setOnInputListener(new VerificationCodeEditText.OnInputListener() {
            @Override
            public void OnEdittextChange(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 6) {
                    btnLogin.setEnabled(true);
                } else {
                    btnLogin.setEnabled(false);
                }
            }

            @Override
            public void OnInputOk(String codeNum) {

            }
        });
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_msg_next_login;
    }

    @OnClick(R.id.btn_login)
    void login() {
        Log.e("delong", "mPhone>>>>>>>" + mPhone);
        if (mPhone.length() > 0) {
            String code = codeEditText.getCode();
            mPresenter.msgLogin(mPhone, code);
        }
    }

    @Override
    public void loginSuccess() {
        MainActivity.show(getActivity());
        getActivity().finish();
    }
}
