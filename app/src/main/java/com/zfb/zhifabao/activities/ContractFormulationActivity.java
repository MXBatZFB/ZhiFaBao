package com.zfb.zhifabao.activities;

import android.content.Context;
import android.content.Intent;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Activity;
import com.zfb.zhifabao.flags.contract.ContractReviewFragment;
import com.zfb.zhifabao.flags.main.CommonTrigger;
import com.zfb.zhifabao.helper.NavHelper;

public class ContractFormulationActivity extends Activity implements CommonTrigger, Common.Constance {

    private NavHelper mHelper;

    public static void show(Context context) {
        context.startActivity(new Intent(context, ContractFormulationActivity.class));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_contract_formulation;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mHelper = new NavHelper(this, getSupportFragmentManager(), R.id.lay_contract_container);
        mHelper.add(TO_CONTRACT_FORMULATION_FRAGMENT, new NavHelper.Tab(ContractReviewFragment.class, ContractReviewFragment.class.getName()));
        mHelper.performanceTab(Common.Constance.TO_MY_CONTRACT_FRAGMENT);

    }

    @Override
    public void triggerView(int flags) {
        mHelper.performanceTab(flags);
    }
}
