package com.roots.app.mvp.http.api.service;

import com.roots.app.mvp.model.entity.AddressBean;
import com.roots.app.mvp.model.entity.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AddressService {

    @GET("/user/address/list")
    Observable<BaseResponse<List<AddressBean>>> list();

    @POST("/user/address/add")
    @FormUrlEncoded
    Observable<BaseResponse<List<AddressBean>>> addAddress(
            @Field("username") String username,
            @Field("phone") String phone,
            @Field("address") String address,
            @Field("isDefault") Integer isDefault
    );
}
