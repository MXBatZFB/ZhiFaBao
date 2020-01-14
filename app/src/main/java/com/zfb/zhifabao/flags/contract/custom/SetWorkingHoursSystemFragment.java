package com.zfb.zhifabao.flags.contract.custom;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.google.android.material.appbar.AppBarLayout;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.app.Fragment;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.contract.CustomContractModel;
import com.zfb.zhifabao.flags.law.DialogFragment;
import com.zfb.zhifabao.flags.main.CommonTrigger;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

public class SetWorkingHoursSystemFragment extends Fragment implements DialogFragment.OnSelectedCallback {
    @BindView(R.id.appbar)
    AppBarLayout appBar;
    private CommonTrigger mCommonTrigger;

    @BindView(R.id.tv_select_type)
    TextView tvSelectType;
    @BindView(R.id.et_cycle)
    EditText etCycle;

    @BindView(R.id.et_custom_time)
    EditText etCustomTime;

    @BindView(R.id.fl_cycle)
    FrameLayout flCycle;

    @BindView(R.id.fl_custom_work_time)
    FrameLayout flCustomWorkTime;

    private String workTimeType;
    private String cycle;
    private String customTime;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCommonTrigger = (CommonTrigger) context;
    }

    public SetWorkingHoursSystemFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_set_working_hours_system;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        CustomContractModel model = CustomContractModel.getInstance();
        Log.e("delong","CustomContractModel_hashCode>>>>>>>>>>>>>>>>>"+model);
        Glide.with(getActivity()).load(R.drawable.icon_bg)
                .into(new ViewTarget<View , GlideDrawable>(appBar) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setBackground(resource.getCurrent());
                    }
                });
    }

    @OnClick(R.id.tv_select_type)
    void selectType(){
        ResModel model = new ResModel();
        List<String> types = new ArrayList<>();
        types.add("标准工时工作制");
        types.add("依法实行以一个时间段为周期的综合计算工时工作制");
        types.add("依法实行不定时工作制");
        model.setData(types);
        DialogFragment fragment = new DialogFragment(this,R.id.tv_select_type,model);
        fragment.show(getChildFragmentManager(),SetWorkingHoursSystemFragment.class.getName());
    }

    @OnClick(R.id.btn_next)
    void doNex(){
        if (checkParameter()){
            if (workTimeType.equals("依法实行以一个时间段为周期的综合计算工时工作制")){
                workTimeType = Common.Constance.WORK_TIME_TYPE_TOW;
                CustomContractModel.getInstance().getNecessaryClauses().setCycle(cycle);
            }else if (workTimeType.equals("依法实行不定时工作制")){
                workTimeType = Common.Constance.WORK_TIME_TYPE_THREE;
                CustomContractModel.getInstance().getNecessaryClauses().setCustomTime(customTime);
            }else {
                workTimeType = Common.Constance.WORK_TIME_TYPE_ONE;
            }
            CustomContractModel.getInstance().getNecessaryClauses().setWorkTimeType(workTimeType);
            mCommonTrigger.triggerView(Common.Constance.TO_SET_LABOR_REMUNERATION);
        }else {
            if (!(workTimeType.length()>0)){
                Application.showToast("请选择工时制度类型类型！");
            }else {
                Application.showToast("所有参数不能为空！");
            }
        }
    }

    private boolean checkParameter() {
        workTimeType = tvSelectType.getText().toString().trim();
        cycle = etCycle.getText().toString().trim();
        customTime = etCustomTime.getText().toString().trim();
        if (workTimeType.length()>0){
            if (workTimeType.equals("依法实行以一个时间段为周期的综合计算工时工作制")){
                return cycle.length()>0;
            }else if(workTimeType.equals("依法实行不定时工作制")){
                return customTime.length()>0;
            }else if(workTimeType.equals("标准工时工作制")){
                return true;
            }
        }
        return false;
    }




    @Override
    public void selected(String str,String name, int temp) {
        workTimeType = str;
        tvSelectType.setText(str);
        if (str.equals("标准工时工作制")){
            flCycle.setVisibility(View.GONE);
            flCustomWorkTime.setVisibility(View.GONE);
        }else if(str.equals("依法实行以一个时间段为周期的综合计算工时工作制")){
            flCycle.setVisibility(View.VISIBLE);
            flCustomWorkTime.setVisibility(View.GONE);
        }else if((str.equals("依法实行不定时工作制"))){
            flCycle.setVisibility(View.GONE);
            flCustomWorkTime.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.btn_previous)
    void previous(){
        mCommonTrigger.triggerView(Common.Constance.TO_SET_CONTRACT_TIME_LIMIT);
    }

    @OnClick(R.id.im_back)
    void back(){
        mCommonTrigger.triggerView(Common.Constance.TO_SET_CONTRACT_TIME_LIMIT);
    }

    @Override
    public boolean onBackPressed() {
        mCommonTrigger.triggerView(Common.Constance.TO_SET_CONTRACT_TIME_LIMIT);
        return true;
    }

}
