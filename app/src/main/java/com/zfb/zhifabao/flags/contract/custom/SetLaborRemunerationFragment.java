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
import com.zfb.zhifabao.activities.LookContractFileActivity;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.contract.CustomContractModel;
import com.zfb.zhifabao.common.factory.model.api.contract.CustomContractResultModel;
import com.zfb.zhifabao.common.factory.presenter.contract.GenerateContract;
import com.zfb.zhifabao.common.factory.presenter.contract.GeneratePresenter;
import com.zfb.zhifabao.common.widget.app.GeneralDialog;
import com.zfb.zhifabao.flags.law.DialogFragment;
import com.zfb.zhifabao.flags.main.CommonTrigger;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

public class SetLaborRemunerationFragment extends PresenterFragment<GenerateContract.Presenter> implements GenerateContract.View, DialogFragment.OnSelectedCallback {
    @BindView(R.id.appbar)
    AppBarLayout appBar;
    private CommonTrigger mCommonTrigger;
    @BindView(R.id.tv_select_type)
    TextView tvSelectType;

    @BindView(R.id.tv_title_salary)
    TextView tvTitleSalary;

    @BindView(R.id.fl_salary)
    FrameLayout flSalary;

    @BindView(R.id.fl_custom_pay_away)
    FrameLayout flCustomPayAway;

    @BindView(R.id.et_salary)
    EditText etSalary;

    @BindView(R.id.et_pay_day)
    EditText etPayDay;

    @BindView(R.id.et_custom_pay_way)
    EditText etCustomPayWay;

    private String salaryType;
    private String salary;//salaryType为1，2，3时，需要提供的参数为1时表示每月的工作金额；为2时表示每一件的报酬，3时是基本工资
    private String salaryDate;//工资发放日，为每月固定的哪一天
    private String salaryMethod;//salaryType为3，4，需要提供的参数

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCommonTrigger = (CommonTrigger) context;
    }

    @Override
    protected GenerateContract.Presenter initPresenter() {
        return new GeneratePresenter(this);
    }

    public SetLaborRemunerationFragment() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_set_labor_remuneration;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        CustomContractModel model = CustomContractModel.getInstance();
        model.getNecessaryClauses().setSalaryType(Common.Constance.SALARY_TYPE_ONE);
        Log.e("delong","SetLaborRemunerationFragment>>>>>>>>>>>>>>"+model.toString());
        Glide.with(Application.getInstance()).load(R.drawable.icon_bg)
                .into(new ViewTarget<View , GlideDrawable>(appBar) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setBackground(resource.getCurrent());
                    }
                });
    }

    // 1.按月发放工资
    // 2.记件工资
    // 3.基本工资和绩效工资相结合的工资
    // 4.双方约定的其他方式
    @OnClick(R.id.btn_next)
    void doNex(){
        if (checkParameter()){
            if (salaryType.equals("按月发放工资")){
                salaryType = Common.Constance.SALARY_TYPE_ONE;
                CustomContractModel.getInstance().getNecessaryClauses().setSalary(salary);
            }else if (salaryType.equals("记件工资")){
                salaryType = Common.Constance.SALARY_TYPE_TOW;
                CustomContractModel.getInstance().getNecessaryClauses().setSalary(salary);
            }else if(salaryType.equals("基本工资和绩效工资相结合的工资")){
                salaryType = Common.Constance.SALARY_TYPE_THREE;
                CustomContractModel.getInstance().getNecessaryClauses().setSalary(salary);
                CustomContractModel.getInstance().getNecessaryClauses().setSalaryMethod(salaryMethod);
            }else if (salaryType.equals("双方约定的其他方式")){
                salaryType = Common.Constance.SALARY_TYPE_FOUR;
                CustomContractModel.getInstance().getNecessaryClauses().setSalaryMethod(salaryMethod);
            }
            CustomContractModel.getInstance().getNecessaryClauses().setSalaryType(salaryType);
            CustomContractModel.getInstance().getNecessaryClauses().setSalaryDate(salaryDate);
            CustomContractModel model = CustomContractModel.getInstance();
            mPresenter.generateContract(model);
        }else {
            if (!(salaryType.length()>0)){
                Application.showToast("请选薪酬支付方式类型！");
            }else {
                Application.showToast("所有参数不能为空！");
            }
        }

    }

    @OnClick(R.id.tv_select_type)
    void selectTermType(){
        ResModel model = new ResModel();
        List<String> types = new ArrayList<>();
        types.add("按月发放工资");
        types.add("记件工资");
        types.add("基本工资和绩效工资相结合的工资");
        types.add("双方约定的其他方式");
        model.setData(types);
        DialogFragment fragment = new DialogFragment(this,R.id.tv_select_type,model);
        fragment.show(getChildFragmentManager(),SetTimeLimitFragment.class.getName());
    }

    // 1.按月发放工资
    // 2.记件工资
    // 3.基本工资和绩效工资相结合的工资
    // 4.双方约定的其他方式
    @Override
    public void selected(String str,String name, int temp) {
        salaryType = str;
        tvSelectType.setText(str);
        if (str.equals("按月发放工资")){
            flCustomPayAway.setVisibility(View.GONE);
            flSalary.setVisibility(View.VISIBLE);
            tvTitleSalary.setText("每月工资");
        }else if(str.equals("记件工资")){
            tvTitleSalary.setText("单件的薪酬：");
            flSalary.setVisibility(View.VISIBLE);
            flCustomPayAway.setVisibility(View.GONE);
        }else if(str.equals("基本工资和绩效工资相结合的工资")){
            tvTitleSalary.setText("基本工资：");
            etCustomPayWay.setHint("请输入您公司定制的绩效具体规则！");
            flCustomPayAway.setVisibility(View.VISIBLE);
        }else if (str.equals("双方约定的其他方式")){
            flCustomPayAway.setVisibility(View.VISIBLE);
            etCustomPayWay.setHint("请在这里输入您自定义的薪酬发放方式（劳动合同中的薪资(不含加班费)只要不低于所在地级市要求的最低工资水平就是合法的）");
            flSalary.setVisibility(View.GONE);
        }
    }

    private boolean checkParameter() {
        salaryType = tvSelectType.getText().toString().trim();
        salary=etSalary.getText().toString().trim();
        salaryDate=etPayDay.getText().toString().trim();
        salaryMethod = etCustomPayWay.getText().toString().trim();
        if (salaryType.length()>0){
            if (salaryType.equals("按月发放工资")){
                return salary.length()>0&&
                        salaryDate.length()>0;
            }

            if (salaryType.equals("记件工资")){
                return salary.length()>0&&
                        salaryDate.length()>0;
            }

            if (salaryType.equals("基本工资和绩效工资相结合的工资")){
                return salary.length()>0&&
                        salaryDate.length()>0&&salaryMethod.length()>0;
            }

            if (salaryType.equals("双方约定的其他方式")){
                return salaryMethod.length()>0&&
                        salaryDate.length()>0;
            }
        }
        return false;
    }

    @OnClick(R.id.btn_previous)
    void previous(){
        mCommonTrigger.triggerView(Common.Constance.TO_SET_WORKING_HOURS_SYSTEM);
    }

    @OnClick(R.id.im_back)
    void back(){
        mCommonTrigger.triggerView(Common.Constance.TO_SET_WORKING_HOURS_SYSTEM);
    }

    @Override
    public boolean onBackPressed() {
        mCommonTrigger.triggerView(Common.Constance.TO_SET_WORKING_HOURS_SYSTEM);
        return true;
    }

    @Override
    public void onGenerateSuccess(CustomContractResultModel model) {
        LookContractFileActivity.show(getContext(),model);
    }

    @Override
    public void onParameterError(int codeError,String msg) {
        View view = getLayoutInflater().inflate(R.layout.parameter_error_dialog,null);
        TextView tvModifyError = view.findViewById(R.id.modify_parameter);
        TextView tvErrorMsg = view.findViewById(R.id.tv_error_describe);
        tvErrorMsg.setText(msg);
        GeneralDialog dialog = new GeneralDialog(getContext(),0,0,view,R.style.DialogTheme);
        dialog.setCancelable(true);
        dialog.show();
        tvModifyError.setOnClickListener(new View.OnClickListener() {
            //200
            //1018：试用期期限错误
            //1019：身份证号码错误
            //1020：统一社会信用代码错误
            //1021：参数错误（参数为空或参数值不合法）
            @Override
            public void onClick(View v) {
                switch (codeError){
                    case 1018:
                        mCommonTrigger.triggerView(Common.Constance.TO_SET_CONTRACT_TIME_LIMIT);
                        break;
                    case 1019:
                        mCommonTrigger.triggerView(Common.Constance.TO_CONTRACT_FORMULATION_SET_PARTY_B_INFO);
                        break;
                    case 1020:
                        mCommonTrigger.triggerView(Common.Constance.TO_CONTRACT_FORMULATION_SET_PARTY_A_INFO);
                        break;
                    default:
                        break;
                }
                dialog.dismiss();
            }
        });

    }
}
