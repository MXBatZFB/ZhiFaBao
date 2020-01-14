package com.zfb.zhifabao.flags.law.consultation;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Fragment;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetCaseListWithTypeResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLabourLawContentByIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLabourLawListWithRegionIdResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.GetLabourLawListWithTypeResultModel;
import com.zfb.zhifabao.common.factory.presenter.consultation.ShowLawListContract;
import com.zfb.zhifabao.common.factory.presenter.consultation.ShowLawListPresenter;
import com.zfb.zhifabao.common.widget.cyclerview.RecyclerAdapter;
import com.zfb.zhifabao.flags.main.CommonTrigger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@SuppressLint("ValidFragment")
public class ShowLawListFragment extends PresenterFragment<ShowLawListContract.Presenter> implements ShowLawListContract.View,Common.Constance {
    private static ResModel mResModel;
    @BindView(R.id.show_law_title)
    TextView tvTitle;
    @BindView(R.id.rv_showLaw)
    RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private CommonTrigger mCommonTrigger;

    public ShowLawListFragment() {
    }

    public static void setResult(ResModel model) {
        mResModel = model;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCommonTrigger = (CommonTrigger) context;
    }

    @Override
    protected ShowLawListContract.Presenter initPresenter() {
        return new ShowLawListPresenter(this);
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        if (mResModel != null) {

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            if (mResModel.getData() instanceof GetLabourLawListWithTypeResultModel){
                tvTitle.setText(String.format("相关劳动法列表",""));
                mAdapter = new ShowLawAdapter();
                mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<GetLabourLawListWithTypeResultModel.LawListBean>() {
                    @Override
                    public void onItemClick(RecyclerAdapter.ViewHolder holder, GetLabourLawListWithTypeResultModel.LawListBean lawListsBean, int position) {
                        mPresenter.loadLawContentById(lawListsBean.getId());
                    }
                });
                mRecyclerView.setAdapter(mAdapter);
                List<GetLabourLawListWithTypeResultModel.LawListBean> lists ;
                lists = ((GetLabourLawListWithTypeResultModel) mResModel.getData()).getLawList();
                mAdapter.replace(lists);
            }

            if (mResModel.getData() instanceof  GetLabourLawListWithRegionIdResultModel){
                tvTitle.setText(String.format("相关劳动法列表",""));
                mAdapter = new ShowLawListAdapter();
                mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<GetLabourLawListWithRegionIdResultModel.LocalLawTitleListBean>() {
                    @Override
                    public void onItemClick(RecyclerAdapter.ViewHolder holder, GetLabourLawListWithRegionIdResultModel.LocalLawTitleListBean lawListsBean, int position) {
                        mPresenter.loadLocalLawContentById(lawListsBean.getId());
                    }
                });
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.replace(((GetLabourLawListWithRegionIdResultModel) mResModel.getData()).getLocalLawTitleList());
            }

           if (mResModel.getData() instanceof GetCaseListWithTypeResultModel){
               mAdapter = new ShowCaseListAdapter();
               mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<GetCaseListWithTypeResultModel.CaseListBean>() {
                   @Override
                   public void onItemClick(RecyclerAdapter.ViewHolder holder, GetCaseListWithTypeResultModel.CaseListBean caseListBean, int position) {
                       mPresenter.loadCaseContentById(caseListBean.getId());
                   }
               });
               mRecyclerView.setAdapter(mAdapter);
               mAdapter.replace(((GetCaseListWithTypeResultModel) mResModel.getData()).getCaseList());
           }

        }
    }

    @OnClick(R.id.im_back)
    void onBack() {
        getActivity().finish();
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
        getActivity().finish();
        return true;
    }

    @Override
    public void onContentSucceed(ResModel model) {
        GetLabourLawContentByIdResultModel resultModel = (GetLabourLawContentByIdResultModel) model.getData();
        LookContentWithLawFragment.setData(resultModel);
        mCommonTrigger.triggerView(Common.Constance.TO_LOOK_CONTENT_LAW_FRAGMENT);
    }

    private class ShowCaseListAdapter extends RecyclerAdapter<GetCaseListWithTypeResultModel.CaseListBean> {
        @Override
        protected int getItemViewType(int viewtype, GetCaseListWithTypeResultModel.CaseListBean caseListBean) {
            return R.layout.cell_look_law;
        }

        @Override
        protected ViewHolder<GetCaseListWithTypeResultModel.CaseListBean> onCreateViewHolder(View root, int viewType) {
            return new ShowLawListViewHolder(root);
        }

        private class ShowLawListViewHolder extends ViewHolder<GetCaseListWithTypeResultModel.CaseListBean> {
            private TextView tvName;

            public ShowLawListViewHolder(View root) {
                super(root);
                tvName = root.findViewById(R.id.tv_law_name);
            }

            @Override
            protected void onBind(GetCaseListWithTypeResultModel.CaseListBean caseListBean) {

            }
        }
    }


    private class ShowLawListAdapter extends RecyclerAdapter<GetLabourLawListWithRegionIdResultModel.LocalLawTitleListBean> {
        @Override
        protected int getItemViewType(int viewtype, GetLabourLawListWithRegionIdResultModel.LocalLawTitleListBean lawListsBean) {
            return R.layout.cell_look_law;
        }

        @Override
        protected ViewHolder<GetLabourLawListWithRegionIdResultModel.LocalLawTitleListBean> onCreateViewHolder(View root, int viewType) {
            return new ShowLawListViewHolder(root);
        }

        private class ShowLawListViewHolder extends ViewHolder<GetLabourLawListWithRegionIdResultModel.LocalLawTitleListBean> {
            private TextView tvName;

            public ShowLawListViewHolder(View root) {
                super(root);
                tvName = root.findViewById(R.id.tv_law_name);
            }

            @Override
            protected void onBind(GetLabourLawListWithRegionIdResultModel.LocalLawTitleListBean lawListsBean) {
                tvName.setText(lawListsBean.getTitle());
            }
        }
    }


    private class ShowLawAdapter extends RecyclerAdapter<GetLabourLawListWithTypeResultModel.LawListBean> {
        @Override
        protected int getItemViewType(int viewtype, GetLabourLawListWithTypeResultModel.LawListBean lawListsBean) {
            return R.layout.cell_look_law;
        }

        @Override
        protected ViewHolder<GetLabourLawListWithTypeResultModel.LawListBean> onCreateViewHolder(View root, int viewType) {
            return new ShowLawViewHolder(root);
        }

        private class ShowLawViewHolder extends ViewHolder<GetLabourLawListWithTypeResultModel.LawListBean> {
            private TextView tvName;

            public ShowLawViewHolder(View root) {
                super(root);
                tvName = root.findViewById(R.id.tv_law_name);
            }

            @Override
            protected void onBind(GetLabourLawListWithTypeResultModel.LawListBean lawListsBean) {
                tvName.setText(lawListsBean.getTitle());
            }
        }
    }

}
