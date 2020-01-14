package com.zfb.zhifabao.flags.law.consultation;

import android.view.View;
import android.widget.TextView;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.app.Fragment;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLabourLawContentByIdResultModel;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LookContentWithLawFragment extends Fragment {

    @BindView(R.id.show_law_title)
    TextView tvTitle;
    @BindView(R.id.tv_law_content)
    TextView tvContent;


    public static GetLabourLawContentByIdResultModel resultModel;
    public LookContentWithLawFragment() {
    }

    public static void setData(GetLabourLawContentByIdResultModel model){
        resultModel = model;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_look_content_with_law;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        initData();
    }

    private void initData() {
        if (resultModel!=null){
            tvContent.setText(resultModel.getContent());
            tvTitle.setText(resultModel.getType());
        }
    }
}
