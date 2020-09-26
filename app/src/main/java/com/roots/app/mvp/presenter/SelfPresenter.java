package com.roots.app.mvp.presenter;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.mvp.IModel;
import com.roots.app.app.utils.RxUtils;
import com.roots.app.mvp.contract.SelfContract;
import com.roots.app.mvp.model.UserModel;
import com.roots.app.mvp.model.entity.BaseResponse;

import java.io.File;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Classname SelfPresenter
 * @Description TODO
 * @Date 2020/8/23 16:10
 * @Created by bird
 */
@FragmentScope
public class SelfPresenter extends BasePresenter<IModel, SelfContract.View> {

    @Inject
    UserModel userModel;

    @Inject
    public SelfPresenter(SelfContract.View rootView) {
        super(rootView);
    }

    @Inject
    RxErrorHandler rxErrorHandler;

    public void updateUserImage(String upload_file) {
        MultipartBody.Part face = MultipartBody.Part.createFormData("upload_file", "header_image.png", RequestBody.create(MediaType.parse("multipart/form-data"), new File(upload_file)));
        userModel.updateUserImage(face)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<String>>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<String> stringBaseResponse) {
                        if (stringBaseResponse.isSuccess()){
                            mRootView.updateUserImageSuccess();
                        } else {
                            mRootView.showMessage(stringBaseResponse.getMsg());
                        }
                    }
                });
    }

}

