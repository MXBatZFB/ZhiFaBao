package com.zfb.zhifabao.permission;


import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.app.Application;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * A simple {@link Fragment} subclass.
 */
public class PermissionFragment extends BottomSheetDialogFragment implements EasyPermissions.PermissionCallbacks {
    private static final int RC = 0x0100;
    private static OnSubmit mCallback;

    public PermissionFragment() {
    }

    private static boolean haveReadperm(Context context) {
        String[] perms = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        return EasyPermissions.hasPermissions(context, perms);
    }

    private static boolean haveWriteperm(Context context) {
        String[] perms = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        return EasyPermissions.hasPermissions(context, perms);
    }

    private static boolean haveNetworkperm(Context context) {
        String[] perms = new String[]{
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE
        };
        return EasyPermissions.hasPermissions(context, perms);
    }

    private static void show(FragmentManager manager) {
        new PermissionFragment()
                .show(manager, PermissionFragment.class.getName());
    }

    public static boolean hasAll(Context context, FragmentManager manager, OnSubmit callback) {
        mCallback = callback;
        boolean hasAll = haveNetworkperm(context)
                && haveReadperm(context)
                && haveWriteperm(context);

        if (!hasAll) {
            show(manager);
        }

        return hasAll;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_permission, container, false);
        ButterKnife.bind(this, root);
        refreshState(root);
        return root;
    }

    private void refreshState(View view) {
        Context context = getContext();

        view.findViewById(R.id.im_sate_permission_network)
                .setVisibility(haveNetworkperm(context) ? View.VISIBLE : View.GONE);

        view.findViewById(R.id.im_sate_permission_write)
                .setVisibility(haveWriteperm(context) ? View.VISIBLE : View.GONE);

        view.findViewById(R.id.im_sate_permission_read)
                .setVisibility(haveReadperm(context) ? View.VISIBLE : View.GONE);

    }

    @OnClick(R.id.btn_submit)
    void onSubmit() {
        if (mCallback != null) {
            mCallback.onClickSubmit(requestPerm());
        }
    }

    private boolean requestPerm() {
        String[] perms = new String[]{
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        if (EasyPermissions.hasPermissions(getContext(), perms)) {
            Application.showToast(R.string.label_permission_ok);
            refreshState(getView());
            return true;
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.title_assist_permissions), RC, perms);
            return false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallback = null;
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog
                    .Builder(this)
                    .build()
                    .show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    public interface OnSubmit {
        void onClickSubmit(boolean requestPermIsSucceed);
    }
}
