package com.example.demoretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewActivity extends AppCompatActivity {
    private WebView webviewDetail;
    String link = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        link = intent.getStringExtra("link");
        Log.d("zxcvb", link);
        setContentView(R.layout.activity_webview);
        webviewDetail = (WebView) findViewById(R.id.webviewDetail);
        webviewDetail.getSettings().setLoadsImagesAutomatically(true);
        webviewDetail.getSettings().setJavaScriptEnabled(true);
        webviewDetail.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webviewDetail.loadUrl(link);
        webviewDetail.setWebViewClient(new WebViewClient());
    }
}
