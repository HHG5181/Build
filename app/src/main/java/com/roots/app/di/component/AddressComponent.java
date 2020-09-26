package com.roots.app.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.roots.app.di.module.AddressModule;
import com.roots.app.mvp.ui.activity.self.AddAddressActivity;
import com.roots.app.mvp.ui.activity.self.AddressActivity;

import dagger.Component;

@ActivityScope
@Component(modules = AddressModule.class, dependencies = AppComponent.class)
public interface AddressComponent {
    void inject(AddressActivity addressActivity);

    void inject(AddAddressActivity addressActivity);
}
