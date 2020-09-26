package com.roots.app.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.roots.app.mvp.contract.CouponContract;
import com.roots.app.mvp.model.CouponModel;

import dagger.Module;
import dagger.Provides;

@Module
public class CouponModule {

    private CouponContract.View view;

    public CouponModule(CouponContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public CouponContract.Model provideModel(IRepositoryManager repositoryManager) {
        return new CouponModel(repositoryManager);
    }

    @ActivityScope
    @Provides
    public CouponContract.View provideView() {
        return view;
    }

}
