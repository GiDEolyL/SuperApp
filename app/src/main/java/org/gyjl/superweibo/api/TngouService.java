package org.gyjl.superweibo.api;

import retrofit2.Call;
import retrofit2.http.GET;

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
}
