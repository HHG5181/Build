package com.roots.app.mvp.http.api.service;

import com.roots.app.mvp.model.entity.BaseResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @Classname UserService
 * @Description TODO
 * @Date 2020/8/25 19:08
 * @Created by bird
 */
public interface UserService {
    @Multipart
    @POST(value = "plugs/uploadImg")
    Observable<BaseResponse<String>> updateUserImage(
            @Part MultipartBody.Part upload_file
    );
}

