package com.zfb.zhifabao.flags.law;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseSuggestionModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseSuggestionResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLookLawResultModel;
import com.zfb.zhifabao.common.factory.presenter.look.LookContract;
import com.zfb.zhifabao.common.factory.presenter.look.LookPresenter;
import com.zfb.zhifabao.common.widget.app.ObservableScrollView;
import com.zfb.zhifabao.common.widget.app.ScrollBindHelper;
import com.zfb.zhifabao.common.widget.app.VerticalSeekBar;
import com.zfb.zhifabao.flags.account.AccountTrigger;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zfb.zhifabao.common.Common.Constance.CASE_LAW;
import static com.zfb.zhifabao.common.Common.Constance.CASE_SUGGESTION;
import static com.zfb.zhifabao.common.Common.Constance.LOCAL_LAW;
import static com.zfb.zhifabao.common.Common.Constance.PROCESS_DOCUMENTS;
import static com.zfb.zhifabao.common.Common.Constance.TO_CONSULTATION_FRAGMENT;
import static com.zfb.zhifabao.common.Common.Constance.TO_SETTLE_CASES_FRAGMENT;
import static com.zfb.zhifabao.common.Common.Constance.TO_SHOW_LAW_FRAGMENT;

@SuppressLint("ValidFragment")
public class LookFragment extends PresenterFragment<LookContract.Presenter> implements LookContract.View {
    private static String mLawName;
    private static String mType;
    private static String mCaseType;
    @BindView(R.id.lookView)
    TextView tvLook;
    @BindView(R.id.scrollView)
    ObservableScrollView scrollView;
    @BindView(R.id.show_look_title)
    TextView tvTitle;
    @BindView(R.id.seekBar_main_scrollThumb)
    VerticalSeekBar seekBar;
    private AccountTrigger mAccountTrigger;

    public LookFragment() {

    }

    public static void setCaseType(String caseType) {
        mCaseType = caseType;
    }

    public static void setLawName(String lawName, String type) {
        mLawName = lawName;
        mType = type;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_look;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("delong", "mType>>>>>>>>>>>>>>>>>>>>>>>>>>" + mType);
        if (mType.equals(CASE_LAW) || mType.equals(LOCAL_LAW) || mType.equals(PROCESS_DOCUMENTS)) {
            mPresenter.lookLaw(mLawName);
        } else {
            GetCaseSuggestionModel model = new GetCaseSuggestionModel(mCaseType, mLawName);
            mPresenter.lookCaseSuggestion(model);
        }

    }

    @OnClick(R.id.im_back)
    void onBack() {
        if (mType.equals(LOCAL_LAW)) {
            mAccountTrigger.triggerView(TO_SHOW_LAW_FRAGMENT);
        } else if (mType.equals(CASE_SUGGESTION)) {
            mAccountTrigger.triggerView(TO_SETTLE_CASES_FRAGMENT);
        } else {
            mAccountTrigger.triggerView(TO_CONSULTATION_FRAGMENT);
        }
    }

    @Override
    public boolean onBackPressed() {
        if (mType.equals(LOCAL_LAW)) {
            mAccountTrigger.triggerView(TO_SHOW_LAW_FRAGMENT);
        } else if (mType.equals(CASE_SUGGESTION)) {
            mAccountTrigger.triggerView(TO_SETTLE_CASES_FRAGMENT);
        } else {
            mAccountTrigger.triggerView(TO_CONSULTATION_FRAGMENT);
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        ScrollBindHelper.bind(seekBar, scrollView);
        if (mType.equals(CASE_LAW) || mType.equals(LOCAL_LAW) || mType.equals(PROCESS_DOCUMENTS)) {
            mPresenter.lookLaw(mLawName);
        } else {
            GetCaseSuggestionModel model = new GetCaseSuggestionModel(mCaseType, mLawName);
            mPresenter.lookCaseSuggestion(model);
        }
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
        tvLook.setText(model.getContent());
        tvTitle.setText(model.getFilename());
    }

    @Override
    public void onLookSuggestionSuccess(GetCaseSuggestionResultModel model) {
        tvLook.setText(model.getSuggestion());
        tvTitle.setText(String.format("%s案列的建议：", mLawName));
    }
}
