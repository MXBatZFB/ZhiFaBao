package com.zfb.zhifabao.flags.law;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Fragment;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLaborLawResultModel;
import com.zfb.zhifabao.common.widget.cyclerview.RecyclerAdapter;
import com.zfb.zhifabao.flags.account.AccountTrigger;

import butterknife.BindView;
import butterknife.OnClick;

@SuppressLint("ValidFragment")
public class ShowLawFragment extends Fragment implements Common.Constance {
    private static ResModel<GetLaborLawResultModel> mResModel;
    @BindView(R.id.show_law_title)
    TextView tvTitle;
    @BindView(R.id.rv_showLaw)
    RecyclerView mRecyclerView;
    private RecyclerAdapter<GetLaborLawResultModel.LawListsBean> mAdapter;
    private AccountTrigger mAccountTrigger;

    public ShowLawFragment() {
    }

    public static void setResult(ResModel<GetLaborLawResultModel> model) {
        mResModel = model;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        if (mResModel != null) {
            tvTitle.setText(String.format("%s相关劳动法", mResModel.getResult().getCity()));
            mAdapter = new ShowLawAdapter();
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<GetLaborLawResultModel.LawListsBean>() {
                @Override
                public void onItemClick(RecyclerAdapter.ViewHolder holder, GetLaborLawResultModel.LawListsBean lawListsBean, int position) {
                    mAccountTrigger.triggerView(Common.Constance.TO_LOOK_LAW_FRAGMENT);
                    LookFragment.setLawName(lawListsBean.getLawName(), LOCAL_LAW);
                }
            });
            mAdapter.replace(mResModel.getResult().getLawLists());
        } else {
            mAccountTrigger.triggerView(TO_CONSULTATION_FRAGMENT);
        }

    }

    @OnClick(R.id.im_back)
    void onBack() {
        mAccountTrigger.triggerView(TO_CONSULTATION_FRAGMENT);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_show_law;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mResModel = null;
    }

    @Override
    public boolean onBackPressed() {
        mAccountTrigger.triggerView(TO_CONSULTATION_FRAGMENT);
        return true;
    }

    private class ShowLawAdapter extends RecyclerAdapter<GetLaborLawResultModel.LawListsBean> {
        @Override
        protected int getItemViewType(int viewtype, GetLaborLawResultModel.LawListsBean lawListsBean) {
            return R.layout.cell_look_law;
        }

        @Override
        protected ViewHolder<GetLaborLawResultModel.LawListsBean> onCreateViewHolder(View root, int viewType) {
            return new ShowLawViewHolder(root);
        }

        private class ShowLawViewHolder extends ViewHolder<GetLaborLawResultModel.LawListsBean> {
            private TextView tvName;

            public ShowLawViewHolder(View root) {
                super(root);
                tvName = root.findViewById(R.id.tv_law_name);
            }

            @Override
            protected void onBind(GetLaborLawResultModel.LawListsBean lawListsBean) {
                tvName.setText(lawListsBean.getLawName());
            }
        }
    }
}
