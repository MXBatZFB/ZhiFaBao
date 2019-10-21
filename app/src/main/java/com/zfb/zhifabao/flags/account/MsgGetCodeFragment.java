package com.zfb.zhifabao.flags.account;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.presenter.account.MsgGetCodeContract;
import com.zfb.zhifabao.common.factory.presenter.account.MsgGetCodePresenter;

import net.qiujuer.genius.ui.widget.Loading;

import butterknife.BindView;
import butterknife.OnClick;

@SuppressWarnings("unused")
public class MsgGetCodeFragment extends PresenterFragment<MsgGetCodeContract.Presenter>
        implements MsgGetCodeContract.View,
        Common.Constance {
    @BindView(R.id.et_mobile)
    EditText et_mobile;
    @BindView(R.id.btn_next)
    Button btn_submit;
    @BindView(R.id.fl_by_weiixn_login)
    FrameLayout fl_by_weiixn_login;
    @BindView(R.id.loading)
    Loading loading;
    private AccountTrigger mAccountTrigger;


    public MsgGetCodeFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        et_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    btn_submit.setEnabled(true);
                } else {
                    btn_submit.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_msg_login;
    }

    @Override
    protected MsgGetCodeContract.Presenter initPresenter() {
        return new MsgGetCodePresenter(this);
    }

    @Override
    public void showLoding() {
        super.showLoding();
        loading.start();
        btn_submit.setClickable(false);
        fl_by_weiixn_login.setClickable(false);
    }

    @OnClick(R.id.btn_next)
    void onNext() {
        mPresenter.getCode(et_mobile.getText().toString().trim());
    }


    @Override
    public void showError(String str) {
        super.showError(str);
        loading.stop();
        btn_submit.setClickable(true);
        fl_by_weiixn_login.setClickable(true);
    }

    @OnClick(R.id.btn_by_pwd_login)
    void onClickByPassword() {
        mAccountTrigger.triggerView(TO_LOGIN_FLAGS);
    }

    @OnClick(R.id.fl_by_weiixn_login)
    void onClickByWeixin() {

    }

    @OnClick(R.id.im_back)
    void clickBack() {
        mAccountTrigger.triggerView(TO_LOGIN_FLAGS);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void getCodeSuccess() {
        NextFragment.setPhone(et_mobile.getText().toString().trim());
        mAccountTrigger.triggerView(TO_NEXT_MSG_LOGIN);
    }
}
