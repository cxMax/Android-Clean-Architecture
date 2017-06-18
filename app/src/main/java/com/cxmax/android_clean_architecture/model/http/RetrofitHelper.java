package com.cxmax.android_clean_architecture.model.http;

import com.cxmax.android_clean_architecture.model.bean.GankItemBean;
import com.cxmax.android_clean_architecture.model.http.api.GankApis;
import com.cxmax.android_clean_architecture.model.http.response.GankHttpResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/6/18.
 */

public class RetrofitHelper implements HttpHelper{

    private GankApis gankApiService;

    @Inject
    public RetrofitHelper(GankApis gankApiService) {
        this.gankApiService = gankApiService;
    }

    @Override
    public Flowable<GankHttpResponse<List<GankItemBean>>> fetchTechList(String tech, int num, int page) {
        return gankApiService.getTechList(tech , num ,page);
    }
}
