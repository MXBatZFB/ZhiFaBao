package com.zfb.zhifabao.activities;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Activity;
import com.zfb.zhifabao.flags.work.WorkFragment;
import com.zfb.zhifabao.helper.NavHelper;

public class WorkActivity extends Activity  {
    private NavHelper mHelper;
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_work;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mHelper = new NavHelper(this, getSupportFragmentManager(), R.id.account_container);
        mHelper.add(Common.Constance.TO_WORK_FRAGMENT_FLAGS, new NavHelper.Tab(WorkFragment.class, "WorkFragment"));
        mHelper.performanceTab(Common.Constance.TO_WORK_FRAGMENT_FLAGS);
    }
}
