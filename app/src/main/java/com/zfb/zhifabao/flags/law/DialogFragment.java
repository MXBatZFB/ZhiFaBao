package com.zfb.zhifabao.flags.law;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.tools.UiTool;
import com.zfb.zhifabao.common.widget.cyclerview.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class DialogFragment extends BottomSheetDialogFragment {
    private RecyclerAdapter<String> mAdapter;
    private RecyclerView mRecyclerView;
    private OnSelectedCallback mCallback;
    private int mTemp;

    public DialogFragment(OnSelectedCallback mCallback, int temp) {
        this.mCallback = mCallback;
        mTemp = temp;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TransStatusBottomSheetDialog(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_common, container, false);
        mRecyclerView = root.findViewById(R.id.content_select_recyclerView);
        mAdapter = new Adapter();
        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<String>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, String s) {
                mCallback.selected(s, mTemp);
                dismiss();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        List<String> city = new ArrayList<>();
        city.add("深圳");
        city.add("广州");
        mAdapter.replace(city);
    }


    interface OnSelectedCallback {
        void selected(String str, int temp);
    }

    public static class TransStatusBottomSheetDialog extends BottomSheetDialog {

        public TransStatusBottomSheetDialog(@NonNull Context context) {
            super(context);
        }

        public TransStatusBottomSheetDialog(@NonNull Context context, int theme) {
            super(context, theme);
        }

        protected TransStatusBottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
            super(context, cancelable, cancelListener);
        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            final Window window = getWindow();
            if (window == null) {
                return;
            }
            int screenHight = UiTool.getScreenHeight(getOwnerActivity());
            int statusHight = UiTool.getStatusBarHeight(getOwnerActivity());
            int dialogHight = screenHight - statusHight;
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHight <= 0 ? ViewGroup.LayoutParams.MATCH_PARENT : dialogHight);
        }
    }

    class Adapter extends RecyclerAdapter<String> {

        @Override
        protected int getItemViewType(int viewType, String s) {
            return R.layout.cell_dialog;
        }

        @Override
        protected ViewHolder<String> onCreateViewHolder(View root, int viewType) {
            return new DialogViewHolder(root);
        }
    }

    class DialogViewHolder extends RecyclerAdapter.ViewHolder<String> {
        TextView tv_common;

        public DialogViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_common = itemView.findViewById(R.id.tv_common);
        }

        @Override
        protected void onBind(String s) {
            tv_common.setText(s);
        }
    }


}
