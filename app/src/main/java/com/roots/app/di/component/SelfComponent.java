package com.roots.app.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.roots.app.di.module.SelfModule;
import com.roots.app.mvp.ui.fragment.self.SelfFragment;

import dagger.Component;

/**
 * @Classname SelfComponent
 * @Description TODO
 * @Date 2020/8/23 16:11
 * @Created by bird
 */
@FragmentScope
@Component(modules = SelfModule.class, dependencies = AppComponent.class)
public interface SelfComponent {
    void inject(SelfFragment selfFragment);
}