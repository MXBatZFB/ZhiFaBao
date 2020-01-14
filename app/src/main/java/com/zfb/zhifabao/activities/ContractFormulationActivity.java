package com.zfb.zhifabao.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Activity;
import com.zfb.zhifabao.common.factory.model.api.contract.CustomContractModel;
import com.zfb.zhifabao.flags.contract.custom.LookContractFileFragment;
import com.zfb.zhifabao.flags.contract.custom.SetLaborRemunerationFragment;
import com.zfb.zhifabao.flags.contract.custom.SetPartyAInfoFragment;
import com.zfb.zhifabao.flags.contract.custom.SetPartyBInfoFragment;
import com.zfb.zhifabao.flags.contract.custom.SetTimeLimitFragment;
import com.zfb.zhifabao.flags.contract.custom.SetWorkTimeAndPlaceFragment;
import com.zfb.zhifabao.flags.contract.custom.SetWorkingHoursSystemFragment;
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

    /**
     * 这是状态栏透明的方法
     */
    private void setStatuTrans() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            //SYSTEM_UI_FLAG_LAYOUT_STABLE   SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        setStatuTrans();
        CustomContractModel model = CustomContractModel.getInstance();
        model = null;
        mHelper = new NavHelper(this, getSupportFragmentManager(), R.id.lay_contract_container);
        //file:///android_asset/index.html
        //https://zhifabao.oss-cn-shenzhen.aliyuncs.com/userInfo/741751c925ce49ff95ee3929e4ba9b17/customContract/contract20191224091425139.doc
        mHelper.add(Common.Constance.TO_LOOK_CONTRACT_FILE_FREGMENT,new NavHelper.Tab(LookContractFileFragment.class,LookContractFileFragment.class.getName()))
                .add(TO_CONTRACT_FORMULATION_SET_PARTY_A_INFO, new NavHelper.Tab(SetPartyAInfoFragment.class, SetPartyAInfoFragment.class.getName()))//设置甲方信息的Fragment
                .add(TO_CONTRACT_FORMULATION_SET_PARTY_B_INFO, new NavHelper.Tab(SetPartyBInfoFragment.class,SetPartyBInfoFragment.class.getName()))//设置已方信息的Fragment
                .add(TO_SET_CONTRACT_TIME_LIMIT,new NavHelper.Tab(SetTimeLimitFragment.class, SetTimeLimitFragment.class.getName()))//设置劳动期限类型信息的Fragment
                .add(TO_SET_WORKING_HOURS_SYSTEM,new NavHelper.Tab(SetWorkingHoursSystemFragment.class,SetWorkingHoursSystemFragment.class.getName()))//设置工时制度类型信息的Fragment
                .add(TO_SET_LABOR_REMUNERATION,new NavHelper.Tab(SetLaborRemunerationFragment.class, SetLaborRemunerationFragment.class.getName()))//设置劳资报酬类型信息的Fragment
                .add(TO_SET_WORK_TIME_AND_PLACE,new NavHelper.Tab(SetWorkTimeAndPlaceFragment.class, SetWorkTimeAndPlaceFragment.class.getName()));//设置工作地点和时间的Fragment
        mHelper.performanceTab(Common.Constance.TO_CONTRACT_FORMULATION_SET_PARTY_A_INFO);//
       // mHelper.performanceTab(Common.Constance.TO_LOOK_CONTRACT_FILE_FREGMENT);//

    }

    @Override
    public void triggerView(int flags) {
        mHelper.performanceTab(flags);
    }
}
