package com.zfb.zhifabao.jpush;

import android.content.Context;
import android.util.Log;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.service.JPushMessageReceiver;

public class JPushReceiver extends JPushMessageReceiver {

    @Override
    public void onRegister(Context context, String s) {
        super.onRegister(context, s);
        Log.e("delong","registerId>>>>>>"+s);
    }

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);
        Log.e("delong","customMessage>>>>>>"+customMessage);
        //Account.completeInfo();
    }

}