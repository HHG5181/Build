package com.roots.app.mvp.http.api.service;

import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.store.StoreBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author : bird
 * @Classname : StoreService
 * @Description : TODO
 * @Date : 2020/8/31 17:50
 */

public interface StoreService {

    @POST("store/detail")
    Observable<BaseResponse<StoreBean>> detail(
            @Query("store_id") int store_id
    );

    @GET("store/follow")
    Observable<BaseResponse<List<String>>> follow(
            @Query("store_id") int store_id
    );

    @GET("cart/clear")
    Observable<BaseResponse<List<String>>> clear(
            @Query("store_id") int store_id
    );

}
