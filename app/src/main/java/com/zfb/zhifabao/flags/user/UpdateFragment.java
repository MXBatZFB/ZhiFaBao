package com.zfb.zhifabao.flags.user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import com.bumptech.glide.Glide;
import com.yalantis.ucrop.UCrop;
import com.zfb.zhifabao.MainActivity;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.presenter.user.UpdateInfoContract;
import com.zfb.zhifabao.common.factory.presenter.user.UpdateInfoPresenter;
import com.zfb.zhifabao.common.widget.cyclerview.PortraitView;
import com.zfb.zhifabao.flags.media.GalleryFragment;

import net.qiujuer.genius.ui.widget.Button;
import net.qiujuer.genius.ui.widget.EditText;
import net.qiujuer.genius.ui.widget.Loading;

import java.io.File;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

@SuppressWarnings("unused")
public class UpdateFragment extends PresenterFragment<UpdateInfoContract.presenter>
        implements UpdateInfoContract.view, Common.Constance {
    @BindView(R.id.im_portrait)
    PortraitView portraitView;
    @BindView(R.id.btn_submit)
    Button mSubmit;
    @BindView(R.id.loading)
    Loading loading;
    @BindView(R.id.edit_userName)
    EditText edit_userName;
    @BindView(R.id.edit_companyName)
    EditText edit_companyName;
    private String portraitPath;

    public UpdateFragment() {
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_updata;
    }

    @OnClick(R.id.im_portrait)
    void onPortraitClick() {
        new GalleryFragment().setListener(new GalleryFragment.OnselectedListener() {
            @Override
            public void onSelectedImage(String path) {
                UCrop.Options options = new UCrop.Options();
                options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
                options.setCompressionQuality(96);
                File dPath = Application.getPortraitTmpFile();
                UCrop.of(Uri.fromFile(new File(path)), Uri.fromFile(dPath))
                        .withAspectRatio(1, 1)
                        .withMaxResultSize(520, 520)
                        .withOptions(options)
                        .start(Objects.requireNonNull(getActivity()), REQUEST_PORTRAIT);
            }
        }).show(getChildFragmentManager(), GalleryFragment.class.getName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PORTRAIT) {
            final Uri resultUri = UCrop.getOutput(data);
            assert resultUri != null;
            loadPortrait(resultUri);
        } else {
            showError("异常！");
        }
    }

    private void loadPortrait(Uri resultUri) {
        portraitPath = resultUri.getPath();
        Glide.with(getActivity())
                .load(resultUri)
                .asBitmap()
                .centerCrop()
                .into(portraitView);
    }

    @Override
    public void showError(String str) {
        super.showError(str);
        loading.stop();
        portraitView.setEnabled(true);
        mSubmit.setEnabled(true);
    }

    @Override
    public void showLoding() {
        super.showLoding();
        loading.start();
        portraitView.setEnabled(false);
        mSubmit.setEnabled(false);
    }

    @OnClick(R.id.btn_submit)
    void onSubmitClick() {
        String userName = edit_userName.getText().toString().trim();
        String companyName = edit_companyName.getText().toString().trim();
        mPresenter.update(userName, companyName, portraitPath);
    }

    @Override
    protected UpdateInfoContract.presenter initPresenter() {
        return new UpdateInfoPresenter(this);
    }

    @Override
    public void updateSuccess() {
        MainActivity.show(Objects.requireNonNull(getContext()));
        Objects.requireNonNull(getActivity()).finish();
    }
}
