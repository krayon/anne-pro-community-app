package com.obins.anne.viewpart;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class robinWebView extends WebViewClient {
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl(url);
        return true;
    }
}
