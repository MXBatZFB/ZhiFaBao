package com.zfb.zhifabao.flags.media;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.tools.UiTool;
import com.zfb.zhifabao.common.widget.cyclerview.GalleyView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends BottomSheetDialogFragment implements GalleyView.SelectedChangeListener {

    @BindView(R.id.galleryView)
    GalleyView galleyView;
    private OnselectedListener mListener;

    public GalleryFragment() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TransStatusBottomSheetDialog(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        // LoaderManager.getInstance(getActivity());
        galleyView.setup(getLoaderManager(), this);
    }

    @Override
    public void onSelectedCountChange(int count) {
        if (count > 0) {
            dismiss();
            if (mListener != null) {
                String[] paths = galleyView.getSelectPtah();
                mListener.onSelectedImage(paths[0]);
                mListener = null;
            }
        }
    }


    public GalleryFragment setListener(OnselectedListener listener) {
        mListener = listener;
        return this;
    }

    public interface OnselectedListener {
        void onSelectedImage(String path);
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


}
