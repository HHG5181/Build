package com.roots.app.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.roots.app.di.module.LoginModule;
import com.roots.app.mvp.ui.activity.login.ForgotActivity;
import com.roots.app.mvp.ui.activity.login.LoginActivity;

import dagger.Component;

@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);

    void inject(ForgotActivity activity);
}
