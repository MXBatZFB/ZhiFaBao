package com.zfb.zhifabao.flags.contract;


import android.app.ProgressDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.app.Fragment;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.model.db.ContractFileInfo;
import com.zfb.zhifabao.common.factory.presenter.contract.MyContractContract;
import com.zfb.zhifabao.common.factory.presenter.contract.MyContractPresenter;
import com.zfb.zhifabao.common.model.FileInfo;
import com.zfb.zhifabao.common.utils.FileUtil;
import com.zfb.zhifabao.common.widget.cyclerview.FileItemView;
import com.zfb.zhifabao.common.widget.cyclerview.RecyclerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;


public class MyContractFragment extends PresenterFragment<MyContractContract.Presenter> implements MyContractContract.View {
        @BindView(R.id.rv_my_contract)
        RecyclerView mRvMyContract;
        private Adapter mAdapter;
    private ProgressDialog progressDialog;
    public MyContractFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        progressDialog = new ProgressDialog(getContext(), ProgressDialog.THEME_HOLO_LIGHT);
        progressDialog.setMessage("正在加载中.....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        mPresenter.checkLocalContract();
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mAdapter =  new Adapter();
        mRvMyContract.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvMyContract.setAdapter(mAdapter);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_my_contract;
    }

    @Override
    protected MyContractContract.Presenter initPresenter() {
        return new MyContractPresenter(this);
    }


    @Override
    public void onCheckLocalContractSuccess(List<ContractFileInfo> lists) {
           mAdapter.replace(lists);
           progressDialog.dismiss();
    }

    private class Adapter extends RecyclerAdapter<ContractFileInfo> {

        @Override
        protected int getItemViewType(int viewType, ContractFileInfo fileInfo) {
            return com.zfb.zhifabao.common.R.layout.cell_folder_file_info_item;
        }

        @Override
        protected ViewHolder<ContractFileInfo> onCreateViewHolder(View root, int viewType) {
            return new MyContractFragment.ViewHolder(root);
        }
    }

    private class ViewHolder extends RecyclerAdapter.ViewHolder<ContractFileInfo> {
        TextView tv_content;
        TextView tv_size;
        TextView tv_time;
        ImageView iv_cover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(com.zfb.zhifabao.common.R.id.tv_content);
            tv_size = itemView.findViewById(com.zfb.zhifabao.common.R.id.tv_size);
            tv_time = itemView.findViewById(com.zfb.zhifabao.common.R.id.tv_time);
            iv_cover = itemView.findViewById(com.zfb.zhifabao.common.R.id.iv_cover);
        }

        @Override
        protected void onBind(ContractFileInfo fileInfo) {
            tv_content.setText(fileInfo.getFileName());
            tv_size.setVisibility(View.GONE);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            Date date = new Date(Long.parseLong(fileInfo.getTime()));
            tv_time.setText(simpleDateFormat.format(date));
            Glide.with(getContext()).load(com.zfb.zhifabao.common.R.mipmap.word).fitCenter().into(iv_cover);
        }
    }
}
