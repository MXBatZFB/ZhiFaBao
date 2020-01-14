package com.zfb.zhifabao.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.app.Activity;
import com.zfb.zhifabao.common.app.Application;
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
    protected void initWidget() {
        super.initWidget();
        setStatuTrans();
    }

    /**
     * 这是状态栏透明的方法
     */
    private void setStatuTrans() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initData() {
        super.initData();
        contentUrl = getIntent().getStringExtra("contentUrl");
        WebSettings settings = webView.getSettings();
      // settings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
       settings.setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
      //  settings.setSupportZoom(true);//是否可以缩放，默认true
        settings.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        settings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        settings.setAppCacheEnabled(true);//是否使用缓存
        settings.setDomStorageEnabled(true);//DOM Storage
        if (contentUrl != null) {
            webView.setWebChromeClient(new WebChromeClient());
            webView.loadUrl(contentUrl);
        }else {
            Application.showToast("url不能为空！");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
