package org.gyjl.superweibo.api;

import org.gyjl.superweibo.model.ServerInfo;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/10/26.
 */

public interface SuperService {
    @GET("info")
    Call<String> getInfo();

    @GET("info/{item}")
    Call<String> getInfoItem(@Path("item") String item);

    /**
     * post请求 参数以  key=value&key=value 方式提交
     * FormUrlEncpded 代表数据形式 key=values
     * @param name
     * @param pwd
     * @return
     */
    @FormUrlEncoded
    @POST("info")
    Call<String> login(@Field("name") String name, @Field("pwd") String pwd);

    /**
     * PUT请求传递一个数据实体类，使用JSON传递
     * @return
     */
    @PUT("info")
    Call<String> updateInfo(@Body ServerInfo serverInfo);

    @Multipart
    @POST("file")
    Call<String> updateFile(@Part("imgae") RequestBody mFile);
}
