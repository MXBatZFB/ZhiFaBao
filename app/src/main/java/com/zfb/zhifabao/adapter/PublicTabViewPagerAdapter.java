package com.zfb.zhifabao.adapter;


import com.zfb.zhifabao.common.app.Fragment;

import java.util.List;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * 选项卡tab
 * Created by yis on 2018/4/17.
 */
public class PublicTabViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;//fragment列表
    private List<String> mTitles;//tab名的列表


    public PublicTabViewPagerAdapter(FragmentManager fragmentManager, List<com.zfb.zhifabao.common.app.Fragment> mFragment, List<String> mTabTitle) {
        super(fragmentManager);
        this.mFragments = mFragment;
        this.mTitles = mTabTitle;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position % mTitles.size());
    }


//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
////    viewpager不会被销毁
//        super.destroyItem(container, position, object);
//    }
}
