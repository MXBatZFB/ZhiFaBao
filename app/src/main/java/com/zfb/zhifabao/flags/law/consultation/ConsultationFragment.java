package com.zfb.zhifabao.flags.law.consultation;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.activities.LookListActivity;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.law.GetCityWithProvinceIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.law.GetRegionResultModel;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;
import com.zfb.zhifabao.common.factory.presenter.consultation.ConsultationContract;
import com.zfb.zhifabao.common.factory.presenter.consultation.ConsultationPresenter;
import com.zfb.zhifabao.flags.account.AccountTrigger;
import com.zfb.zhifabao.flags.law.DialogFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class ConsultationFragment extends PresenterFragment<ConsultationContract.Presenter> implements DialogFragment.OnSelectedCallback,ConsultationContract.View {
    @BindView(R.id.select_law_type)
    TextView tv_type;
    @BindView(R.id.select_city)
    TextView tv_city;
    @BindView(R.id.select_controversy_type)
    TextView tv_controversy;
    @BindView(R.id.select_precess)
    TextView tv_precess;
    private DialogFragment regionFragment;
    private int mTemp;
    private AccountTrigger mAccountTrigger;
    private String mCityId;



    public ConsultationFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    protected ConsultationContract.Presenter initPresenter() {
        return new ConsultationPresenter(this);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_consulation;
    }

    @OnClick(R.id.im_back)
    void back(){
        getActivity().finish();
    }

    /**
     * 选择相关劳动法类型
     */
    @OnClick(R.id.select_law_type)
    void selectLaborLawType() {
        mPresenter.loadCategoryOfLabourLawList();
    }

    /**
     * 根据相关劳动法的类型查询相关内容
     */
    @OnClick(R.id.btn_law_find_type)
    void findLawWithType(){
        String lawType = tv_type.getText().toString().trim();
        if (lawType.length()>0){
            mPresenter.loadLawListByType(lawType);
        }else {
            showError("请先选择相关法律的类别");
        }
    }

    /**
     * 选择你要查询的城市/地区
     */
    @OnClick(R.id.select_city)
    void selectCity() {
        mPresenter.loadProvinceList();
    }

    /**
     * 根据城市查询当地劳动法条例
     */
    @OnClick(R.id.btn_law_find)
    void findLawWithCity(){
        String city = tv_city.getText().toString().trim();
        if (city.length()>0){
            mPresenter.loadLawListByRegionId(mCityId);
        }else {
            showError("请先选择你要查询的城市");
        }
    }

    /**
     * 选择争议类型
     */
    @OnClick(R.id.select_controversy_type)
    void selectControversyType() {
        mPresenter.loadControversyTypeList();
    }

    /**
     * 根据争议类型查询相关案列
     */
    @OnClick(R.id.btn_case_find)
    void findCaseWithType(){
        String caseType = tv_controversy.getText().toString().trim();
        if (caseType.length()>0){
            mPresenter.loadCaseListByType(caseType);
        }else {
            showError("请先选择争议类型");
        }
    }

    /**
     * 选择劳动法解决的法律流程
     */
    @OnClick(R.id.select_precess)
    void selectedLawPress(){

    }

    /**
     * 根据法律流程类型获取流程介绍信息
     */
    @OnClick(R.id.btn_precess_find)
    void findLawPress(){
        String precess_type = tv_precess.getText().toString().trim();
        if (precess_type.length()>0){
            //mPresenter.loadPrecessInfoByType(precess_type);TODO
        }else {
            showError("请先选择流程类型");
        }
    }


    /**
     * Dilog Item被点击后接口的具体实现
     * @param str
     * @param temp
     */
    @Override
    public void selected(String parameterInfo ,String str, int temp) {
        switch (temp){
            case 999:
                mCityId = parameterInfo;
                tv_city.setText(str);
                tv_city.setTextColor(Color.parseColor("#333333"));
                break;
            case R.id.select_law_type:
                tv_type.setText(parameterInfo);
                tv_type.setTextColor(Color.parseColor("#333333"));
                break;
            case R.id.select_controversy_type:
                tv_controversy.setText(parameterInfo);
                tv_controversy.setTextColor(Color.parseColor("#333333"));
            default:
                break;
        }
    }

    /**
     * 获取生份/地区 信息成功的处理方法
     * @param result
     */
    @Override
    public void onLoadProvinceSuccess(ResModel result) {
        regionFragment = new DialogFragment(this,999,result);
        regionFragment.setPresenter(mPresenter);
        regionFragment.show(getChildFragmentManager(),ConsultationFragment.class.getName());
    }

    /**
     * 获取城市信息成功时的处理方法
     * @param resModel
     */
    @Override
    public void onLoadCitySuccess(ResModel resModel) {
        regionFragment.refreshProvinceToCity((GetCityWithProvinceIdResultModel) resModel.getData());
    }

    /**
     * 获取相关劳动法种类成功时的处理方法
     * @param resModel
     */
    @Override
    public void onLoadCategoryOfLabourLawSuccess(ResModel resModel) {
             DialogFragment dialogFragment = new DialogFragment(this,R.id.select_law_type,resModel);
             dialogFragment.show(getChildFragmentManager(),ConsultationFragment.class.getName());
    }

    @Override
    public void onLoadLawListWithTypeSuccess(ResModel resModel) {
        //跳转到显示相关法律条例额列表的Fragment
        LookListActivity.show(getContext(),resModel);
    }

    @Override
    public void onLoadControversyTypeListSuccess(ResModel resModel) {
        DialogFragment dialogFragment = new DialogFragment(this,R.id.select_controversy_type,resModel);
        dialogFragment.show(getChildFragmentManager(),ConsultationFragment.class.getName());
    }

    @Override
    public void onLoadLawListByRegionIdSuccess(ResModel resModel) {
        LookListActivity.show(getContext(),resModel);
    }

    @Override
    public void onLoadCaseListByTypeSuccess(ResModel resModel) {
        LookListActivity.show(getContext(),resModel);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }
}
