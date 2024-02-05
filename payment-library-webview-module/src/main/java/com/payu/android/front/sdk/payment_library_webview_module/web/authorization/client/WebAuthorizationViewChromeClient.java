package com.payu.android.front.sdk.payment_library_webview_module.web.authorization.client;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

public class WebAuthorizationViewChromeClient extends WebChromeClient {

    private static final int MAX_PROGRESS = 100;

    private WeakReference<ProgressBar> mProgressBar;

    public WebAuthorizationViewChromeClient(ProgressBar progressBar) {
        mProgressBar = new WeakReference<ProgressBar>(progressBar);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        ProgressBar progressBar = mProgressBar.get();

        if (progressBar != null) {
            updateProgress(progressBar, newProgress);
        }
    }

    private void updateProgress(ProgressBar progressBar, int newProgress) {
        progressBar.setProgress(newProgress);

        if (newProgress == MAX_PROGRESS) {
            progressBar.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
        }
    }
}
