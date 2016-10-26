package org.gyjl.superweibo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.gyjl.superweibo.api.TngouService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TngouService mTngouService;

    //http://www.tngou.net/tnfs/api/classify
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btnGetClassify(View view) {
        if (mTngouService == null) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl("http://www.tngou.net/");
            //支持服务器返回的数据转换为字符串
            builder.addConverterFactory(ScalarsConverterFactory.create());
            //添加Gson转换      应该先添加基本数据类型转换
            builder.addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();

            //创建TngouService接口的实现，用于发起的网络请求
            mTngouService = retrofit.create(TngouService.class);
        }
        //2.调用Service的方法
        //方法返回Call对象，用于实际的网络请求，并且返回结果
        Call<String> call = mTngouService.getGalleryClassify();
        //同步代码
//        call.execute();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //请求成功 返回数据 注意不代表状态码为200，包含 405 400等其他状态码
                String body = response.body();
                Log.d("TAG", "onResponse: "+body);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void btnLoadFile(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,998);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==998) {
            if (resultCode==RESULT_OK) {
                Bitmap bmp=data.getParcelableExtra("data");
                ByteArrayOutputStream bout=new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG,100,bout);
                byte[] bytes = bout.toByteArray();
                try {
                    bout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                RequestBody body = RequestBody.create(MediaType.parse("image/png"), bytes);
//                RequestBody.create(MediaType.parse("text"),"这是一个文件");
//                mTngouService.updateFile(body,);
            }
        }

    }
}
