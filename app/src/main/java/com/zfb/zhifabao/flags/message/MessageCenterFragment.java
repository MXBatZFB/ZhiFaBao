package com.zfb.zhifabao.flags.message;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.adapter.PublicTabViewPagerAdapter;
import com.zfb.zhifabao.common.app.Fragment;
import com.zfb.zhifabao.common.factory.model.api.message.GetMessageResultModel;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageCenterFragment extends Fragment {
    @BindView(R.id.tl_message)
     TabLayout tlMsg;
    @BindView(R.id.vp_message)
     ViewPager vpMsg;
    private List<String> mTabTitle = new ArrayList<>();
    private List<Fragment> mFragment = new ArrayList<>();

    public MessageCenterFragment() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_message_center;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mTabTitle.add("通知");
        mTabTitle.add("活动");
        MessageDataFragment commonFragment = new MessageDataFragment();
        Bundle mBundle = new Bundle();
        ArrayList<GetMessageResultModel.NoticeListBean> mData = new ArrayList<>();
        mData.add(new GetMessageResultModel.NoticeListBean("1",0,"我是Test标题","我是测试的类容","1578552798000"));
        mData.add(new GetMessageResultModel.NoticeListBean("1",0,"我是Test标题","我是测试的类容","1578552798000"));
        mData.add(new GetMessageResultModel.NoticeListBean("1",0,"我是Test标题","我是测试的类容","1578552798000"));
        mData.add(new GetMessageResultModel.NoticeListBean("1",1,"我是Test标题","我是测试的类容","1578552798000"));
        mBundle.putParcelableArrayList("_data", mData);
        commonFragment.setArguments(mBundle);
        mFragment.add(commonFragment);

        MessageDataFragment activityFragment = new MessageDataFragment();
        Bundle activityBundle = new Bundle();
        ArrayList<GetMessageResultModel.NoticeListBean> activityData = new ArrayList<>();
        activityData.add(new GetMessageResultModel.NoticeListBean("1",2,"","",""));
        activityData.add(new GetMessageResultModel.NoticeListBean("1",2,"","",""));
        activityBundle.putParcelableArrayList("_data",activityData);
        activityFragment.setArguments(activityBundle);
        mFragment.add(activityFragment);

        FragmentManager fragmentManager = getChildFragmentManager();

        PublicTabViewPagerAdapter tabViewPagerAdapter = new PublicTabViewPagerAdapter(fragmentManager, mFragment, mTabTitle);
        vpMsg.setAdapter(tabViewPagerAdapter);

        tlMsg.setupWithViewPager(vpMsg);

        tlMsg.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpMsg.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
