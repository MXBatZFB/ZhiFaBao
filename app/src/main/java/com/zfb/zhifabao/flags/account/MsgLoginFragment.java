package com.zfb.zhifabao.flags.account;

import android.content.Context;
import android.graphics.Outline;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.zfb.zhifabao.MainActivity;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.presenter.account.MsgLoginContract;
import com.zfb.zhifabao.common.factory.presenter.account.MsgLoginPresenter;
import com.zfb.zhifabao.common.widget.app.TimCount;
import net.qiujuer.genius.ui.widget.Loading;
import java.util.Objects;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

@SuppressWarnings("unused")
public class MsgLoginFragment extends PresenterFragment<MsgLoginContract.Presenter>
                                  implements MsgLoginContract.View ,
                                             Common.Constance {
    private AccountTrigger mAccountTrigger;
    private CallbackOnRegister callback;
    @BindView(R.id.bg_msglogin)
    CircleImageView circleImageView;

    @BindView(R.id.et_phone)
    EditText et_phone;

    @BindView(R.id.et_code)
    EditText et_code;

    @BindView(R.id.btn_get_code)
    Button btn_get_code;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @BindView(R.id.fl_by_weiixn_login)
    FrameLayout fl_by_weiixn_login;

    @BindView(R.id.fl_by_pwd_login)
    FrameLayout fl_by_pwd_login;

    @BindView(R.id.loading)
    Loading loading;

    private TimCount mTimCount ;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAccountTrigger = (AccountTrigger) context;
    }

    public MsgLoginFragment() {

    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        circleImageView.setClipToOutline(true);
        circleImageView.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(new Rect(0, 0, view.getHeight(), view.getHeight()));
            }
        });
        mTimCount = new TimCount(60000,1000);
        mTimCount.setView(btn_get_code);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_msg_login;
    }

    @Override
    protected MsgLoginContract.Presenter initPresenter() {
        return new MsgLoginPresenter(this);
    }

    @Override
    public void loginSuccess(){
        MainActivity.show(Objects.requireNonNull(getContext()));
        Objects.requireNonNull(getActivity()).finish();
        Application.showToast(R.string.data_account_login_success);
    }

    @Override
    public void showLoding() {
        super.showLoding();
        loading.start();
        btn_submit.setClickable(false);
        btn_get_code.setClickable(false);
        fl_by_pwd_login.setClickable(false);
        fl_by_weiixn_login.setClickable(false);
    }

    public void setOnRegisterListener(CallbackOnRegister callback){
        this.callback = callback;
    }

    @Override
    public void toRegister() {
        mAccountTrigger.triggerView(TO_REGISTER_FLAGS);
        callback.onRegister(et_phone.getText().toString().trim(),et_code.getText().toString().trim());
    }

    @Override
    public void showTimer() {
        mTimCount.start();
    }

    @OnClick(R.id.btn_get_code)
    void ongetCode(){
       mPresenter.getCode(et_phone.getText().toString().trim());
    }

    @OnClick(R.id.btn_submit)
    void onSubmit(){
         mPresenter.login(et_phone.getText().toString().trim(),et_code.getText().toString());
    }

    @Override
    public void showError(String str) {
        super.showError(str);
        loading.stop();
        btn_submit.setClickable(true);
        btn_get_code.setClickable(true);
        fl_by_pwd_login.setClickable(true);
        fl_by_weiixn_login.setClickable(true);
    }

    @OnClick(R.id.fl_by_pwd_login)
    void onClickByPassword(){
        mAccountTrigger.triggerView(TO_LOGIN_FLAGS);
    }

    @OnClick(R.id.fl_by_weiixn_login)
    void onClickByWeixin(){

    }

    @OnClick(R.id.im_back)
    void clickBack(){
        Objects.requireNonNull(getActivity()).finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        et_code.setText("");
    }
}
