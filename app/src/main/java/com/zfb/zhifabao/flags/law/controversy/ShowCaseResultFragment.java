package com.zfb.zhifabao.flags.law.controversy;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.model.api.cases.GetPlanForIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.cases.ResolveLaborCasesResultModel;
import com.zfb.zhifabao.common.factory.presenter.cases.ShowCaseContract;
import com.zfb.zhifabao.common.factory.presenter.cases.ShowCasePresenter;
import com.zfb.zhifabao.common.widget.cyclerview.RecyclerAdapter;
import com.zfb.zhifabao.flags.account.AccountTrigger;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;


public class ShowCaseResultFragment extends PresenterFragment<ShowCaseContract.Presenter> implements ShowCaseContract.View {
    public static ResolveLaborCasesResultModel model;
    @BindView(R.id.container_plan)
    RecyclerView plan_rv;
    PlanAdapter mAdapter;
    private boolean isFirst =true;
    private AccountTrigger mAccountTrigger;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    protected ShowCaseContract.Presenter initPresenter() {
        return new ShowCasePresenter(this);
    }

    public ShowCaseResultFragment() {
        // Required empty public constructor
    }

    public static void setCaseResult(ResolveLaborCasesResultModel result){
        model = result;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mAdapter = new PlanAdapter();
        plan_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        plan_rv.setAdapter(mAdapter);
        mAdapter.replace(model.getCaseSolutionList());
    }



    @Override
    public void onResume() {
        super.onResume();
        mAdapter.replace(model.getCaseSolutionList());
        isFirst = true;
    }

    @Override
    public boolean onBackPressed() {
        mAccountTrigger.triggerView(Common.Constance.TO_SETTLE_CASES_FRAGMENT);
        return true;
    }

    @OnClick(R.id.im_back)
    void back(){
        mAccountTrigger.triggerView(Common.Constance.TO_SETTLE_CASES_FRAGMENT);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_show_case_result;
    }

    @Override
    public void onLoadPlanPlanSuccess(GetPlanForIdResultModel model) {
        LookPlanFragment.setPlan(model);
        mAccountTrigger.triggerView(Common.Constance.TO_LOOK_FRAGMENT);
    }

    private class PlanAdapter extends RecyclerAdapter<ResolveLaborCasesResultModel.CaseSolutionListBean>{
        @Override
        protected int getItemViewType(int viewType, ResolveLaborCasesResultModel.CaseSolutionListBean caseSolutionListBean) {
            return R.layout.cell_plan;
        }

        @Override
        protected ViewHolder<ResolveLaborCasesResultModel.CaseSolutionListBean> onCreateViewHolder(View root, int viewType) {
            return new PlanViewHolder(root);
        }

        private class PlanViewHolder extends ViewHolder<ResolveLaborCasesResultModel.CaseSolutionListBean> {
            private TextView tvTitle;
            private TextView tvRcommended;
            private Button btnLook;

            public PlanViewHolder(@NonNull View itemView) {
                super(itemView);
                 tvTitle = itemView.findViewById(R.id.tvTitle);
                 tvRcommended = itemView.findViewById(R.id.tvRcommended);
                 btnLook = itemView.findViewById(R.id.btn_look);
                 btnLook.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         mPresenter.loadTextTemPlan(mData.getId()+"");
                     }
                 });
            }

            @Override
            protected void onBind(ResolveLaborCasesResultModel.CaseSolutionListBean caseSolutionListBean) {
                tvTitle.setText(caseSolutionListBean.getTitle().trim());
                if (isFirst){
                    isFirst = false;
                    tvRcommended.setText("强烈推荐");
                    tvRcommended.setBackgroundColor(Color.parseColor("#FF6464"));
                }else {
                    tvRcommended.setText("一般推荐");
                    tvRcommended.setBackgroundColor(Color.parseColor("#1EC0AE"));
                }
            }
        }
    }
}
