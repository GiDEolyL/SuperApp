package org.gyjl.superweibo.web;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Project: org.gyjl.superweibo.web
 * Created by Yasin
 * Date: 2016-10-26.
 */

public class MyWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        boolean ret=false;

        Log.d("WebView", "shouldOverrideUrlLoading: "+url);

        return ret;
    }

}
