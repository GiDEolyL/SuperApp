package org.gyjl.superweibo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.gyjl.superweibo.api.FileService;
import org.gyjl.superweibo.api.SuperService;
import org.gyjl.superweibo.api.TngouService;
import org.gyjl.superweibo.model.Gallery;
import org.gyjl.superweibo.model.GalleryClassList;
import org.gyjl.superweibo.model.GalleryList;
import org.gyjl.superweibo.model.Galleryclass;
import org.gyjl.superweibo.model.ServerInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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

    private SuperService mSuperService;


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
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://10.0.153.115:8080/rest/api/");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit r1=builder.build();
        mSuperService=r1.create(SuperService.class);

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

    public void btnGetVersion(View view) {
        Call<String> call = mSuperService.getInfoItem("version");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String body = response.body();
                    System.out.println("body"+body);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void btnPostLogin(View view) {
        //post请求的方式
        Call<String> call = mSuperService.login("admin", "123");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String body = response.body();
                    System.out.println("body"+body);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void btnPutJson(View view) {
        ServerInfo info = new ServerInfo();
        info.setAuthor("yx");
        info.setName("服务器");
        info.setUpdateTime(String.valueOf(System.currentTimeMillis()));
        info.setVersion("1.0");
        Call<String> call = mSuperService.updateInfo(info);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String body = response.body();
                    System.out.println("body"+body);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void btnUploadFile(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,998);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==998){
            if (resultCode== Activity.RESULT_OK){
                Bitmap bmp=data.getParcelableExtra("data");
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG,100,bout);
                byte[] bmData = bout.toByteArray();
                try {
                    bout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                RequestBody fileBody
                        = RequestBody.create(MediaType.parse("image/png"), bmData);
                Call<String> call = mSuperService.updateFile(fileBody);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String body = response.body();
                            System.out.println("body"+body);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        }
    }
}
