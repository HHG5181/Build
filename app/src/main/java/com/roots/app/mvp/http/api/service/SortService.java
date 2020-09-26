package com.roots.app.mvp.http.api.service;

import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.sort.SortBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author : bird
 * @Classname : SortService
 * @Description : 分类接口
 * @Date : 2020/8/27 15:30
 */
public interface SortService {

    @GET("cate/list")
    Observable<BaseResponse<List<SortBean>>> getList();
}
