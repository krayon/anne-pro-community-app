package com.obins.anne;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.obins.anne.viewpart.robinWebView;

public class Activity_Manual extends BaseActivity {
    private RelativeLayout backBtn;
    private WebView manual_webView;

    class C01231 implements OnClickListener {
        C01231() {
        }

        public void onClick(View arg0) {
            Activity_Manual.this.finish();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_manual);
        this.backBtn = (RelativeLayout) findViewById(C0182R.id.backBtn);
        this.backBtn.setOnClickListener(new C01231());
        this.manual_webView = (WebView) findViewById(C0182R.id.manual_webView);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        this.manual_webView.setWebViewClient(new robinWebView());
        this.manual_webView.getSettings().setJavaScriptEnabled(true);
        this.manual_webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.manual_webView.setScrollBarStyle(0);
        this.manual_webView.getSettings().setCacheMode(2);
        this.manual_webView.getSettings().setUseWideViewPort(true);
        this.manual_webView.getSettings().setSupportZoom(true);
        this.manual_webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        this.manual_webView.getSettings().setLoadWithOverviewMode(true);
        this.manual_webView.getContentHeight();
        this.manual_webView.loadUrl("http://www.obins.net/anne_manual_book/annepromanual.html");
    }
}
