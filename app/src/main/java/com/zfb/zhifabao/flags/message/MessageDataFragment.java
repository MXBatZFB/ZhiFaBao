package com.zfb.zhifabao.flags.message;


import android.os.Bundle;
import android.view.View;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.adapter.MessageDataRecycleAdapter;
import com.zfb.zhifabao.common.app.Fragment;
import com.zfb.zhifabao.common.factory.model.api.message.GetMessageResultModel;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class MessageDataFragment extends Fragment {
    @BindView(R.id.rv_msg)
     RecyclerView rvMsg;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_msg;
    }


    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        initData();
    }

    private void initData() {
        Bundle bundle = this.getArguments();
        List<GetMessageResultModel.NoticeListBean> data = bundle.getParcelableArrayList("_data");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //设置RecyclerView 布局
        rvMsg.setLayoutManager(linearLayoutManager);
        MessageDataRecycleAdapter mAdapter = new MessageDataRecycleAdapter();
        mAdapter.replace(data);
        rvMsg.setAdapter(mAdapter);
    }
}
