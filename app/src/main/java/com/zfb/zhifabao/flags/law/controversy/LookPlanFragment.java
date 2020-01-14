package com.zfb.zhifabao.flags.law.controversy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseSuggestionResultModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetPlanForIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLookLawResultModel;
import com.zfb.zhifabao.common.factory.presenter.look.LookContract;
import com.zfb.zhifabao.common.factory.presenter.look.LookPresenter;
import com.zfb.zhifabao.common.widget.app.ObservableScrollView;
import com.zfb.zhifabao.flags.account.AccountTrigger;
import butterknife.BindView;
import butterknife.OnClick;

@SuppressLint("ValidFragment")
public class LookPlanFragment extends PresenterFragment<LookContract.Presenter> implements LookContract.View {
    @BindView(R.id.scrollView)
    ObservableScrollView scrollView;
    @BindView(R.id.tv_plan_title)
    TextView tvPlanTitle;

    @BindView(R.id.tvTile)
    TextView tvTitle;

    @BindView(R.id.tv_plan_content)
    TextView tvPlanContent;

    @BindView(R.id.tv_lawyers_suggested)
    TextView tvLawyersSuggested;

    @BindView(R.id.tv_case_analysis)
    TextView tvCaseAnalysis;

    public static  GetPlanForIdResultModel model;
    private AccountTrigger mAccountTrigger;

    public LookPlanFragment() {

    }


    public static void setPlan(GetPlanForIdResultModel resultModel) {
       model = resultModel;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_plan_look;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAccountTrigger = (AccountTrigger) context;
    }

    @OnClick(R.id.im_back)
    void onBack() {
        mAccountTrigger.triggerView(Common.Constance.TO_LOOK_RESULT_FRAGMENT);
        model=null;
    }

    @Override
    public boolean onBackPressed() {
        mAccountTrigger.triggerView(Common.Constance.TO_LOOK_RESULT_FRAGMENT);
        model=null;
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
    //    ScrollBindHelper.bind(seekBar, scrollView);
        Log.e("delong","model>>>>>>>>>>>>>>>>"+model);
        tvTitle.setText(String.format("第%s方案",model.getTitle().substring(2,3)));
        tvPlanTitle.setText(model.getTitle());
        tvPlanContent.setText(model.getRegulation());
        tvLawyersSuggested.setText(model.getCaseSolved().getAdvice());
        tvCaseAnalysis.setText(model.getCaseSolved().getContent());
    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText(String.format("第%s方案",model.getTitle().substring(2,3)));
        tvPlanTitle.setText(model.getTitle());
        tvPlanContent.setText(model.getRegulation());
        tvLawyersSuggested.setText(model.getCaseSolved().getAdvice());
        tvCaseAnalysis.setText(model.getCaseSolved().getContent());
    }

    @Override
    protected LookContract.Presenter initPresenter() {
        return new LookPresenter(this);
    }

    @Override
    public void onDownSuccess() {
    }

    @Override
    public void onLookSuccess(GetLookLawResultModel model) {
      //  tvLook.setText(model.getContent());
      //  tvTitle.setText(model.getFilename());
    }

    @Override
    public void onLookSuggestionSuccess(GetCaseSuggestionResultModel model) {

    }
}
