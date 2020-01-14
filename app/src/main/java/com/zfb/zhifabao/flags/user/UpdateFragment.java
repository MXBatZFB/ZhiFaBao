package com.zfb.zhifabao.flags.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.RadioGroup;

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
import com.zfb.zhifabao.flags.main.CommonTrigger;
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
    @BindView(R.id.rg_sex)
    RadioGroup rg_sex;
    private String portraitPath;
    private  String sex="n";

    public UpdateFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rd_m:
                        sex="m";
                        break;
                    case R.id.rd_n:
                        sex="n";
                        break;
                    default:
                        break;
                }
            }
        });
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
        if (sex!=null)
        mPresenter.update(userName, sex, portraitPath);
    }

    @Override
    protected UpdateInfoContract.presenter initPresenter() {
        return new UpdateInfoPresenter(this);
    }

    @Override
    public void updateSuccess() {
        getActivity().finish();
    }
}
