package com.roots.app.mvp.model;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.roots.app.mvp.contract.SelfContract;
import com.roots.app.mvp.http.api.service.UserService;
import com.roots.app.mvp.model.entity.BaseResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * @Classname UserModel
 * @Description TODO
 * @Date 2020/8/25 19:06
 * @Created by bird
 */

public class UserModel extends BaseModel implements SelfContract.Model {
    public UserModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseResponse<String>> updateUserImage(MultipartBody.Part upload_file) {
        return mRepositoryManager.obtainRetrofitService(UserService.class)
                .updateUserImage(upload_file);
    }

}

