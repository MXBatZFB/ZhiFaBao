package com.zfb.zhifabao.flags.contract.custom;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
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
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import androidx.annotation.RequiresApi;
import butterknife.BindView;
import butterknife.OnClick;

public class SetTimeLimitFragment extends Fragment implements DialogFragment.OnSelectedCallback {
    @BindView(R.id.et_task)
    EditText etTask;
    @BindView(R.id.tv_termType)
    TextView tvTermType;
    @BindView(R.id.tv_startDate)
    TextView tvStartDate;
    @BindView(R.id.tv_endDate)
    TextView tvEndDate;
    @BindView(R.id.tv_endProbationaryDate)
    TextView tvEndProbationaryDate;
    @BindView(R.id.appbar)
    AppBarLayout appBar;
    @BindView(R.id.fl_termType)
    FrameLayout flTermType;
    @BindView(R.id.fl_startDate)
    FrameLayout flStartDate;
    @BindView(R.id.fl_endDate)
    FrameLayout flEndDate;
    @BindView(R.id.fl_endProbationaryDate)
    FrameLayout flEndProbationaryDate;
    @BindView(R.id.fl_work_task)
    FrameLayout flTask;
    private  Calendar calendar = Calendar.getInstance(Locale.CHINA);
    private CommonTrigger mCommonTrigger;
    private  String termType="";
    private  String startDate;
    private  String endDate;
    private  String endProbationaryDate;
    private  String taskContent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCommonTrigger = (CommonTrigger) context;
    }

    public SetTimeLimitFragment() {
    }

    /**
     * 日期选择
     * @param activity
     * @param themeResId
     * @param tv
     * @param calendar
     */
    public static void showDatePickerDialog(Activity activity, int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                String monthStr = monthOfYear+1 + "";
                String dayStr = dayOfMonth + "";
                if(monthOfYear < 10){
                    monthStr = "0" + monthStr;
                }
                if(dayOfMonth < 10){
                    dayStr = "0" + dayStr;
                }
                tv.setText(year +"-"+monthStr + "-"+ dayStr );
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_set_time_limit;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        CustomContractModel model = CustomContractModel.getInstance();
        Log.e("delong","CustomContractModel_hashCode>>>>>>>>>>>>>>>>>"+model.hashCode());
        Glide.with(getActivity()).load(R.drawable.icon_bg)
                .into(new ViewTarget<View , GlideDrawable>(appBar) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setBackground(resource.getCurrent());
                    }
                });
    }

    @OnClick(R.id.tv_startDate)
    void selectStartDate(){
        showDatePickerDialog(getActivity(),  0, tvStartDate, calendar);;
    }

    @OnClick(R.id.tv_endDate)
    void selectEndDate(){
        showDatePickerDialog(getActivity(),  0, tvEndDate, calendar);;
    }

    @OnClick(R.id.tv_endProbationaryDate)
    void selectEndProbationaryDate(){
        showDatePickerDialog(getActivity(),  0, tvEndProbationaryDate, calendar);;
    }

    @OnClick(R.id.tv_termType)
    void selectTermType(){
        ResModel model = new ResModel();
        List<String> types = new ArrayList<>();
        types.add("固定期限");
        types.add("无固定期限");
        types.add("以完成一定工作任务为期限");
        model.setData(types);
        DialogFragment fragment = new DialogFragment(this,R.id.tv_termType,model);
        fragment.show(getChildFragmentManager(),SetTimeLimitFragment.class.getName());
    }
    @OnClick(R.id.btn_next)
    void doNex(){
        boolean temp = checkParameter();
        if(checkParameter()){
            if (termType.equals("固定期限")){
                termType = Common.Constance.TERM_TYPE_ONE;
                CustomContractModel.getInstance().getNecessaryClauses().setTermType(termType);
                CustomContractModel.getInstance().getNecessaryClauses().setStartDate(startDate);
                CustomContractModel.getInstance().getNecessaryClauses().setEndDate(endDate);
                CustomContractModel.getInstance().getNecessaryClauses().setEndProbationaryDate(endProbationaryDate);
            }else if (termType.equals("无固定期限")){
                termType = Common.Constance.TERM_TYPE_TWO;
                CustomContractModel.getInstance().getNecessaryClauses().setTermType(termType);
                CustomContractModel.getInstance().getNecessaryClauses().setStartDate(startDate);
                CustomContractModel.getInstance().getNecessaryClauses().setEndProbationaryDate(endProbationaryDate);
            }else if(termType.equals("以完成一定工作任务为期限")){
                termType = Common.Constance.TERM_TYPE_THREE;
                CustomContractModel.getInstance().getNecessaryClauses().setTermType(termType);
                CustomContractModel.getInstance().getNecessaryClauses().setStartDate(startDate);
                CustomContractModel.getInstance().getNecessaryClauses().setEndProbationaryDate(endProbationaryDate);
                CustomContractModel.getInstance().getNecessaryClauses().setTasks(taskContent);
            }
            mCommonTrigger.triggerView(Common.Constance.TO_SET_WORKING_HOURS_SYSTEM);
        }else {
            if (!(termType.length()>0)){
                Application.showToast("请选择期限类型！");
            }else {
                Application.showToast("所有参数不能为空！");
            }
        }
    }

    @Override
    public void selected(String str,String name, int temp) {
        tvTermType.setText(str);
        termType=str;
        if (str.equals("固定期限")){
            //除了Task其他都需要
            flEndDate.setVisibility(View.VISIBLE);
            flEndProbationaryDate.setVisibility(View.VISIBLE);
            flTask.setVisibility(View.GONE);
        }else if(str.equals("无固定期限")){//
            flTask.setVisibility(View.GONE);
            flEndDate.setVisibility(View.GONE);
        }else if(str.equals("以完成一定工作任务为期限")){
            flEndDate.setVisibility(View.GONE);
            flEndProbationaryDate.setVisibility(View.GONE);
            flTask.setVisibility(View.VISIBLE);
        }
    }

    private boolean checkParameter() {
        termType = tvTermType.getText().toString().trim();
        startDate=tvStartDate.getText().toString().trim();
        endDate=tvEndDate.getText().toString().trim();
        endProbationaryDate=tvEndProbationaryDate.getText().toString().trim();
        taskContent = etTask.getText().toString().trim();
        if (termType.length()>0){
            if (termType.equals("固定期限")){
                return startDate.length()>0&&
                        endDate.length()>0&&
                        endProbationaryDate.length()>0;
            }

            if (termType.equals("无固定期限")){
                Log.e("delong",startDate+startDate);
                Log.e("delong",""+startDate);
                     return startDate.length()>0&&
                             endProbationaryDate.length()>0;
            }

            if (termType.equals("以完成一定工作任务为期限")){
                return startDate.length()>0&&
                        taskContent.length()>0;
            }
        }
        return false;
    }

    @OnClick(R.id.btn_previous)
    void previous(){
        mCommonTrigger.triggerView(Common.Constance.TO_SET_WORK_TIME_AND_PLACE);
    }

    @OnClick(R.id.im_back)
    void back(){
        mCommonTrigger.triggerView(Common.Constance.TO_SET_WORK_TIME_AND_PLACE);
    }

    @Override
    public boolean onBackPressed() {
        mCommonTrigger.triggerView(Common.Constance.TO_SET_WORK_TIME_AND_PLACE);
        return true;
    }

}
