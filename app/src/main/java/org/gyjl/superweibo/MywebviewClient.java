package org.gyjl.superweibo;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2016/10/26.
 */

public class MywebviewClient extends WebViewClient {

    public MyCallback mCallback;
    public interface MyCallback{
        void onGetToken(String code);
    }

    public MywebviewClient(MyCallback callback) {
        mCallback = callback;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        System.out.println(url);
        int index = url.lastIndexOf("=");
        String code = url.substring(index);
        mCallback.onGetToken(code);
        return false;
    }
}
