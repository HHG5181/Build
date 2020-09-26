package com.roots.app.di.module;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.roots.app.mvp.contract.IndexContract;
import com.roots.app.mvp.model.IndexModel;

import dagger.Module;
import dagger.Provides;

/**
 * @Classname IndexModule
 * @Description TODO
 * @Date 2020/8/23 15:11
 * @Created by bird
 */
@Module
public class IndexModule {
    private IndexContract.View view;

    public IndexModule(IndexContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    public IndexContract.View provideView() {
        return view;
    }

    @FragmentScope
    @Provides
    public IndexContract.Model provideModel(IRepositoryManager iRepositoryManager) {
        return new IndexModel(iRepositoryManager);
    }
}

