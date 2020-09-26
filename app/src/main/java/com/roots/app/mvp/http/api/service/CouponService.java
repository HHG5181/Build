package com.roots.app.mvp.http.api.service;

import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.CouponBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @Classname CouponService
 * @Description TODO
 * @Date 2020/8/25 18:05
 * @Created by bird
 */

public interface CouponService {

    @GET("/user/coupon/list")
    Observable<BaseResponse<List<CouponBean>>> list();
}

