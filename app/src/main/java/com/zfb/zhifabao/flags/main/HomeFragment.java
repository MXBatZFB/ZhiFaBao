package com.zfb.zhifabao.flags.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.lzj.gallery.library.views.BannerViewPager;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.activities.CareerQuizActivity;
import com.zfb.zhifabao.activities.ConsultationActivity;
import com.zfb.zhifabao.activities.ContractFormulationActivity;
import com.zfb.zhifabao.activities.ContractReviewActivity;
import com.zfb.zhifabao.activities.MyContractActivity;
import com.zfb.zhifabao.activities.SettleCaseActivity;
import com.zfb.zhifabao.activities.WebActivity;
import com.zfb.zhifabao.common.app.Fragment;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends Fragment {
    @BindView(R.id.tv_time)
    TextView mTime;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.banner_viewPager)
    BannerViewPager mViewPager;
    @BindView(R.id.tv_week)
    TextView mWeek;
    private CommonTrigger mCommonTrigger;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");
        String time = dateFormat.format(new Date());
        mTime.setText(time);
        String weekStr = getWeek();
        switch (weekStr) {
            case "Monday":
                weekStr = "星期一";
                break;
            case "Tuesday":
                weekStr = "星期二";
                break;
            case "Wednesday":
                weekStr = "星期三";
                break;
            case "Thursday":
                weekStr = "星期四";
                break;
            case "Friday":
                weekStr = "星期五";
                break;
            case "Saturday":
                weekStr = "星期六";
                break;
            case "Sunday":
                weekStr = "星期日";
                break;
        }
        mWeek.setText(weekStr);

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
                        WebActivity.show(getActivity(), "https://mp.weixin.qq.com/s/K1ENozD-wq83Hcz5zmJXmg");
                    }
                });
    }


    public String getWeek() {
        // 再转换为时间

        Date date = new Date();

        Calendar c = Calendar.getInstance();

        c.setTime(date); //

        int hour = c.get(Calendar.DAY_OF_WEEK);

        ///hour中存的就是星期几了，其范围 1~7  1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }


    public Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCommonTrigger = (CommonTrigger) context;
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

    @OnClick(R.id.im_quiz)
    void quiz() {
        CareerQuizActivity.show(getActivity());
    }

    @OnClick(R.id.im_contract_review)
    void contractReview() {
        ContractReviewActivity.show(getActivity());
    }

    @OnClick(R.id.im_formulation_contract)
    void formulationContract() {
        ContractFormulationActivity.show(getActivity());
    }


    @OnClick(R.id.im_my_contract)
    void myContract() {
        MyContractActivity.show(getActivity());
    }

    @OnClick(R.id.im_law_home)
    void law_home() {
        WebActivity.show(getActivity(), "https://mp.weixin.qq.com/s/K1ENozD-wq83Hcz5zmJXmg");
    }


}
