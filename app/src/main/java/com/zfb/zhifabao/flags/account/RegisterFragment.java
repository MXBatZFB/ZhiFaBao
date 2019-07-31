package com.zfb.zhifabao.flags.account;

import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.zfb.zhifabao.MainActivity;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.presenter.account.RegisterPresenter;
import com.zfb.zhifabao.common.factory.presenter.account.RegisterContract;
import net.qiujuer.genius.ui.widget.Loading;
import java.util.Objects;
import butterknife.BindView;
import butterknife.OnClick;

public class RegisterFragment extends PresenterFragment<RegisterContract.Presenter>
        implements RegisterContract.View,
        Common.Constance,
        CallbackOnRegister {
    private AccountTrigger mAccountTrigger;
    private String phone;
    private String code;
    @BindView(R.id.et_first_pwd)
    EditText et_first_pwd;
    @BindView(R.id.et_second_pwd)
    EditText et_second_pwd;
    @BindView(R.id.loading)
    Loading loading;
    @BindView(R.id.im_show_pwd)
    ImageView im_show_pwd;

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
                        et_first_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        et_second_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        im_show_pwd.setImageResource(R.drawable.ic_off);
                        et_first_pwd.setSelection(et_first_pwd.getText().length());
                        et_second_pwd.setSelection(et_second_pwd.getText().length());
                        break;
                    case MotionEvent.ACTION_DOWN:
                        et_first_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        et_second_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        im_show_pwd.setImageResource(R.drawable.ic_on);
                        et_first_pwd.setSelection(et_first_pwd.getText().length());
                        et_second_pwd.setSelection(et_second_pwd.getText().length());
                        break;
                }
                return false;
            }
        });
    }

    public RegisterFragment() {
    }

    @OnClick(R.id.btn_go_login)
    void onToLogin() {
        String first = et_first_pwd.getText().toString().trim();
        String second = et_second_pwd.getText().toString().trim();
        mPresenter.register(code, phone, first, second);
    }

    @OnClick(R.id.im_back)
    void onBack() {
        mAccountTrigger.triggerView(TO_MSG_LOGIN_FLAGS);
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
    public void loginSuccess() {
        MainActivity.show(Objects.requireNonNull(getContext()));
        Objects.requireNonNull(getActivity()).finish();
    }


    @Override
    public void onRegister(String phone, String code) {
        this.phone = phone;
        this.code = code;
    }
}
