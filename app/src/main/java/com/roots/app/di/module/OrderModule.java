package com.roots.app.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.roots.app.mvp.contract.OrderContract;
import com.roots.app.mvp.model.OrderModel;

import dagger.Module;
import dagger.Provides;

@Module
public class OrderModule {

    private OrderContract.View view;

    public OrderModule(OrderContract.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    public OrderContract.View provideBaseView() {
        return view;
    }

    @Provides
    @ActivityScope
    public OrderContract.Model provideBaseModel(IRepositoryManager repositoryManager){
        return new OrderModel(repositoryManager);
    }
}
