package com.zfb.zhifabao.flags.contract.custom;

import android.util.Log;
import android.view.View;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.app.Fragment;
import com.zfb.zhifabao.common.factory.model.api.contract.CustomContractModel;

public class ContractFormulationFragment extends Fragment {
    public ContractFormulationFragment() {
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_contract_formulation;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        CustomContractModel model = CustomContractModel.getInstance();
        Log.e("delong","CustomContractModel_hashCode>>>>>>>>>>>>>>>>>"+model.hashCode());
    }


}
