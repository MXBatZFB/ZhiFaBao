package com.zfb.zhifabao.flags.law;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.cases.GetCaseTypeResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetProcessDucumentsResult;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetRelatedCaseTypeResultModel;
import com.zfb.zhifabao.common.tools.UiTool;
import com.zfb.zhifabao.common.widget.cyclerview.RecyclerAdapter;
import com.zfb.zhifabao.flags.account.AccountTrigger;

import java.util.Collection;
import java.util.Objects;

import static com.zfb.zhifabao.common.factory.Factory.getGson;

@SuppressLint("ValidFragment")
public class DialogFragment extends BottomSheetDialogFragment implements Common.Constance {
    private RecyclerAdapter<String> mAdapter;
    private RecyclerAdapter<GetRelatedCaseTypeResultModel.CaseTypeBean> mRelatedCaseTypeAdapter;
    private RecyclerAdapter<GetRelatedCaseTypeResultModel.CaseTypeBean.TypeNamesBean> mRelatedCaseTypeNameAdapter;
    private RecyclerAdapter<GetCaseTypeResultModel.CaseTypesBean> mCaseTypeNameAdapter;
    private OnSelectedCallback mCallback;
    private ResModel mResModel;
    private int mTemp;
    private int lastSelectPosition = -1;
    private AccountTrigger mAccountTrigger;

    public DialogFragment(OnSelectedCallback mCallback, int temp, ResModel result) {
        this.mCallback = mCallback;
        mTemp = temp;
        mResModel = result;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TransStatusBottomSheetDialog(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = null;
        if (mTemp == R.id.select_city) {
            root = inflater.inflate(R.layout.fragment_common, container, false);
            initLawData(root);
        } else if (mTemp == R.id.select_type) {
            root = inflater.inflate(R.layout.fragment_case, container, false);
            initRelatedCaseData(root);
        } else if (mTemp == R.id.select_process_documents) {
            root = inflater.inflate(R.layout.fragment_common, container, false);
            initProcessDocuments(root);
        } else if (mTemp == R.id.select_cases_type) {
            root = inflater.inflate(R.layout.fragment_case, container, false);
            initCaseType(root);
        }
        return root;
    }

    private void initProcessDocuments(View root) {
        RecyclerView mRecyclerView = root.findViewById(R.id.content_select_recyclerView);
        mAdapter = new Adapter();
        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<String>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, String s, int position) {
                mAccountTrigger.triggerView(TO_LOOK_LAW_FRAGMENT);
                LookFragment.setLawName(s, PROCESS_DOCUMENTS);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initCaseType(View root) {
        RecyclerView mCaseTypeRecyclerView = root.findViewById(R.id.content_menu_recyclerView);
        RecyclerView mCaseTypeNameRecyclerView = root.findViewById(R.id.content_select_recyclerView);
        mAdapter = new Adapter();
        mCaseTypeNameAdapter = new CasesAdapter();
        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<String>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, String s, int position) {
                mAccountTrigger.triggerView(TO_LOOK_LAW_FRAGMENT);
                LookFragment.setLawName(s, CASE_SUGGESTION);
                for (GetCaseTypeResultModel.CaseTypesBean bean : mCaseTypeNameAdapter.getItems()) {
                    if (bean.isSelected()) {
                        LookFragment.setCaseType(bean.getCasesTypeName());
                    }
                }

            }
        });

        mCaseTypeNameAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<GetCaseTypeResultModel.CaseTypesBean>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, GetCaseTypeResultModel.CaseTypesBean bean, int position) {
                mAdapter.replace(bean.getLawFileNames());
                bean.setSelected(true);
                if (lastSelectPosition != position && lastSelectPosition >= 0) {
                    mCaseTypeNameAdapter.getItems().get(lastSelectPosition).setSelected(false);
                    lastSelectPosition = position;
                }
                mCaseTypeNameAdapter.notifyDataSetChanged();
            }
        });
        mCaseTypeNameRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCaseTypeNameRecyclerView.setAdapter(mAdapter);
        mCaseTypeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCaseTypeRecyclerView.setAdapter(mCaseTypeNameAdapter);
    }

    private void initRelatedCaseData(View root) {
        RecyclerView mRelatedCaseTypeRecyclerView = root.findViewById(R.id.content_menu_recyclerView);
        RecyclerView mRelatedCaseTypeNameRecyclerView = root.findViewById(R.id.content_select_recyclerView);
        mRelatedCaseTypeAdapter = new Adapter();
        mRelatedCaseTypeNameAdapter = new RelatedCaseAdapter();
        mRelatedCaseTypeNameRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRelatedCaseTypeNameRecyclerView.setAdapter(mRelatedCaseTypeAdapter);
        mRelatedCaseTypeNameAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<GetRelatedCaseTypeResultModel.CaseTypeBean.TypeNamesBean>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, GetRelatedCaseTypeResultModel.CaseTypeBean.TypeNamesBean typeNamesBean, int position) {
                mAccountTrigger.triggerView(Common.Constance.TO_LOOK_LAW_FRAGMENT);
                LookFragment.setLawName(typeNamesBean.getSecondTypeName(), CASE_LAW);
            }
        });
        mRelatedCaseTypeAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<GetRelatedCaseTypeResultModel.CaseTypeBean>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, GetRelatedCaseTypeResultModel.CaseTypeBean model, int position) {
                mRelatedCaseTypeNameAdapter.replace(model.getTypeNames());
                model.setSelected(true);
                if (lastSelectPosition != position && lastSelectPosition >= 0) {
                    mRelatedCaseTypeAdapter.getItems().get(lastSelectPosition).setSelected(false);
                    lastSelectPosition = position;
                }
                mRelatedCaseTypeAdapter.notifyDataSetChanged();
            }
        });
        mRelatedCaseTypeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRelatedCaseTypeRecyclerView.setAdapter(mRelatedCaseTypeAdapter);
        mRelatedCaseTypeNameRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRelatedCaseTypeNameRecyclerView.setAdapter(mRelatedCaseTypeNameAdapter);
    }

    private void initLawData(View root) {
        RecyclerView mRecyclerView = root.findViewById(R.id.content_select_recyclerView);
        mAdapter = new Adapter();
        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<String>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, String s, int position) {
                mCallback.selected(s, mTemp);
                dismiss();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        if (mTemp == R.id.select_city) {
            mAdapter.replace((Collection<String>) mResModel.getResult());
        } else if (mTemp == R.id.select_type) {
            GetRelatedCaseTypeResultModel model = (GetRelatedCaseTypeResultModel) mResModel.getResult();
            if (!isHaveSelected(model)) {
                lastSelectPosition = 0;
                model.getCaseType().get(lastSelectPosition).setSelected(true);
                mRelatedCaseTypeNameAdapter.replace(model.getCaseType().get(lastSelectPosition).getTypeNames());
            }
            mRelatedCaseTypeAdapter.replace(model.getCaseType());
        } else if (mTemp == R.id.select_process_documents) {
            GetProcessDucumentsResult model = getGson().fromJson(mResModel.getResult().toString(), GetProcessDucumentsResult.class);
            mAdapter.replace(model.getFilenames());
        } else if (mTemp == R.id.select_cases_type) {
            GetCaseTypeResultModel model = (GetCaseTypeResultModel) mResModel.getResult();
            if (!isHaveSelected(model)) {
                lastSelectPosition = 0;
                model.getCaseTypes().get(lastSelectPosition).setSelected(true);
                mAdapter.replace(model.getCaseTypes().get(lastSelectPosition).getLawFileNames());
            }
            mCaseTypeNameAdapter.replace(model.getCaseTypes());
        }
    }

    private boolean isHaveSelected(GetCaseTypeResultModel model) {
        for (GetCaseTypeResultModel.CaseTypesBean bean : model.getCaseTypes()) {
            if (bean.isSelected()) {
                mAdapter.replace(bean.getLawFileNames());
                return true;
            }
        }
        return false;
    }

    private boolean isHaveSelected(GetRelatedCaseTypeResultModel model) {
        for (GetRelatedCaseTypeResultModel.CaseTypeBean bean : model.getCaseType()) {
            if (bean.isSelected()) {
                mRelatedCaseTypeNameAdapter.replace(bean.getTypeNames());
                return true;
            }
        }
        return false;
    }

    interface OnSelectedCallback {
        void selected(String str, int temp);
    }

    @Override
    public void onResume() {
        super.onResume();
        // initData();
    }

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

    class CityViewHolder extends RecyclerAdapter.ViewHolder<String> {
        TextView tv_common;

        CityViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_common = itemView.findViewById(R.id.tv_common);
        }

        @Override
        protected void onBind(String s) {
            tv_common.setText(s);
        }
    }

    class CasesViewHolder extends RecyclerAdapter.ViewHolder<GetCaseTypeResultModel.CaseTypesBean> {
        TextView tv_common;

        CasesViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_common = itemView.findViewById(R.id.tv_common);
        }

        @Override
        protected void onBind(GetCaseTypeResultModel.CaseTypesBean bean) {
            if (bean.isSelected()) {
                tv_common.setTextColor(Color.parseColor("#007AF0"));
            } else {
                tv_common.setTextColor(Color.parseColor("#666666"));
            }
            tv_common.setText(bean.getCasesTypeName());
        }
    }

    class CaseFirstTypeHolder extends RecyclerAdapter.ViewHolder<GetRelatedCaseTypeResultModel.CaseTypeBean> {
        TextView tv_common;

        CaseFirstTypeHolder(@NonNull View itemView) {
            super(itemView);
            tv_common = itemView.findViewById(R.id.tv_common);
        }

        @Override
        protected void onBind(GetRelatedCaseTypeResultModel.CaseTypeBean bean) {
            if (bean.isSelected()) {
                tv_common.setTextColor(Color.parseColor("#007AF0"));
            } else {
                tv_common.setTextColor(Color.parseColor("#666666"));
            }
            tv_common.setText(bean.getFirstType());
        }
    }

    class CaseSecondTypeHolder extends RecyclerAdapter.ViewHolder<GetRelatedCaseTypeResultModel.CaseTypeBean.TypeNamesBean> {
        TextView tv_common;

        CaseSecondTypeHolder(@NonNull View itemView) {
            super(itemView);
            tv_common = itemView.findViewById(R.id.tv_common);
        }

        @Override
        protected void onBind(GetRelatedCaseTypeResultModel.CaseTypeBean.TypeNamesBean s) {
            tv_common.setText(s.getSecondTypeName());
        }
    }

    class Adapter extends RecyclerAdapter {

        @Override
        protected int getItemViewType(int viewtype, Object o) {
            return R.layout.cell_dialog;
        }

        @Override
        protected ViewHolder onCreateViewHolder(View root, int viewType) {
            if (mTemp == R.id.select_city || mTemp == R.id.select_process_documents || mTemp == R.id.select_cases_type) {
                return new CityViewHolder(root);
            } else if (mTemp == R.id.select_type) {//R.id.select_type
                return new CaseFirstTypeHolder(root);
            } else {
                return null;
            }
        }
    }


    private class RelatedCaseAdapter extends RecyclerAdapter<GetRelatedCaseTypeResultModel.CaseTypeBean.TypeNamesBean> {
        @Override
        protected int getItemViewType(int viewtype, GetRelatedCaseTypeResultModel.CaseTypeBean.TypeNamesBean typeNamesBean) {
            return R.layout.cell_dialog;
        }

        @Override
        protected ViewHolder<GetRelatedCaseTypeResultModel.CaseTypeBean.TypeNamesBean> onCreateViewHolder(View root, int viewType) {
            return new CaseSecondTypeHolder(root);
        }
    }

    private class CasesAdapter extends RecyclerAdapter<GetCaseTypeResultModel.CaseTypesBean> {
        @Override
        protected int getItemViewType(int viewtype, GetCaseTypeResultModel.CaseTypesBean typeNamesBean) {
            return R.layout.cell_dialog;
        }

        @Override
        protected ViewHolder<GetCaseTypeResultModel.CaseTypesBean> onCreateViewHolder(View root, int viewType) {
            return new CasesViewHolder(root);
        }
    }
}
