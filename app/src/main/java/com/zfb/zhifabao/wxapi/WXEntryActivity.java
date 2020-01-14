package com.zfb.zhifabao.wxapi;

import android.app.Activity;
import butterknife.BindView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.zfb.zhifabao.R;

import com.zfb.zhifabao.common.factory.Factory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_wxentry);
       Factory.getApi().handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Factory.getApi().handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Toast.makeText(this,"onReq",Toast.LENGTH_LONG).show();
        Log.e("delong","openId>>>>>>>>>>>>"+baseReq.openId);
    }

    @Override
    public void onResp(BaseResp baseResp) {
        Toast.makeText(this,baseResp.toString(),Toast.LENGTH_LONG).show();
        Log.e("delong","openId>>>>>>>>>>>>");
        Log.e("delong","openId>>>>>>>>>>>>"+baseResp.openId);
    }

}
