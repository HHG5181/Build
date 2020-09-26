package com.roots.app.mvp.http.api.service;

import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.JWTBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author : bird
 * @Classname : LoginService
 * @Description : 登陆接口
 * @Date : 2020/8/25 21:18
 */
public interface LoginService {

    @POST("plugs/code")
    Observable<BaseResponse<List<String>>> sendSms(
            @Query("phone") String phone
    );

    @FormUrlEncoded
    @POST("user.login/index")
    Observable<BaseResponse<JWTBean>> login(
            @Field("login_type") String login_type,
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("code") String code
    );

    @FormUrlEncoded
    @POST("user.login/retrieve")
    Observable<BaseResponse<List<String>>> retrieve(
            @Field("phone") String phone,
            @Field("code") String code,
            @Field("password") String password
    );

}

