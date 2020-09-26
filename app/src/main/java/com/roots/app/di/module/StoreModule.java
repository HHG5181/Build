package com.roots.app.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.roots.app.mvp.contract.StoreContract;
import com.roots.app.mvp.model.StoreModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author : bird
 * @Classname : StoreModule
 * @Description : TODO
 * @Date : 2020/8/31 14:36
 */
@Module
public class StoreModule {

    private StoreContract.View view;

    public StoreModule(StoreContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public StoreContract.Model provideStoreModel(IRepositoryManager repositoryManager) {
        return new StoreModel(repositoryManager);
    }

    @ActivityScope
    @Provides
    public StoreContract.View provideSortView() {
        return view;
    }


}
