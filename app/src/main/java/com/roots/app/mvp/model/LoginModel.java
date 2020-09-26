package com.roots.app.mvp.model;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.roots.app.mvp.contract.LoginContract;
import com.roots.app.mvp.http.api.service.LoginService;
import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.JWTBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @Classname LoginModel
 * @Description TODO
 * @Date 2020/8/25 20:55
 * @Created by bird
 */
public class LoginModel extends BaseModel implements LoginContract.Model {

    public LoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseResponse<List<String>>> sendSms(String phone) {
        return mRepositoryManager.obtainRetrofitService(LoginService.class).sendSms(phone);
    }

    @Override
    public Observable<BaseResponse<JWTBean>> login(String login_type, String phone, String password, String code) {
        return mRepositoryManager.obtainRetrofitService(LoginService.class).login(login_type, phone, password, code);
    }

    @Override
    public Observable<BaseResponse<List<String>>> retrieve(String phone, String code, String password) {
        return mRepositoryManager.obtainRetrofitService(LoginService.class).retrieve(phone, code, password);
    }
}

