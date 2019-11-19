package com.zfb.zhifabao.flags.law;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.activities.ConsultationActivity;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLaborLawResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetRelatedCaseTypeResultModel;
import com.zfb.zhifabao.common.factory.presenter.consultation.ConsultationContract;
import com.zfb.zhifabao.common.factory.presenter.consultation.ConsultationPresenter;
import com.zfb.zhifabao.flags.account.AccountTrigger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zfb.zhifabao.common.factory.Factory.getGson;

public class ConsultationFragment extends PresenterFragment<ConsultationContract.Presenter> implements
        ConsultationContract.View,
        DialogFragment.OnSelectedCallback, Common.Constance {
    @BindView(R.id.select_city)
    TextView tv_city;
    @BindView(R.id.select_type)
    TextView tv_type;


    private int mTemp;
    private AccountTrigger mAccountTrigger;

    public ConsultationFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAccountTrigger = (AccountTrigger) context;

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_consulation;
    }

    @OnClick(R.id.select_city)
    void selectCity() {
        mTemp = R.id.select_city;
        List<String> strLists = new ArrayList<>();
        strLists.add("深圳");
        ResModel model = new ResModel();
        model.setResult(strLists);
        DialogFragment fragment = new DialogFragment(this, mTemp, model);
        fragment.show(getChildFragmentManager(), ConsultationFragment.class.getName());
    }

    @OnClick(R.id.select_process_documents)
    void select_process_documents() {
        mPresenter.loadProcessDocuments();
    }

    @OnClick(R.id.select_type)
    void selectType() {
        mPresenter.loadCase();
    }

    @OnClick(R.id.im_back)
    void onBack() {
        getActivity().finish();
    }

    @OnClick(R.id.btn_law_find)
    void findLaw() {
        if (!TextUtils.isEmpty(tv_city.getText().toString().trim()) && !tv_city.getText().toString().contains("请选择城市")) {
            mPresenter.loadLaw(tv_city.getText().toString().trim());
        } else {
            Application.showToast("请先选择城市！");
        }
    }

    @Override
    public void selected(String str, int temp) {
        switch (temp) {
            case R.id.select_city:
                tv_city.setText(str);
                tv_city.setTextColor(Color.BLACK);
                break;
            case R.id.select_type:
                tv_type.setText(str);
                tv_type.setTextColor(Color.BLACK);
                break;
        }
    }

    @Override
    public void onLoadLawSuccess(ResModel result) {
        mAccountTrigger.triggerView(TO_SHOW_LAW_FRAGMENT);
        GetLaborLawResultModel temp = getGson().fromJson(result.getResult().toString(), GetLaborLawResultModel.class);
        temp.setCity(tv_city.getText().toString().trim());
        result.setResult(temp);
        ShowLawFragment.setResult(result);
    }

    @Override
    public void onLoadCaseSuccess(ResModel result) {
        GetRelatedCaseTypeResultModel model = getGson().fromJson(result.getResult().toString(), GetRelatedCaseTypeResultModel.class);
        ResModel<GetRelatedCaseTypeResultModel> resModel = result;
        resModel.setResult(model);
        DialogFragment fragment = new DialogFragment(this, R.id.select_type, result);
        fragment.show(getChildFragmentManager(), ConsultationFragment.class.getName());
    }

    @Override
    public void onLoadProcessDocumentsSuccess(ResModel result) {
        DialogFragment fragment = new DialogFragment(this, R.id.select_process_documents, result);
        fragment.show(getChildFragmentManager(), ConsultationActivity.class.getName());
    }

    @Override
    protected ConsultationContract.Presenter initPresenter() {
        return new ConsultationPresenter(this);
    }
}
