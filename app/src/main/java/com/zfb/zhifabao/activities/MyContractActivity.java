package com.zfb.zhifabao.activities;

import android.content.Context;
import android.content.Intent;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Activity;
import com.zfb.zhifabao.flags.contract.MyContractFragment;
import com.zfb.zhifabao.flags.main.CommonTrigger;
import com.zfb.zhifabao.helper.NavHelper;

public class MyContractActivity extends Activity implements CommonTrigger, Common.Constance {

    private NavHelper mHelper;

    public static void show(Context context) {
        context.startActivity(new Intent(context, MyContractActivity.class));
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_contract;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mHelper = new NavHelper(this, getSupportFragmentManager(), R.id.lay_contract_container);
        mHelper.add(TO_MY_CONTRACT_FRAGMENT, new NavHelper.Tab(MyContractFragment.class, MyContractFragment.class.getName()));
        mHelper.performanceTab(Common.Constance.TO_MY_CONTRACT_FRAGMENT);
    }

    @Override
    public void triggerView(int flags) {
        mHelper.performanceTab(flags);
    }
}
