package com.roots.app.mvp.http.api.service;

import com.lljjcoder.bean.CustomCityData;
import com.roots.app.mvp.model.entity.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author : bird
 * @Classname : CommonService
 * @Description : 公共接口
 * @Date : 2020/8/27 2:26
 */
public interface CommonService {

    @GET("plugs/region")
    Observable<BaseResponse<List<CustomCityData>>> cityData();
}
