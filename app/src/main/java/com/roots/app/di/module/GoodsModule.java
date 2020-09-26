package com.roots.app.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.roots.app.mvp.contract.GoodsContract;
import com.roots.app.mvp.model.GoodsModel;

import dagger.Module;
import dagger.Provides;

@Module
public class GoodsModule  {

    private GoodsContract.View view;

    public GoodsModule(GoodsContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public GoodsContract.View provideView() {
        return view;
    }

    @ActivityScope
    @Provides
    public GoodsContract.Model provideModel(IRepositoryManager iRepositoryManager) {
        return new GoodsModel(iRepositoryManager);
    }
}
