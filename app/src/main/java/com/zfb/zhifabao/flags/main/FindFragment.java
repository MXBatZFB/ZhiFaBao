package com.zfb.zhifabao.flags.main;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.activities.WebActivity;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.model.api.find.FindContent;
import com.zfb.zhifabao.common.factory.persistence.Account;
import com.zfb.zhifabao.common.factory.presenter.find.FindContract;
import com.zfb.zhifabao.common.factory.presenter.find.FindPresenter;
import com.zfb.zhifabao.common.widget.cyclerview.RecyclerAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Fragment
 */
public class FindFragment extends PresenterFragment<FindContract.Presenter> implements FindContract.View<List<FindContent>> {
    @BindView(R.id.find_recyclerView)
    RecyclerView mRecyclerView;
    private FindAdapter mAdapter;
    private LinearLayoutManager manager;

    public FindFragment() {

    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new FindAdapter();
        mAdapter.setListener(new RecyclerAdapter.AdapterListener<FindContent>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, FindContent findContent, int position) {
                WebActivity.show(getActivity(), findContent.getArticle_link());
            }

            @Override
            public void onItemLongClick(RecyclerAdapter.ViewHolder holder, FindContent findContent, int position) {

            }
        });

        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getFindList(Account.getToken());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResume() {
        super.onResume();
        setStatuTrans();
    }

    /**
     * 这是状态栏透明的方法
     */
    private void setStatuTrans() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected FindContract.Presenter initPresenter() {
        return new FindPresenter(this);
    }

    @Override
    public void onInit(List<FindContent> findContents) {

    }

    @Override
    public RecyclerAdapter<FindContent> getAdapter() {
        return mAdapter;
    }

    @Override
    public void onAdapterDataChange() {
        mAdapter.notifyDataSetChanged();
    }


    private class FindAdapter extends RecyclerAdapter<FindContent> {

        @Override
        protected int getItemViewType(int viewtype, FindContent findContent) {
            return R.layout.cell_find_view;
        }

        @Override
        protected ViewHolder<FindContent> onCreateViewHolder(View root, int viewType) {
            return new FindViewHolder(root);
        }
    }


    class FindViewHolder extends RecyclerAdapter.ViewHolder<FindContent> {
        TextView mTitle;
        ImageView pic_Title;

        public FindViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.content_title);
            pic_Title = itemView.findViewById(R.id.pic_title);
        }

        @Override
        protected void onBind(FindContent findContent) {
            mTitle.setText(findContent.getArticle_title());
            Glide.with(getActivity()).load(findContent.getArticle_pic_link()).into(pic_Title);
        }
    }
}
