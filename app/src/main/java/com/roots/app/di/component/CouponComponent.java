package com.roots.app.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.roots.app.di.module.CouponModule;
import com.roots.app.mvp.ui.activity.self.CouponActivity;

import dagger.Component;

@ActivityScope
@Component(modules = CouponModule.class, dependencies = AppComponent.class)
public interface CouponComponent {
    void inject(CouponActivity activity);
}
