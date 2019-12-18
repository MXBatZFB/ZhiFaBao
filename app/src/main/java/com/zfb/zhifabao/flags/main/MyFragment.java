package com.zfb.zhifabao.flags.main;

import android.graphics.Color;
import android.os.Build;
import com.google.android.material.appbar.AppBarLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.activities.AccountActivity;
import com.zfb.zhifabao.common.app.Fragment;
import com.zfb.zhifabao.common.factory.model.api.account.UserInfo;
import com.zfb.zhifabao.common.factory.persistence.Account;

import net.qiujuer.genius.ui.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 主界面我的模块的Fragment
 */
public class MyFragment extends Fragment {
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.portrait_pic)
    CircleImageView mPortrait;
    @BindView(R.id.tv_userName)
    TextView tvName;
    @BindView(R.id.member_pic)
    ImageView memberPic;
    @BindView(R.id.out_login)
    Button btnOutLogin;

    public MyFragment() {
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        setStatuTrans();
        Glide.with(this)
                .load(R.drawable.no_huiyuan)
                .into(new ViewTarget<View, GlideDrawable>(mAppbar) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

                    }
                });
        UserInfo userInfo = Account.getUser();
        Glide.with(this).load(userInfo.getPortrait()).into(mPortrait);
        if (userInfo.isMember()) {
            Glide.with(this).load(R.drawable.hy_logo).into(memberPic);
        }
        tvName.setText(userInfo.getUsername());
    }

    @OnClick(R.id.out_login)
    void outLogin() {
        Account.outLogin();
        AccountActivity.show(getContext());
        getActivity().finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        setStatuTrans();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_my;
    }

    /**
     * 这是状态栏透明的方法
     */
    private void setStatuTrans() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
