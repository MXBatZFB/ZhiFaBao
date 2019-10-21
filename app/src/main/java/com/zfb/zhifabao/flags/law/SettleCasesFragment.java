package com.zfb.zhifabao.flags.law;

import android.widget.TextView;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.app.Fragment;

import butterknife.BindView;
import butterknife.OnClick;

public class SettleCasesFragment extends Fragment implements DialogFragment.OnSelectedCallback {
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
        DialogFragment fragment = new DialogFragment(this, R.id.select_cases_type);
        fragment.show(getChildFragmentManager(), SettleCasesFragment.class.getName());
    }

    @OnClick(R.id.im_back)
    void onBack() {
        getActivity().finish();
    }

    @Override
    public void selected(String str, int temp) {
        tv_cases_type.setText(str);
    }
}
