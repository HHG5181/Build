package com.roots.app.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.roots.app.di.module.IndexModule;
import com.roots.app.mvp.ui.fragment.index.IndexFragment;
import com.roots.app.mvp.ui.fragment.index.NearbyFragment;
import com.roots.app.mvp.ui.fragment.index.ReommendFragment;

import dagger.Component;

/**
 * @Classname IndexComponent
 * @Description TODO
 * @Date 2020/8/23 15:11
 * @Created by bird
 */
@FragmentScope
@Component(modules = IndexModule.class, dependencies = AppComponent.class)
public interface IndexComponent {
    void inject(IndexFragment indexFragment);
    void inject(NearbyFragment nearbyFragment);
    void inject(ReommendFragment reommendFragment);
}


