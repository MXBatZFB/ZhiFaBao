package com.zfb.zhifabao.flags.contract.custom;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.google.android.material.appbar.AppBarLayout;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Fragment;
import com.zfb.zhifabao.common.factory.model.api.contract.CustomContractModel;
import com.zfb.zhifabao.flags.main.CommonTrigger;
import butterknife.BindView;
import butterknife.OnClick;

public class SetWorkTimeAndPlaceFragment extends Fragment {
    @BindView(R.id.appbar)
    AppBarLayout appBar;

    @BindView(R.id.et_post)
    EditText etPost;

    @BindView(R.id.et_duties)
    EditText etDuties;

    @BindView(R.id.et_workPlace)
    EditText etWorkPlace;

    private String post;
    private String duties;
    private String workPlace;

    private CommonTrigger mCommonTrigger;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCommonTrigger = (CommonTrigger) context;
    }

    public SetWorkTimeAndPlaceFragment() {
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_set_work_time_and_place;
    }
    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        Glide.with(getActivity()).load(R.drawable.icon_bg)
                .into(new ViewTarget<View , GlideDrawable>(appBar) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setBackground(resource.getCurrent());
                    }
                });
    }

    @OnClick(R.id.btn_next)
    void doNex(){
        if (checkParameter()){
            CustomContractModel.getInstance().getNecessaryClauses().setWorkPlace(workPlace);
            CustomContractModel.getInstance().getNecessaryClauses().setDuties(duties);
            CustomContractModel.getInstance().getNecessaryClauses().setPost(post);
            mCommonTrigger.triggerView(Common.Constance.TO_SET_CONTRACT_TIME_LIMIT);
        }

    }
    private boolean checkParameter() {
        post = etPost.getText().toString().trim();
        duties = etDuties.getText().toString().trim();
        workPlace = etWorkPlace.getText().toString().trim();

        return post.length()>0&&
                duties.length()>0&&
                workPlace.length()>0;
    }

    @OnClick(R.id.btn_previous)
    void previous(){
        mCommonTrigger.triggerView(Common.Constance.TO_CONTRACT_FORMULATION_SET_PARTY_B_INFO);
    }

    @OnClick(R.id.im_back)
    void back(){
        mCommonTrigger.triggerView(Common.Constance.TO_CONTRACT_FORMULATION_SET_PARTY_B_INFO);
    }

    @Override
    public boolean onBackPressed() {
        mCommonTrigger.triggerView(Common.Constance.TO_CONTRACT_FORMULATION_SET_PARTY_B_INFO);
        return true;
    }
}
