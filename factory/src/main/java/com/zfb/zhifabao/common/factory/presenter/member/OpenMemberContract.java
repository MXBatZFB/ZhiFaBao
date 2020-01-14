package com.zfb.zhifabao.common.factory.presenter.member;

import com.zfb.zhifabao.common.factory.model.api.member.OrderResultModel;
import com.zfb.zhifabao.common.factory.presenter.BaseContract;

public interface OpenMemberContract extends BaseContract {

    interface View extends BaseContract.View<Presenter>{
        void openMemberSucceed(String msg);
        //创建订单成功
        void createOrderSucceed(OrderResultModel model);
    }

    interface Presenter extends BaseContract.Presenter{
         void  createOrder(String price);
    }

}
