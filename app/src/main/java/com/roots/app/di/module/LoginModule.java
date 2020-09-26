package com.roots.app.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.roots.app.mvp.contract.LoginContract;
import com.roots.app.mvp.model.LoginModel;

import dagger.Module;
import dagger.Provides;

/**
 * @Classname LoginModule
 * @Description TODO
 * @Date 2020/8/25 20:55
 * @Created by bird
 */

@Module
public class LoginModule {
    LoginContract.View view;

    public LoginModule(LoginContract.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    public LoginContract.Model provideModel(IRepositoryManager repositoryManager) {
        return new LoginModel(repositoryManager);
    }

    @Provides
    @ActivityScope
    public LoginContract.View provideView() {
        return view;
    }
}

