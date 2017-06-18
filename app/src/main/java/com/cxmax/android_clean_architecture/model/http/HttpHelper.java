package com.cxmax.android_clean_architecture.model.http;

import com.cxmax.android_clean_architecture.model.bean.GankItemBean;
import com.cxmax.android_clean_architecture.model.http.response.GankHttpResponse;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/6/18.
 */

public interface HttpHelper {
    Flowable<GankHttpResponse<List<GankItemBean>>> fetchTechList(String tech, int num, int page);
}
