package com.roots.app.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.roots.app.di.module.OrderModule;
import com.roots.app.mvp.ui.activity.self.OrderActivity;

import dagger.Component;

@ActivityScope
@Component(modules = OrderModule.class, dependencies = AppComponent.class)
public interface OrderComponent {
    void inject(OrderActivity orderActivity);
}
