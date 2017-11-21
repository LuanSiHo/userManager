package com.hosiluan.usermanager;

import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

public class CompanyWebViewActivity extends AppCompatActivity {


    private WebView mWebView;
    private ImageButton backImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_web_view);
        mWebView = (WebView) findViewById(R.id.webview_company);
        mWebView.loadUrl("https://jv-it.com.vn/");
        mWebView.setWebViewClient(new SSLTolerentWebViewClient());
        setView();
        setEvent();

    }
    private void setView(){
        backImageButton = (ImageButton) findViewById(R.id.img_btn_company_webview_back);
    }

    private void setEvent(){
        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private class SSLTolerentWebViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignore SSL certificate errors
        }

    }
}
