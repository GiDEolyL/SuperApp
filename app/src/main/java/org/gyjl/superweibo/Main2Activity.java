package org.gyjl.superweibo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.gyjl.superweibo.api.SinaService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Main2Activity extends AppCompatActivity {
    private SinaService mSinaService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        WebView webView = (WebView) findViewById(R.id.webView);

        Retrofit.Builder builder1 = new Retrofit.Builder();
        builder1.baseUrl("https://api.weibo.com/");
        builder1.addConverterFactory(ScalarsConverterFactory.create());
        builder1.addConverterFactory(GsonConverterFactory.create());
        Retrofit r2 = builder1.build();
        mSinaService = r2.create(SinaService.class);
        MywebviewClient client = new MywebviewClient();
        webView.setWebViewClient(client);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("https://api.weibo.com/oauth2/authorize?client_id=98605433&redirect_uri=https://api.weibo.com/oauth2/default.html");
    }
}
