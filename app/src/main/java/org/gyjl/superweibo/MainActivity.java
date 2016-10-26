package org.gyjl.superweibo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.gyjl.superweibo.api.TngouService;
import org.gyjl.superweibo.model.Gallery;
import org.gyjl.superweibo.model.GalleryClassList;
import org.gyjl.superweibo.model.GalleryList;
import org.gyjl.superweibo.model.Galleryclass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 大菠萝的activity
 */
public class MainActivity extends AppCompatActivity {

    private TngouService mTngouService;

    //http://www.tngou.net/tnfs/api/classify
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (mTngouService == null) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl("http://www.tngou.net/");
            //支持服务器返回的数据转换为字符串
            //必须添加基本的在添加Gson的转换器  不然会报错
            builder.addConverterFactory(ScalarsConverterFactory.create());

            builder.addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit = builder.build();

            //创建TngouService接口的实现，用于发起的网络请求
            mTngouService = retrofit.create(TngouService.class);
        }

    }

    public void btnGetClassify(View view) {

        //2.调用Service的方法
        //方法返回Call对象，用于实际的网络请求，并且返回结果
        Call<GalleryClassList> call = mTngouService.getGalleryClassify();
        //同步代码
//        call.execute();
        call.enqueue(new Callback<GalleryClassList>() {
            @Override
            public void onResponse(Call<GalleryClassList> call, Response<GalleryClassList> response) {
                //请求成功 返回数据 注意不代表状态码为200，包含 405 400等其他状态码
                if (response.isSuccessful()){
                    GalleryClassList body = response.body();
                    List<Galleryclass> list = body.getGalleryclasses();
                    for (Galleryclass galleryclass : list) {
                        String title = galleryclass.getTitle();
                        System.out.println(title);
                    }
                }

            }

            @Override
            public void onFailure(Call<GalleryClassList> call, Throwable t) {
                System.out.println("shibai");
            }
        });
    }

    public void btnGetList(View view) {
        //1.服务对象
        Call<GalleryList> listCall = mTngouService.getListById("1");
        listCall.enqueue(new Callback<GalleryList>() {
            @Override
            public void onResponse(Call<GalleryList> call, Response<GalleryList> response) {
                if (response.isSuccessful()){
                    GalleryList body = response.body();
                    List<Gallery> galleries = body.getGalleries();
                    for (Gallery gallery : galleries) {
                        String title = gallery.getTitle();
                        System.out.println("title"+title);
                    }
                    System.out.println("body"+body);
                }
            }

            @Override
            public void onFailure(Call<GalleryList> call, Throwable t) {

            }
        });
    }
}
