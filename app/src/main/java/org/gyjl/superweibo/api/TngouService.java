package org.gyjl.superweibo.api;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;

/**
 * Created by Administrator on 2016/10/26.
 */

public interface TngouService {

    //方法 ->  接口请求
    //CALL 实际上是网络请求的工具，可以同步，异步请求数据

    /**
     * 定义一个方法 ，对应某一个请求网址，并且声明 数据返回的结构类型（通过 Convert Factory）
     * @return
     */
    @GET("tnfs/api/classify")
    Call<String> getGalleryClassify();

    @Multipart
    @POST("file")
    Call<String> updateFile(@Part("file")RequestBody body,@Part("description") RequestBody des);

    @FormUrlEncoded
    @POST("oauth2/access_token")
    Call<String> getAccessToken(@Field("client_id") String id,
                                @Field("client_secret") String secret,
                                @Field("grant_type") String type,
                                @Field("code") String code,
                                @Field("redirect_uri") String uri);
}
