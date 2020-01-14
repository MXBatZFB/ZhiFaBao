package com.zfb.zhifabao.flags.contract.custom;


import android.content.Context;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.OnClick;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class SetPartyBInfoFragment extends Fragment {
    @BindView(R.id.appbar)
    AppBarLayout appBar;

    @BindView(R.id.et_name)
    EditText mName;

    @BindView(R.id.et_number_id)
    EditText mNumberId;

    @BindView(R.id.et_household_registration)
    EditText mHouseholdRegistration;

    @BindView(R.id.et_address)
    EditText mAddress;

    @BindView(R.id.et_contract_number)
    EditText mContractNumber;

    private String name;
    private String numberId;
    private String householdRegistration;
    private String address;
    private  String contractNumber;

    private CommonTrigger mCommonTrigger;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCommonTrigger = (CommonTrigger) context;
    }

    public SetPartyBInfoFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_set_party_binfo;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        CustomContractModel model = CustomContractModel.getInstance();
        CustomContractModel.NecessaryClausesBean.PartyBInfoBean bInfoBean = new CustomContractModel.NecessaryClausesBean.PartyBInfoBean();
        bInfoBean.setContactNumber("13825735292");
        model.getNecessaryClauses().setPartyBInfo(bInfoBean);
        Log.e("delong","CustomContractModel_hashCode>>>>>>>>>>>>>>>>>"+model);
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
             CustomContractModel.getInstance().getNecessaryClauses().getPartyBInfo().setContactNumber(contractNumber);
            CustomContractModel.getInstance().getNecessaryClauses().getPartyBInfo().setHouseholdRegister(householdRegistration);
            CustomContractModel.getInstance().getNecessaryClauses().getPartyBInfo().setIdNumber(numberId);
            CustomContractModel.getInstance().getNecessaryClauses().getPartyBInfo().setName(name);
            CustomContractModel.getInstance().getNecessaryClauses().getPartyBInfo().setResidentialAddress(address);
            mCommonTrigger.triggerView(Common.Constance.TO_SET_WORK_TIME_AND_PLACE);
        }

    }


    private boolean checkParameter() {
        name = mName.getText().toString().trim();
        address = mAddress.getText().toString().trim();
        householdRegistration = mHouseholdRegistration.getText().toString().trim();
        numberId = mNumberId.getText().toString().trim();
        contractNumber = mContractNumber.getText().toString().trim();
        return name.length()>0&&
                address.length()>0&&
                householdRegistration.length()>0&&
                numberId.length()>0&&contractNumber.length()>0;
    }

    @OnClick(R.id.btn_previous)
    void previous(){
        mCommonTrigger.triggerView(Common.Constance.TO_CONTRACT_FORMULATION_SET_PARTY_A_INFO);
    }

    @OnClick(R.id.im_back)
    void back(){
        mCommonTrigger.triggerView(Common.Constance.TO_CONTRACT_FORMULATION_SET_PARTY_A_INFO);
    }

    @Override
    public boolean onBackPressed() {
        mCommonTrigger.triggerView(Common.Constance.TO_CONTRACT_FORMULATION_SET_PARTY_A_INFO);
        return true;
    }

}
