package com.roots.app.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.roots.app.mvp.model.entity.BaseResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * @Classname SelfContract
 * @Description TODO
 * @Date 2020/8/23 16:09
 * @Created by bird
 */

public interface SelfContract {

    interface View extends IView {
        void updateUserImageSuccess();
    }

    interface Model extends IModel {
        Observable<BaseResponse<String>> updateUserImage(MultipartBody.Part upload_file);

    }

}
