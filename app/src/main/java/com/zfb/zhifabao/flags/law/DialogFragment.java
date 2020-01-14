package com.zfb.zhifabao.flags.law;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseTypeResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetCategoryOfLabourLawResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetControversyTypeListResultModel;
import com.zfb.zhifabao.common.factory.model.api.law.GetCityWithProvinceIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.law.GetRegionResultModel;
import com.zfb.zhifabao.common.factory.presenter.BasePresenter;
import com.zfb.zhifabao.common.factory.presenter.consultation.ConsultationContract;
import com.zfb.zhifabao.common.tools.UiTool;
import com.zfb.zhifabao.common.utils.ChineseCharToEn;
import com.zfb.zhifabao.common.widget.cyclerview.RecyclerAdapter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * 所有法律相关需要数据显示的Dialog
 */
@SuppressLint("ValidFragment")
public class DialogFragment extends BottomSheetDialogFragment implements Common.Constance, View.OnClickListener {
    private RecyclerAdapter<GetCaseTypeResultModel.CaseTypeListBean> mCaseTypeAdapter;
    private RecyclerAdapter<String> mCommonAdapter;
    //    private RecyclerAdapter<String> mMenuAdapter;
    private RecyclerAdapter mRegionInfoAdapter;
    private OnSelectedCallback mCallback;
    private ResModel mResModel;
    private int temp;
    private ArrayList positions = new ArrayList();
    private List<GetRegionResultModel.ProvinceListBean> provinceListBeans;
    private boolean isFirst = true;
    private ConsultationContract.Presenter mPresenter;
    private ImageView imBack;
    private ImageView imDismiss;
    private TextView tvTitle;
    private TextView tvProvince;
    private boolean isShowCity= false;
    private List<GetCityWithProvinceIdResultModel.CityListBean> cityListBeans;
    private  RecyclerView mRegionInfoRecyclerView;


    public void refreshProvinceToCity(GetCityWithProvinceIdResultModel model){
        List<String> menuInfo = new ArrayList<>();
        cityListBeans = model.getCityList();
        //进行中文首字母排序
        Collections.sort(cityListBeans, new Comparator<GetCityWithProvinceIdResultModel.CityListBean>() {
            @Override
            public int compare(GetCityWithProvinceIdResultModel.CityListBean o1, GetCityWithProvinceIdResultModel.CityListBean o2) {
                return Collator.getInstance(Locale.CHINA).compare(o1.getName(), o2.getName());
            }
        });

        int i = -1;
        for (GetCityWithProvinceIdResultModel.CityListBean bean : cityListBeans) {
            i++;
            String str = ChineseCharToEn.getFirstLetter(bean.getName());
            if (!menuInfo.contains(str.toUpperCase())) {
                positions.add(i);
                menuInfo.add(str.toUpperCase());
            }
        }
        mRegionInfoAdapter.replace(cityListBeans);
        mRegionInfoAdapter.notifyDataSetChanged();
        mRegionInfoRecyclerView.scrollToPosition(0);
        imBack.setVisibility(View.VISIBLE);
    }

    public void setPresenter(ConsultationContract.Presenter presenter){
        this.mPresenter = presenter;
    }

    public DialogFragment(OnSelectedCallback mCallback, int temp, ResModel result) {
        this.mCallback = mCallback;
        mResModel = result;
        this.temp = temp;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.e("delong", "onCreateDialog");
        return new TransStatusBottomSheetDialog(getContext());
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("delong", "onCreateDialog");
        View root = null;
        if (temp != 999) {
            root = inflater.inflate(R.layout.fragment_common, container, false);
        } else {
            root = inflater.inflate(R.layout.fragment_province_or_city, container, false);
        }
        initView(root);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("delong", "onStart");
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        FrameLayout bottomSheet = dialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet);
        bottomSheet.setBackgroundResource(R.color.trans);
        if (bottomSheet != null) {
            BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
            behavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        }
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("delong", "onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("delong", "onDestroy");
    }


    /**
     * 根据temp初始化相应的Dialog界面，以及View组件
     * @param root
     */
    private void initView(View root) {
        if (temp == R.id.select_cases_type) {
            RecyclerView mCaseTypeRecyclerView = root.findViewById(R.id.content_select_recyclerView);
            mCaseTypeAdapter = new CasesTypeAdapter();
            mCaseTypeAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<GetCaseTypeResultModel.CaseTypeListBean>() {
                @Override
                public void onItemClick(RecyclerAdapter.ViewHolder holder, GetCaseTypeResultModel.CaseTypeListBean caseTypeListBean, int position) {
                    mCallback.selected(caseTypeListBean.getType(),"", temp);
                    dismiss();
                }
            });
            mCaseTypeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mCaseTypeRecyclerView.setAdapter(mCaseTypeAdapter);
        }else if (temp == 999) {//表示显示选择城市
            imBack = root.findViewById(R.id.im_back);
            imDismiss = root.findViewById(R.id.im_dismiss);
            imBack.setVisibility(View.INVISIBLE);
            tvTitle = root.findViewById(R.id.tv_select_title);
            tvProvince = root.findViewById(R.id.tvProvince);
            imBack.setOnClickListener(this);
            imDismiss.setOnClickListener(this);
            mRegionInfoRecyclerView = root.findViewById(R.id.city_or_province_selected_recyclerView);
            LinearLayoutManager regionManager = new LinearLayoutManager(getActivity());
            mRegionInfoRecyclerView.setLayoutManager(regionManager);
            mRegionInfoAdapter = new RegionInfoAdapter();
            //首字母导航栏监听
            mRegionInfoAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl() {
                @Override
                public void onItemClick(RecyclerAdapter.ViewHolder holder, Object obj, int position) {
                    isShowCity = true;
                    if (obj instanceof GetRegionResultModel.ProvinceListBean){//需要显示省/地区信息的情况
                        GetRegionResultModel.ProvinceListBean  province = (GetRegionResultModel.ProvinceListBean) obj;
                        mPresenter.loadCityList(province.getId());
                        tvProvince.setText(province.getName());
                        tvProvince.setVisibility(View.VISIBLE);
                        tvTitle.setText("选择城市");
                    }else if (obj instanceof GetCityWithProvinceIdResultModel.CityListBean){//需要显示城市信息的情况
                        GetCityWithProvinceIdResultModel.CityListBean cityListBean = (GetCityWithProvinceIdResultModel.CityListBean) obj;
                        mCallback.selected(cityListBean.getId()+"",cityListBean.getName(),999);
                        dismiss();
                    }
                }
            });
            mRegionInfoRecyclerView.setAdapter(mRegionInfoAdapter);
        } else {
            RecyclerView mCommonRecyclerView = root.findViewById(R.id.content_select_recyclerView);
            mCommonAdapter = new CommonAdapter();
            mCommonAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<String>() {
                @Override
                public void onItemClick(RecyclerAdapter.ViewHolder holder, String s, int position) {
                    mCallback.selected(s,"", temp);
                    dismiss();
                }
            });
            mCommonRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mCommonRecyclerView.setAdapter(mCommonAdapter);
        }
    }


    /**
     * 根据temp初始化相应的数据
     */
    private void initData() {
        if (temp == R.id.select_cases_type) {
            GetCaseTypeResultModel model = (GetCaseTypeResultModel) mResModel.getData();
            mCaseTypeAdapter.replace(model.getCaseTypeList());
        } else if (temp == 999) {
            List<String> menuInfo = new ArrayList<>();
            GetRegionResultModel model = (GetRegionResultModel) mResModel.getData();
            provinceListBeans = model.getProvinceList();
            Collections.sort(provinceListBeans, new Comparator<GetRegionResultModel.ProvinceListBean>() {
                @Override
                public int compare(GetRegionResultModel.ProvinceListBean o1, GetRegionResultModel.ProvinceListBean o2) {
                    return Collator.getInstance(Locale.CHINA).compare(o1.getName(), o2.getName());
                }
            });
            int i = -1;
            for (GetRegionResultModel.ProvinceListBean bean : provinceListBeans) {
                i++;
                String str = ChineseCharToEn.getFirstLetter(bean.getName());
                if (!menuInfo.contains(str.toUpperCase())) {
                    positions.add(i);
                    menuInfo.add(str.toUpperCase());
                }
            }
            //mMenuAdapter.replace(menuInfo);
            provinceListBeans.add(provinceListBeans.size(), new GetRegionResultModel.ProvinceListBean(" ", " "));
            mRegionInfoAdapter.replace(provinceListBeans);
        }else if (temp == R.id.select_law_type){
            List<String> typeNames = new ArrayList<>();
            GetCategoryOfLabourLawResultModel model = (GetCategoryOfLabourLawResultModel) mResModel.getData();
            List<GetCategoryOfLabourLawResultModel.LawTypeListBean> lawTypeListBeans = model.getLawTypeList();
            for (GetCategoryOfLabourLawResultModel.LawTypeListBean bean:lawTypeListBeans) {
                      typeNames.add(bean.getType());
            }
            mCommonAdapter.replace(typeNames);
        }else if (temp == R.id.select_controversy_type){
            List<String> typeNames = new ArrayList<>();
            GetControversyTypeListResultModel model = (GetControversyTypeListResultModel) mResModel.getData();
            List<GetControversyTypeListResultModel.CaseTypeListBean> caseTypeListBeans = model.getCaseTypeList();
            for (GetControversyTypeListResultModel.CaseTypeListBean bean:caseTypeListBeans) {
                typeNames.add(bean.getType());
            }
            mCommonAdapter.replace(typeNames);
        } else {
            mCommonAdapter.replace((Collection<String>) mResModel.getData());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_back:
                if (provinceListBeans.size()>0){
                    mRegionInfoAdapter.replace(provinceListBeans);
                    imBack.setVisibility(View.INVISIBLE);
                    isShowCity = false;
                    tvProvince.setVisibility(View.GONE);
                    tvTitle.setText("选择省份/地区");
                    mRegionInfoRecyclerView.scrollToPosition(0);
                }
                break;
            default:
                dismiss();
                break;
        }
    }

    /**
     * dialog 最终选定的数据item被点击时回调的接口
     */
    public interface OnSelectedCallback {
        void selected(String data,String name, int temp);
    }

    /**
     * 案件类型ViewHolder
     */
    class CasesTypeViewHolder extends RecyclerAdapter.ViewHolder<GetCaseTypeResultModel.CaseTypeListBean> {
        TextView tv_common;

        CasesTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_common = itemView.findViewById(R.id.tv_common);
        }

        @Override
        protected void onBind(GetCaseTypeResultModel.CaseTypeListBean bean) {
            tv_common.setText(bean.getType());
        }
    }

    /**
     * 普通的ViewHolder
     */
    class CommonViewHolder extends RecyclerAdapter.ViewHolder<String> {
        TextView tv_common;

        public CommonViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_common = itemView.findViewById(R.id.tv_common);
        }

        @Override
        protected void onBind(String s) {
            tv_common.setText(s);
        }
    }

    /**
     * 首字母快捷导航的ViewHolder
     */
    class MenuViewHolder extends RecyclerAdapter.ViewHolder<String> {
        TextView tv_menu;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_menu = itemView.findViewById(R.id.tv_menu);
        }

        @Override
        protected void onBind(String s) {
            tv_menu.setText(s);
        }
    }

    /**
     * 省/地区/市数据的ViewHolder
     */
    class RegionInfoViewHolder extends RecyclerAdapter.ViewHolder {
        TextView tvFirst;
        TextView tvName;

        public RegionInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFirst = itemView.findViewById(R.id.tvFirst);
            tvName = itemView.findViewById(R.id.tvName);
        }

        @Override
        protected void onBind(Object obj) {
            if (obj instanceof GetRegionResultModel.ProvinceListBean){//需要显示省/地区信息的情况
                GetRegionResultModel.ProvinceListBean  province = (GetRegionResultModel.ProvinceListBean) obj;
                refreshDialog(province.getName());
            }else if (obj instanceof GetCityWithProvinceIdResultModel.CityListBean){//需要显示城市信息的情况
                GetCityWithProvinceIdResultModel.CityListBean cityListBean = (GetCityWithProvinceIdResultModel.CityListBean) obj;
                refreshDialog(cityListBean.getName());
            }else {
                Application.showToast("数据显示异常");
            }
        }
        //根据不一样的数据进行相应的刷新界面
        private void refreshDialog(String name) {
            tvName.setText(name);
            tvFirst.setText(ChineseCharToEn.getFirstLetter(name).toUpperCase());
            if (!isFirst) {
                if (getAdapterPosition() == 0) {
                    tvFirst.setVisibility(View.VISIBLE);
                } else {
                    String lastStr ="";
                    Log.e("delong","isShowCity="+isShowCity);
                    if (isShowCity){
                        lastStr= ChineseCharToEn.getFirstLetter(cityListBeans.get(getAdapterPosition() - 1).getName());
                    }else {
                        lastStr= ChineseCharToEn.getFirstLetter(provinceListBeans.get(getAdapterPosition() - 1).getName());
                    }
                    String currentStr = ChineseCharToEn.getFirstLetter(name);
                    if (!(currentStr.equalsIgnoreCase(lastStr))) {
                        tvFirst.setVisibility(View.VISIBLE);
                    } else {
                        tvFirst.setVisibility(View.INVISIBLE);
                    }
                }
            } else {
                isFirst = false;
            }
        }
    }

    /**
     * 普通的适配器
     */
    private class CommonAdapter extends RecyclerAdapter<String> {
        @Override
        protected int getItemViewType(int viewtype, String s) {
            return R.layout.cell_dialog;
        }

        @Override
        protected ViewHolder<String> onCreateViewHolder(View root, int viewType) {
            return new CommonViewHolder(root);
        }
    }

    /**
     * 首字母快捷导航适配器
     */
    private class RegionMenuAdapter extends RecyclerAdapter<String> {

        @Override
        protected int getItemViewType(int viewtype, String str) {
            return R.layout.cell_region_menu;
        }

        @Override
        protected ViewHolder<String> onCreateViewHolder(View root, int viewType) {
            return new MenuViewHolder(root);
        }
    }

    /**
     * 案件类型的适配器
     */
    private class CasesTypeAdapter extends RecyclerAdapter<GetCaseTypeResultModel.CaseTypeListBean> {
        @Override
        protected int getItemViewType(int viewtype, GetCaseTypeResultModel.CaseTypeListBean typeNamesBean) {
            return R.layout.cell_dialog;
        }

        @Override
        protected ViewHolder<GetCaseTypeResultModel.CaseTypeListBean> onCreateViewHolder(View root, int viewType) {
            return new CasesTypeViewHolder(root);
        }
    }


    /**
     * 省/地区的/市的适配器
     */
    private class RegionInfoAdapter extends RecyclerAdapter{
        @Override
        protected int getItemViewType(int viewtype, Object provinceListBean) {
            return R.layout.cell_region;
        }

        @Override
        protected ViewHolder<Object> onCreateViewHolder(View root, int viewType) {
            return new RegionInfoViewHolder(root);
        }
    }

    /*
     *处理使用BottomSheetDialog时状态栏不透明的情况
     */
    public static class TransStatusBottomSheetDialog extends BottomSheetDialog {

        public TransStatusBottomSheetDialog(@NonNull Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            final Window window = getWindow();
            if (window == null) {
                return;
            }
            int screenHight = UiTool.getScreenHeight(Objects.requireNonNull(getOwnerActivity()));
            int statusHight = UiTool.getStatusBarHeight(getOwnerActivity());
            int dialogHight = screenHight - statusHight;
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHight <= 0 ? ViewGroup.LayoutParams.MATCH_PARENT : dialogHight);
        }
    }

}
