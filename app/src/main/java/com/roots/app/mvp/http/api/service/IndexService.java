package com.roots.app.mvp.http.api.service;

import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.index.IndexBean;
import com.roots.app.mvp.model.entity.store.StoreBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author : bird
 * @Description : TODO
 * @Date : 2020/8/28 13:48
 */

public interface IndexService {

    @POST("index/getHome")
    Observable<BaseResponse<IndexBean>> index();

    @GET("store/nearby")
    Observable<BaseResponse<List<StoreBean>>> getNearBy();
}
