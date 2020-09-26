package com.roots.app.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.roots.app.di.module.GoodsModule;
import com.roots.app.mvp.ui.activity.goods.GoodsDetailActivity;

import dagger.Component;

@ActivityScope
@Component(modules = GoodsModule.class, dependencies = AppComponent.class)
public interface GoodsComponent  {
    void inject(GoodsDetailActivity goodsDetailActivity);
}
