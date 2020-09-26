package com.roots.app.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.roots.app.di.module.SortModule;
import com.roots.app.mvp.ui.fragment.SortFragment;

import dagger.Component;

/**
 * @author : bird
 * @Classname : SortComponent
 * @Description : TODO
 * @Date : 2020/8/27 2:51
 */
@FragmentScope
@Component(modules = SortModule.class, dependencies = AppComponent.class)
public interface SortComponent {

    void inject(SortFragment sortFragment);

}
