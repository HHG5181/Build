package com.roots.app.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.roots.app.mvp.contract.AddressContract;
import com.roots.app.mvp.model.AddressModel;

import dagger.Module;
import dagger.Provides;

@Module
public class AddressModule {

    private AddressContract.View view;

    public AddressModule(AddressContract.View view) {
        this.view =view;
    }

    @ActivityScope
    @Provides
    public AddressContract.Model provideCategoryModel(IRepositoryManager repositoryManager) {
        return new AddressModel(repositoryManager);
    }

    @ActivityScope
    @Provides
    public AddressContract.View provideCategoryView() {
        return view;
    }

}
