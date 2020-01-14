package com.zfb.zhifabao.flags.contract;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zfb.zhifabao.App;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.contract.ContractResultMode;
import com.zfb.zhifabao.common.factory.presenter.contract.ReviewContract;
import com.zfb.zhifabao.common.factory.presenter.contract.ReviewPresenter;
import com.zfb.zhifabao.common.widget.cyclerview.RecyclerAdapter;
import com.zfb.zhifabao.flags.media.FileFragment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class ContractReviewFragment extends PresenterFragment<ReviewContract.Presenter> implements ReviewDialogFragment.OnSelectedCallback, ReviewContract.View {
    @BindView(R.id.tv_contract_type)
    TextView tvContractType;

    @BindView(R.id.tv_up)
    TextView tvUpName;

    @BindView(R.id.circle_add_file)
    CircleImageView mCircleImageView;

    @BindView(R.id.rv_add_file)
    RecyclerView mRecyclerView;

    @BindView(R.id.tv_method_type)
    TextView tvMethodType;
    private String mCurrentPhotoPath;
    private String upMethod = "";

    public ContractReviewFragment() {

    }

    @SuppressLint("NewApi")
    public static String generateFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "PIC_" + timeStamp + "_";
        return imageFileName;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_contract_review;
    }

    @OnClick(R.id.selected_contract_type)
    void selectedContractType() {
        mPresenter.getContractType();
    }

    @SuppressLint("NewApi")
    @OnClick(R.id.circle_add_file)
    void addFile() {
        if (upMethod.equals("拍照")) {
            takeCamera(1);
        } else if (upMethod.equals("文件")) {
            new FileFragment().show(getChildFragmentManager(), ContractReviewFragment.class.getName());
        } else {
            App.showToast("请选择上传方式");
        }
    }

    @OnClick(R.id.selected_up_method)
    void selectedUpMethod() {
        ResModel model = new ResModel();
        List<String> data = new ArrayList<>();
        data.add("文件");
        data.add("拍照");
        model.setData(data);
        ReviewDialogFragment fragment = new ReviewDialogFragment(this, model, 2);
        fragment.show(getChildFragmentManager(), ReviewDialogFragment.class.getName());
    }

    @Override
    public void selected(String str, int temp) {
        if (temp == 1) {
            tvContractType.setText(str);
        } else {
            tvMethodType.setText(str);
            tvUpName.setText(String.format("请上传合同%s", str));
            if (!str.equals("文件")) {
                mCircleImageView.setImageResource(R.drawable.register_icon_user);
                upMethod = "拍照";
            } else {
                upMethod = "文件";
                mCircleImageView.setImageResource(R.drawable.function_add);
            }
        }
    }

    @Override
    protected ReviewContract.Presenter initPresenter() {
        return new ReviewPresenter(this);
    }

    @Override
    public void onGetTypeSuccess(ContractResultMode model) {
        ResModel resModel = new ResModel();
        resModel.setData(model.getTypes());
        ReviewDialogFragment fragment = new ReviewDialogFragment(this, resModel, 1);
        fragment.show(getChildFragmentManager(), ReviewDialogFragment.class.getName());
    }

    @SuppressLint("NewApi")
    private void takeCamera(int num) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            File photoFile = null;
            photoFile = createImageFile();
            if (photoFile != null) {
                String authority = getActivity().getPackageName() + ".fileProvider"; //【清单文件中provider的authorities属性的值】
                Uri uri = FileProvider.getUriForFile(getActivity(), authority, photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            }
        }
        startActivityForResult(takePictureIntent, num);//跳转界面传回拍照所得数据
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private File createImageFile() {
        File storageDir = getContext().getFilesDir();
        File image = null;
        try {
            image = File.createTempFile(
                    generateFileName(),  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    class Adapter extends RecyclerAdapter<String> {
        @Override
        protected int getItemViewType(int viewtype, String s) {
            return 0;
        }

        @Override
        protected ViewHolder<String> onCreateViewHolder(View root, int viewType) {
            return null;
        }
    }
}
