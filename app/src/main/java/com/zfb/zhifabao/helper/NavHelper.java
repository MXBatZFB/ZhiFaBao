package com.zfb.zhifabao.helper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.flags.account.CallbackOnRegister;
import com.zfb.zhifabao.flags.account.MsgLoginFragment;
import com.zfb.zhifabao.flags.account.RegisterFragment;

@SuppressWarnings("ALL")
public class NavHelper<T> implements Common.Constance {
    private SparseArray<Tab<T>> tabs = new SparseArray<>();
    private Context mContext;
    private FragmentManager mFragmentManager;
    private Tab currentTab;
    private int containerId;

    public NavHelper(Context mContext, FragmentManager mFragmentManager, int containerId) {
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.containerId = containerId;
    }

    public boolean performanceTab(int menuItemId) {
        Tab<T> tab = tabs.get(menuItemId);
        if (tab != null) {
            doSelectTab(tab);
            return true;
        }
        return false;
    }

    private void doSelectTab(Tab<T> tab) {
        if (currentTab != null) {
            if (tab == currentTab) {
                return;
            }
            doTabChange(tab);
            currentTab = tab;
        } else {
            currentTab = tab;
            doTabChange(tab);
        }
    }

    private void doTabChange(Tab newTab) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (currentTab.fragment != null) {
            transaction.detach(currentTab.fragment);
        }
        if (newTab.fragment == null) {
            Fragment fragment = Fragment.instantiate(mContext, newTab.clx.getName());
            newTab.fragment = fragment;
            if (newTab.extra.equals("MsgLoginFragment")) {
                Fragment tmp = Fragment.instantiate(mContext, RegisterFragment.class.getName());
                tabs.get(TO_REGISTER_FLAGS).fragment = tmp;
                ((MsgLoginFragment) fragment).setOnRegisterListener((CallbackOnRegister) tmp);
                transaction.add(containerId, tmp);
                transaction.detach(tmp);
            }
            transaction.add(containerId, fragment);
        } else {
            transaction.attach(newTab.fragment);
        }
        transaction.commit();
    }

    @SuppressWarnings("unchecked")
    public NavHelper add(int id, Tab tab) {
        tabs.put(id, tab);
        return this;
    }

    public static class Tab<T> {
        private Class<?> clx;
        private Fragment fragment;
        private T extra;

        public Tab(Class<?> clx, T extra) {
            this.clx = clx;
            this.extra = extra;
        }
    }
}
