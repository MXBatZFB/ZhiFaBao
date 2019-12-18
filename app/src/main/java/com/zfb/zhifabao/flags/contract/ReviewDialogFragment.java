package com.zfb.zhifabao.flags.contract;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
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
import com.zfb.zhifabao.common.tools.UiTool;
import com.zfb.zhifabao.common.widget.cyclerview.RecyclerAdapter;

import java.util.Collection;
import java.util.Objects;

@SuppressLint("ValidFragment")
public class ReviewDialogFragment extends BottomSheetDialogFragment implements Common.Constance {
    private OnSelectedCallback mCallback;
    private RecyclerAdapter<String> mAdapter;
    private ResModel mResModel;
    private int mTemp;

    public ReviewDialogFragment(OnSelectedCallback mCallback, ResModel model, int temp) {
        this.mCallback = mCallback;
        mResModel = model;
        mTemp = temp;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TransStatusBottomSheetDialog(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_common, container, false);
        RecyclerView mRecyclerView = root.findViewById(R.id.content_select_recyclerView);

        mAdapter = new Adapter();
        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<String>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, String s, int position) {
                mCallback.selected(s, mTemp);
                dismiss();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }


    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        mAdapter.replace((Collection<String>) mResModel.getResult());
    }


    interface OnSelectedCallback {
        void selected(String str, int temp);
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

    class ViewHolder extends RecyclerAdapter.ViewHolder<String> {
        TextView tv_common;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_common = itemView.findViewById(R.id.tv_common);
        }

        @Override
        protected void onBind(String s) {
            tv_common.setText(s);
        }
    }


    class Adapter extends RecyclerAdapter {

        @Override
        protected int getItemViewType(int viewtype, Object o) {
            return R.layout.cell_dialog;
        }

        @Override
        protected ViewHolder onCreateViewHolder(View root, int viewType) {
            return new ReviewDialogFragment.ViewHolder(root);
        }
    }


}
