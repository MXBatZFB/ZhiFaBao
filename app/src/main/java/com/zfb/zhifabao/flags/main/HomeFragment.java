package com.zfb.zhifabao.flags.main;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.lzj.gallery.library.views.BannerViewPager;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.activities.ConsultationActivity;
import com.zfb.zhifabao.activities.SettleCaseActivity;
import com.zfb.zhifabao.common.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends Fragment {
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.banner_viewPager)
    BannerViewPager mViewPager;
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        Glide.with(this)
                .load(R.drawable.head_backgroud)
                .into(new ViewTarget<View, GlideDrawable>(mAppbar) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setBackground(resource.getCurrent());
                    }
                });

        List<String> bannerList = new ArrayList<>();
        bannerList.add("https://weilong-1.oss-cn-shenzhen.aliyuncs.com/image/YYYY09/message_banner_01.png");
        bannerList.add("https://weilong-1.oss-cn-shenzhen.aliyuncs.com/image/YYYY09/message_banner_02.png");
        bannerList.add("https://weilong-1.oss-cn-shenzhen.aliyuncs.com/image/YYYY09/banner1.png");
        bannerList.add("https://weilong-1.oss-cn-shenzhen.aliyuncs.com/image/YYYY09/banner2.png");
        mViewPager.initBanner(bannerList, true)
                .addPageMargin(-30, 20)
                .addPoint(5)//添加指示器
                .addPointBottom(12)
                // .addStartTimer(5)//自动轮播5秒间隔
                .addRoundCorners(12)//圆角
                .finishConfig()//这句必须加
                .addBannerListener(new BannerViewPager.OnClickBannerListener() {
                    @Override
                    public void onBannerClick(int position) {
                        //点击item
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResume() {
        super.onResume();
        setStatuTrans();
    }

    /**
     * 这是状态栏透明的方法
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setStatuTrans() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }


    @OnClick(R.id.im_zixun)
    void zixun() {
        ConsultationActivity.show(getContext());
    }

    @OnClick(R.id.im_settle)
    void settleCases() {
        SettleCaseActivity.show(getActivity());
    }

}
