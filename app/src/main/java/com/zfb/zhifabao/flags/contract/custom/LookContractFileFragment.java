package com.zfb.zhifabao.flags.contract.custom;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.app.Fragment;
import com.zfb.zhifabao.common.widget.app.AdvancedWebView;

import butterknife.BindView;


public class LookContractFileFragment extends Fragment implements AdvancedWebView.Listener {
    @BindView(R.id.look_web_view)
    AdvancedWebView lookWebView;
    private  String look_url ;

    public LookContractFileFragment() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_look_contract_file;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        look_url =getArguments().getString(Common.Constance.LOOK_CONTRACT_FILE_URL);
       // lookWebView.setListener(getActivity(),this);
        lookWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Application.showToast(message);
                return false;
            }

        });
        //Common.Constance.BASE_TO_LOOK_URL+look_url
        lookWebView.loadUrl(Common.Constance.BASE_TO_LOOK_URL+look_url);
    }


    @Override
    public void onResume() {
        super.onResume();
        lookWebView.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        lookWebView.onPause();
    }

    @Override
    public void onDestroy() {
       // lookWebView.onDestroy();
        super.onDestroy();

    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {
           Application.showToast("加载完成！");
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }
}
