package com.zfb.zhifabao.flags.contract.custom;

import android.content.Context;
import butterknife.BindView;
import butterknife.OnClick;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
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
import java.util.ArrayList;


public class SetPartyAInfoFragment extends Fragment {
    @BindView(R.id.appbar)
    AppBarLayout appBar;
    @BindView(R.id.et_company_name)
    EditText mCompanyName;

    @BindView(R.id.et_credit_code)
    EditText mCreditCode;

    @BindView(R.id.et_legal_representative)
    EditText mLegalRepresentative;

    @BindView(R.id.et_register_place)
    EditText mRegisterPlace;

    @BindView(R.id.et_premises)
    EditText mPremises;

    @BindView(R.id.et_contract_number)
    EditText mContractNumber;

    private CommonTrigger mCommonTrigger;
    private String companyName;
    private String creditCode;
    private String legalRepresentative;
    private String premises;
    private String registerPlace;
    private String contractNumber;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCommonTrigger = (CommonTrigger) context;
    }

    public SetPartyAInfoFragment() {

    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        CustomContractModel model = initParameter();
        Glide.with(getActivity()).load(R.drawable.icon_bg)
                .into(new ViewTarget<View ,GlideDrawable>(appBar) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setBackground(resource.getCurrent());
                    }
                });
    }

    private CustomContractModel initParameter() {
        CustomContractModel model = CustomContractModel.getInstance();
        CustomContractModel.NecessaryClausesBean bean = new CustomContractModel.NecessaryClausesBean();
        bean.setPartyAInfo( new CustomContractModel.NecessaryClausesBean.PartyAInfoBean());
        bean.setPartyBInfo(new CustomContractModel.NecessaryClausesBean.PartyBInfoBean());
        model.setExtraClausesList(new ArrayList<>());
        model.setNecessaryClauses(bean);
        return model;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_set_party_ainfo;
    }

    @OnClick(R.id.btn_next)
    void doNex(){
        if (checkParameter()){
            CustomContractModel model = CustomContractModel.getInstance();
            model .getNecessaryClauses().getPartyAInfo().setCompanyName(companyName);
            model .getNecessaryClauses().getPartyAInfo().setLegalPersonName(legalRepresentative);
            model .getNecessaryClauses().getPartyAInfo().setContactNumber(contractNumber);
            model .getNecessaryClauses().getPartyAInfo().setRegistration(registerPlace);
            model .getNecessaryClauses().getPartyAInfo().setPlaceOfBusiness(premises);
            model .getNecessaryClauses().getPartyAInfo().setUnicode(creditCode);
            mCommonTrigger.triggerView(Common.Constance.TO_CONTRACT_FORMULATION_SET_PARTY_B_INFO);
            Log.e("delong","CustomContractModel_hashCode>>>>>>>>>>>>>>>>>"+model);
        }else {
            Toast.makeText(getActivity(),"所有参数不能为空！",Toast.LENGTH_LONG).show();
        }

    }

    private boolean checkParameter() {
        companyName = mCompanyName.getText().toString().trim();
        creditCode = mCreditCode.getText().toString().trim();
        legalRepresentative = mLegalRepresentative.getText().toString().trim();
         premises = mPremises.getText().toString().trim();
         registerPlace = mRegisterPlace.getText().toString().trim();
         contractNumber = mContractNumber.getText().toString().trim();

        return companyName.length()>0&&
                creditCode.length()>0&&
                legalRepresentative.length()>0&&
                premises.length()>0&&
                registerPlace.length()>0&&
                contractNumber.length()>0;
    }

    @OnClick(R.id.im_back)
    void back(){
         getActivity().finish();
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
