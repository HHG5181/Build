package com.roots.app.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.JWTBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @Classname LoginContract
 * @Description TODO
 * @Date 2020/8/25 20:53
 * @Created by bird
 */

public interface LoginContract {
    interface View extends IView {
        void loginResult(JWTBean data);

    }

    interface Model extends IModel {
        //发送验证码
        Observable<BaseResponse<List<String>>> sendSms(String phone);

        //登陆
        Observable<BaseResponse<JWTBean>> login(String login_type, String phone, String password, String code);

        //找回密码
        Observable<BaseResponse<List<String>>> retrieve(String phone, String code, String password);
    }
}

