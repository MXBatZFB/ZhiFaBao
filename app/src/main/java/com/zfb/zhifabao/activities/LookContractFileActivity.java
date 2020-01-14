package com.zfb.zhifabao.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Activity;
import com.zfb.zhifabao.common.factory.model.api.contract.CustomContractResultModel;
import com.zfb.zhifabao.flags.contract.custom.LookContractFileFragment;
import com.zfb.zhifabao.flags.main.CommonTrigger;
import com.zfb.zhifabao.helper.NavHelper;

public class LookContractFileActivity extends Activity implements CommonTrigger{
    private NavHelper mHelper;
    private static String look_url ;

    public static void show(Context context, CustomContractResultModel model) {
        look_url = model.getFileurl();
        Intent intent = new Intent(context, LookContractFileActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected boolean initArgs(Bundle bundle) {
        return super.initArgs(bundle);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mHelper = new NavHelper(this,getSupportFragmentManager(),R.id.contract_container);
        mHelper.add(Common.Constance.TO_LOOK_CONTRACT_FILE_FREGMENT,new NavHelper.Tab(LookContractFileFragment.class,look_url));
        mHelper.performanceTab(Common.Constance.TO_LOOK_CONTRACT_FILE_FREGMENT);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_look_contract_file;
    }

    @Override
    public void triggerView(int flags) {
       mHelper.performanceTab(flags);
    }
}
