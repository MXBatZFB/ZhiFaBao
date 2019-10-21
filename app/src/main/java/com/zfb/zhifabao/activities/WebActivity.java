package com.zfb.zhifabao.activities;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.app.Activity;
import com.zfb.zhifabao.helper.NavHelper;

import butterknife.BindView;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class WebActivity extends Activity {
    @BindView(R.id.web_view)
    WebView webView;
    private String contentUrl;
    private NavHelper mHelper;

    public static void show(Context context, String contentUrl) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("contentUrl", contentUrl);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initData() {
        super.initData();
        contentUrl = getIntent().getStringExtra("contentUrl");
        if (contentUrl != null) {
            webView.loadUrl(contentUrl);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }

}
