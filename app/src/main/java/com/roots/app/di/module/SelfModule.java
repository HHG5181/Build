package com.roots.app.di.module;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.roots.app.mvp.contract.SelfContract;
import com.roots.app.mvp.model.UserModel;

import dagger.Module;
import dagger.Provides;

/**
 * @Classname SelfModule
 * @Description TODO
 * @Date 2020/8/23 16:11
 * @Created by bird
 */
@Module
public class SelfModule {

    private SelfContract.View view;

    public SelfModule(SelfContract.View view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    public SelfContract.View provideBaseView() {
        return view;
    }

    @Provides
    @FragmentScope
    public UserModel provideBaseModel(IRepositoryManager repositoryManager){
        return new UserModel(repositoryManager);
    }
}
