package com.zfb.zhifabao.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Activity;
import com.zfb.zhifabao.flags.account.LoginFragment;
import com.zfb.zhifabao.flags.member.OpenMemberFragment;
import com.zfb.zhifabao.helper.NavHelper;

public class MemberActivity extends Activity {
    private NavHelper mHelper;

    public static void show(Context context) {
        context.startActivity(new Intent(context, MemberActivity.class));
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_member;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mHelper = new NavHelper(this, getSupportFragmentManager(), R.id.account_container);
        mHelper.add(Common.Constance.TO_OPEN_MEMBER_FRAGMENT, new NavHelper.Tab(OpenMemberFragment.class, OpenMemberFragment.class.getName()));
        mHelper.performanceTab(Common.Constance.TO_OPEN_MEMBER_FRAGMENT);
    }
}
