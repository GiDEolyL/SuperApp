package org.gyjl.superweibo;

import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Project: ${PACKAGE_NAME}
 * Created by Administrator
 * Date: 2016/10/26.
 */

public class MywebviewClient extends WebViewClient {

    private MyCallback mCallback;

    public interface MyCallback {
        void onGetToken(String code);
    }

    public MywebviewClient(MyCallback callback) {
        mCallback = callback;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.d("Test", "shouldOverrideUrlLoading: "+url);
        int index = url.lastIndexOf("=");
        String code = url.substring(index + 1);
        Log.d("Test", "shouldOverrideUrlLoading: " + code);
        mCallback.onGetToken(code);
        return false;
    }
}
