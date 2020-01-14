package com.zfb.zhifabao.flags.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.appbar.AppBarLayout;
import android.util.Log;
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
import com.zfb.zhifabao.activities.EditUserInfoActivity;
import com.zfb.zhifabao.activities.MemberActivity;
import com.zfb.zhifabao.activities.UserActivity;
import com.zfb.zhifabao.activities.ZFBMessageActivity;
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
public class MyFragment extends Fragment{
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
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @OnClick(R.id.im_find_msg)
    void findMsg(){
        ZFBMessageActivity.show(getActivity());
    }

    @OnClick(R.id.portrait_pic)
     void upUserInfo(){
        getActivity().startActivityForResult(new Intent(getContext(), EditUserInfoActivity.class),188);
    }


    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        setStatusTrans();
        initData();
    }

    private void initData() {
        UserInfo userInfo = Account.getUser();
        if (userInfo.getMemberType()!=0) {
              Glide.with(getActivity())
                .load(R.drawable.huiyuan)
                .into(new ViewTarget<View, GlideDrawable>(mAppbar) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

                    }
                });
            Glide.with(getActivity()).load(R.drawable.hy_logo).into(memberPic);
        }
        Log.e("delong","MyFragment》》》》》》》》》》》》》》》"+userInfo.getPortrait());
        Glide.with(getActivity()).load(userInfo.getPortrait()).asBitmap()
               .diskCacheStrategy( DiskCacheStrategy.NONE )
               .skipMemoryCache(true).into(mPortrait);
        tvName.setText(userInfo.getNickname());
    }

    @OnClick(R.id.out_login)
    void outLogin() {
        Account.outLogin();
        AccountActivity.show(getContext());
        getActivity().finish();
    }

    @OnClick(R.id.fl_open_member)
    void openMember(){
        //Todo
        MemberActivity.show(getActivity());
    }


    @OnClick(R.id.fl_edit_info)
    void editUserInfo(){
        //Todo
        getActivity().startActivityForResult(new Intent(getContext(), EditUserInfoActivity.class),188);
    }

    @OnClick(R.id.fl_modify_pwd)
    void modifyPwd(){
        //Todo

    }

    @OnClick(R.id.fl_bind_weChat)
    void bindWeChat(){
        //Todo
    }

    @OnClick(R.id.fl_zfb)
    void upOrLookAppVersion(){
        //TODO
    }



    @Override
    public void onResume() {
        super.onResume();
        setStatusTrans();
        initData();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_my;
    }


    /**
     * 这是状态栏透明的方法
     */
    private void setStatusTrans() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}