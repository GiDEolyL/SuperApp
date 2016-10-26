package org.gyjl.superweibo.api;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by Administrator on 2016/10/26.
 */

public interface SinaService {

    @FormUrlEncoded
    @POST("oauth2/authorize")
    Call<String> login(@Field("client_id") String client_id, @Field("redirect_uri") String redirect_uri);
}
