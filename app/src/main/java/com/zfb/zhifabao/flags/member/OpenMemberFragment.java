package com.zfb.zhifabao.flags.member;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.activities.ZFBMessageActivity;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.app.Fragment;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.model.api.member.OrderResultModel;
import com.zfb.zhifabao.common.factory.presenter.member.OpenMemberContract;
import com.zfb.zhifabao.common.factory.presenter.member.OpenMemberPresenter;
import com.zfb.zhifabao.flags.main.CommonTrigger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenMemberFragment extends PresenterFragment<OpenMemberContract.Presenter> implements OpenMemberContract.View {
    @BindView(R.id.im_member_type_one)
    ImageView im_select_one;

    @BindView(R.id.im_member_type_two)
    ImageView im_select_two;

    @BindView(R.id.im_member_type_three)
    ImageView im_select_three;

    private String price = "78";

    public OpenMemberFragment() {
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_open_member;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @OnClick(R.id.im_back)
    void back(){
       getActivity().finish();
    }

    @OnClick(R.id.im_find_msg)
    void lookMsg(){
        ZFBMessageActivity.show(getActivity());
    }

    @OnClick(R.id.im_member_type_one)
    void onSelectMemberOne(){
        price = "78";
        im_select_one.setImageResource(R.drawable.selected_member_type_one);
        im_select_two.setImageResource(R.drawable.no_select_member_type_two_bg);
        im_select_three.setImageResource(R.drawable.no_select_member_type_three_bg);
    }

    @OnClick(R.id.im_member_type_two)
    void onSelectMemberTow(){
        price = "198";
        im_select_one.setImageResource(R.drawable.no_select_member_type_one_bg);
        im_select_two.setImageResource(R.drawable.selected_member_type_two);
        im_select_three.setImageResource(R.drawable.no_select_member_type_three_bg);
    }

    @OnClick(R.id.im_member_type_three)
    void onSelectMemberThree(){
        price="808";
        im_select_one.setImageResource(R.drawable.no_select_member_type_one_bg);
        im_select_two.setImageResource(R.drawable.no_select_member_type_two_bg);
        im_select_three.setImageResource(R.drawable.selected_member_type_three);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @OnClick(R.id.fl_weChat_pay)
    void onPayByWeChat(){
        mPresenter.createOrder(price);
    }

    @Override
    protected OpenMemberContract.Presenter initPresenter() {
        return new OpenMemberPresenter(this);
    }

    @Override
    public void openMemberSucceed(String msg) {
        Application.showToast(msg);
    }

    @Override
    public void createOrderSucceed(OrderResultModel model) {
        try {
            Log.e("delong",model.toString());
            PayReq req = new PayReq();
            //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
            req.appId = model.getAppid();
            req.partnerId = model.getPartnerid();
            req.prepayId = model.getPrepayid();
            req.nonceStr = model.getNoncestr();
            req.timeStamp = model.getTimestamp();
            req.packageValue = model.getPackageX();
            req.sign = model.getSign();
            Toast.makeText(getContext(), "正常调起支付", Toast.LENGTH_SHORT).show();
            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信

            Factory.getApi().registerApp(model.getAppid());
            Factory.getApi().sendReq(req);
        }catch (Exception e){
            Log.e("delong",e.getMessage());
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
