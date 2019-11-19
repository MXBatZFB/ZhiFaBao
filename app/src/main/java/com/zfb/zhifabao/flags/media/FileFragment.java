package com.zfb.zhifabao.flags.media;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.widget.cyclerview.FileItemView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FileFragment extends BottomSheetDialogFragment {
    @BindView(R.id.rv_file_item)
    FileItemView fileItemView;
    private ProgressDialog progressDialog;

    public FileFragment() {
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new GalleryFragment.TransStatusBottomSheetDialog(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_file, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        progressDialog = new ProgressDialog(getContext(), ProgressDialog.THEME_HOLO_LIGHT);
        progressDialog.setMessage("正在加载中.....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        fileItemView.setup(getLoaderManager(), progressDialog);
    }
}
