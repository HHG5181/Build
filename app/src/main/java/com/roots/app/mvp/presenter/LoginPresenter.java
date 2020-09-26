package com.roots.app.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.roots.app.app.utils.RxUtils;
import com.roots.app.mvp.contract.LoginContract;
import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.JWTBean;

import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * @Classname LoginPresenter
 * @Description TODO
 * @Date 2020/8/25 20:53
 * @Created by bird
 */
@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {

    RxErrorHandler rxErrorHandler;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView, RxErrorHandler rxErrorHandler) {
        super(model, rootView);
        this.rxErrorHandler = rxErrorHandler;
    }

    public void sendSms(String phone) {
        mModel.sendSms(phone)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<List<String>>>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<List<String>> stringBaseResponse) {
                        if (stringBaseResponse.isSuccess()) {
                            mRootView.showMessage(stringBaseResponse.getMsg());
                        } else {
                            mRootView.showMessage(stringBaseResponse.getMsg());
                        }
                    }
                });
    }

    public void login(String login_type, String phone, String password, String code) {
        mModel.login(login_type, phone, password, code)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<JWTBean>>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<JWTBean> jwt) {
                        if (jwt.isSuccess()) {
                            mRootView.loginResult(jwt.getData());
                        } {
                            mRootView.showMessage(jwt.getMsg());
                        }
                    }
                });
    }

    public void retrieve(String phone, String code, String password) {
        mModel.retrieve(phone, code, password)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<List<String>>>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<List<String>> stringBaseResponse) {
                        if (stringBaseResponse.isSuccess()) {
                            mRootView.showMessage(stringBaseResponse.getMsg());
                        } else {
                            mRootView.showMessage(stringBaseResponse.getMsg());
                        }
                    }
                });
    }
}

