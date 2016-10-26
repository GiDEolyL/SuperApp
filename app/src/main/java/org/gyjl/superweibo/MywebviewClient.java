package org.gyjl.superweibo;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2016/10/26.
 */

public class MywebviewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        System.out.println(url);
        return false;
    }
}
