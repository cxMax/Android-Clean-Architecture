package com.cxmax.android_clean_architecture.model.http.api;

import com.cxmax.android_clean_architecture.model.bean.GankItemBean;
import com.cxmax.android_clean_architecture.model.http.response.GankHttpResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/6/18.
 */

public interface GankApis {

    String HOST = "http://gank.io/api/";

    /**
     * 技术文章列表
     */
    @GET("data/{tech}/{num}/{page}")
    Flowable<GankHttpResponse<List<GankItemBean>>> getTechList(@Path("tech") String tech, @Path("num") int num, @Path("page") int page);


}
