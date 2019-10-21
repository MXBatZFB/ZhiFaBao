package com.zfb.zhifabao.flags.law;

import android.graphics.Color;
import android.widget.TextView;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.app.Fragment;

import butterknife.BindView;
import butterknife.OnClick;

public class ConsultationFragment extends Fragment implements DialogFragment.OnSelectedCallback {
    @BindView(R.id.select_city)
    TextView tv_city;
    @BindView(R.id.select_type)
    TextView tv_type;

    public ConsultationFragment() {
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_consulation;
    }

    @OnClick(R.id.select_city)
    void selectCity() {
        DialogFragment fragment = new DialogFragment(this, R.id.select_city);
        fragment.show(getChildFragmentManager(), ConsultationFragment.class.getName());
    }

    @OnClick(R.id.select_type)
    void selectType() {
        DialogFragment fragment = new DialogFragment(this, R.id.select_type);
        fragment.show(getChildFragmentManager(), ConsultationFragment.class.getName());
    }

    @OnClick(R.id.im_back)
    void onBack() {
        getActivity().finish();
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
}
