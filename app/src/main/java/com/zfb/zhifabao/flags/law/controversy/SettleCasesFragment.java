package com.zfb.zhifabao.flags.law.controversy;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.flags.account.AccountTrigger;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseTypeResultModel;
import com.zfb.zhifabao.common.factory.model.api.cases.ResolveLaborCasesModel;
import com.zfb.zhifabao.common.factory.model.api.cases.ResolveLaborCasesResultModel;
import com.zfb.zhifabao.common.factory.presenter.cases.SettleCasesContract;
import com.zfb.zhifabao.common.factory.presenter.cases.SettleCasesPresenter;
import com.zfb.zhifabao.flags.law.DialogFragment;

import net.qiujuer.genius.res.Resource;
import net.qiujuer.genius.ui.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SettleCasesFragment extends PresenterFragment<SettleCasesContract.Presenter>
        implements DialogFragment.OnSelectedCallback,
        SettleCasesContract.View {
    @BindView(R.id.select_cases_type)
    TextView tv_cases_type;
    @BindView(R.id.select_cases_info)
    TextView tv_cases_info;
    @BindView(R.id.select_cases_program)
    TextView tv_cases_program;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private  ResolveLaborCasesModel model = new ResolveLaborCasesModel();
    private AccountTrigger mAccountTrigger;

    public SettleCasesFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_settle_case;
    }


    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        setStatuTrans();
    }

    /**
     * 这是状态栏透明的方法
     */
    private void setStatuTrans() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /**
     *  *identity	string	是	0：被告 1：原告
     *   * process	string	是	法律流程 0：一审，1：二审，2：再审，3:劳动仲裁
     */
    @OnClick(R.id.select_cases_type)
    void selectCasesType() {
        String identity = tv_cases_info.getText().toString().trim();
        if (identity.equals("被告")){
            mPresenter.loadCaseType("0");
            model.setIdentity("0");
        }else if (identity.equals("原告")){
            mPresenter.loadCaseType("1");
            model.setIdentity("1");
        }else{
            Toast.makeText(getActivity(),"请先选择案件信息！",Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_submit)
    void submit() {
            mPresenter.doCase(model);
    }


    @OnClick(R.id.select_cases_info)
    void selectCasesInfo() {
        List<String> infoList = new ArrayList<>();
        infoList.add("原告");
        infoList.add("被告");
        ResModel resModel = new ResModel();
        resModel.setData(infoList);
        DialogFragment fragment = new DialogFragment(this, R.id.select_cases_info, resModel);
        fragment.show(getChildFragmentManager(), SettleCasesFragment.class.getName());
    }

    @OnClick(R.id.select_cases_program)
    void selectCasesProgram() {
        List<String> programList = new ArrayList<>();
        programList.add("一审");
        programList.add("二审");
        programList.add("再审");
        //劳动仲裁
        programList.add("劳动仲裁");
        ResModel resModel = new ResModel();
        resModel.setData(programList);

        DialogFragment fragment = new DialogFragment(this, R.id.select_cases_program, resModel);
        fragment.show(getChildFragmentManager(), SettleCasesFragment.class.getName());
    }



    @OnClick(R.id.im_back)
    void onBack() {
        getActivity().finish();
    }

    @Override
    public void selected(String str,String name,int temp) {
        switch (temp){
            case  R.id.select_cases_info:
                tv_cases_info.setText(str);
                tv_cases_info.setTextColor(Resource.Color.BLACK);
                tv_cases_info.setGravity(Gravity.CENTER);
                if (str.equals("被告")){
                    model.setIdentity("1");
                }else if(str.equals("原告")) {
                    model.setIdentity("0");
                }
                break;
            case  R.id.select_cases_type:
                tv_cases_type.setText(str);
                tv_cases_type.setTextColor(Resource.Color.BLACK);
                tv_cases_type.setGravity(Gravity.CENTER);
                model.setType(str);
                break;
            case  R.id.select_cases_program:
                tv_cases_program.setText(str);
                tv_cases_program.setTextColor(Resource.Color.BLACK);
                tv_cases_program.setGravity(Gravity.CENTER);
                if (str.equals("一审")){
                    model.setProcess("0");
                }else if(str.equals("二审")){
                    model.setProcess("1");
                }else if(str.equals("再审")){
                    model.setProcess("2");
                }else if(str.equals("劳动仲裁")){
                    model.setProcess("3");
                }
                break;
        }
        Log.e("delong",model.toString());
        if (model.getIdentity()!=null&&model.getType()!=null&&model.getProcess()!=null){
            btnSubmit.setEnabled(true);
            Log.e("delong",">>>>>>>>>>>>>>>>>>>>>>>参数初始化完成！！");
        }

    }

    @Override
    protected SettleCasesContract.Presenter initPresenter() {
        return new SettleCasesPresenter(this);
    }

    @Override
    public void onLoadCaseTypeSuccess(GetCaseTypeResultModel result) {
        ResModel<GetCaseTypeResultModel> model = new ResModel<>();
        model.setData(result);
        DialogFragment fragment = new DialogFragment(this, R.id.select_cases_type, model);
        fragment.show(getChildFragmentManager(), SettleCasesFragment.class.getName());
    }

    @Override
    public void onResolveLaborCasesComplete(ResolveLaborCasesResultModel result) {
        ShowCaseResultFragment.setCaseResult(result);
        mAccountTrigger.triggerView(Common.Constance.TO_LOOK_RESULT_FRAGMENT);
    }
}
