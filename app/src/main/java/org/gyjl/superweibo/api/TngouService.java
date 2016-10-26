package org.gyjl.superweibo.api;

import org.gyjl.superweibo.model.GalleryClassList;
import org.gyjl.superweibo.model.GalleryList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

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
    Call<GalleryClassList> getGalleryClassify();

    /**
     * 定义网址，并且指定GET请求的查询参数 id=xxx
     * x形成 tnfs/api/list?id=xxx
     * @param id
     * @return
     */
    @GET("tnfs/api/list")
    Call<GalleryList> getListById(@Query("id") String id);
}
