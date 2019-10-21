package com.zfb.zhifabao;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zfb.zhifabao.activities.UserActivity;
import com.zfb.zhifabao.common.app.Activity;
import com.zfb.zhifabao.common.factory.persistence.Account;
import com.zfb.zhifabao.flags.main.FindFragment;
import com.zfb.zhifabao.flags.main.HomeFragment;
import com.zfb.zhifabao.flags.main.MyFragment;
import com.zfb.zhifabao.helper.NavHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 这是开发分支，用于智法宝开发
 */
public class MainActivity extends Activity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private NavHelper mNavHelper;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;

    public static void show(Context context) {
        context.startActivity(new Intent(context,MainActivity.class));
    }


    @Override
    protected boolean initArgs(Bundle Bundle) {
        if (Account.isComplete()) {
            return super.initArgs(Bundle);
        } else {
            UserActivity.show(this);
            finish();
            return false;
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressWarnings("unchecked")
    @SuppressLint("ResourceType")
    @Override
    protected void initWidget() {
        super.initWidget();
        ButterKnife.bind(this);
        setStatuTrans();
        mNavHelper = new NavHelper(this, getSupportFragmentManager(), R.id.fl_container);
        mNavHelper.add(R.id.action_home, new NavHelper.Tab(HomeFragment.class, R.string.menu_item_home_title))
                .add(R.id.action_find, new NavHelper.Tab(FindFragment.class, R.string.menu_item_find_title))
                  .add(R.id.action_my, new NavHelper.Tab(MyFragment.class, R.string.menu_iteam_my_title));
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        mNavHelper.performanceTab(R.id.action_home);
    }


    /**
     * 这是状态栏透明的方法
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setStatuTrans() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Menu menu = bottomNavigation.getMenu();
//        menu.performIdentifierAction(R.id.action_home,0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return mNavHelper.performanceTab(menuItem.getItemId());
    }

}

