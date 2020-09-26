package com.roots.app.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.roots.app.di.module.StoreModule;
import com.roots.app.mvp.ui.activity.store.StoreActivity;
import com.roots.app.mvp.ui.fragment.store.SDetailFragment;
import com.roots.app.mvp.ui.fragment.store.SGoodsFragment;

import dagger.Component;

/**
 * @author : bird
 * @Classname : StoreComponent
 * @Description : TODO
 * @Date : 2020/8/31 14:38
 */
@ActivityScope
@Component(modules = StoreModule.class, dependencies = AppComponent.class)
public interface StoreComponent {

    void inject(StoreActivity storeActivity);

    void inject(SGoodsFragment sGoodsFragment);

    void inject(SDetailFragment sDetailFragment);
}
