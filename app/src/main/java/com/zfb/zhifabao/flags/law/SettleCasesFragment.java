package com.zfb.zhifabao.flags.law;

import android.widget.TextView;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseTypeResultModel;
import com.zfb.zhifabao.common.factory.presenter.cases.SettleCasesContract;
import com.zfb.zhifabao.common.factory.presenter.cases.SettleCasesPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class SettleCasesFragment extends PresenterFragment<SettleCasesContract.Presenter>
        implements DialogFragment.OnSelectedCallback,
        SettleCasesContract.View {
    @BindView(R.id.select_cases_type)
    TextView tv_cases_type;

    public SettleCasesFragment() {
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_settle_case;
    }

    @OnClick(R.id.select_cases_type)
    void selectCasesType() {
        mPresenter.loadCaseType();
    }

    @OnClick(R.id.im_back)
    void onBack() {
        getActivity().finish();
    }

    @Override
    public void selected(String str, int temp) {
        tv_cases_type.setText(str);
    }

    @Override
    protected SettleCasesContract.Presenter initPresenter() {
        return new SettleCasesPresenter(this);
    }

    @Override
    public void onLoadCaseTypeSuccess(GetCaseTypeResultModel result) {
        ResModel<GetCaseTypeResultModel> model = new ResModel<>();
        model.setResult(result);
        DialogFragment fragment = new DialogFragment(this, R.id.select_cases_type, model);
        fragment.show(getChildFragmentManager(), SettleCasesFragment.class.getName());
    }
}
