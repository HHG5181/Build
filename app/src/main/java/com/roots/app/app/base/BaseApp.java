package com.roots.app.app.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;

import com.jess.arms.base.App;
import com.jess.arms.base.delegate.AppDelegate;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.Preconditions;

public class BaseApp extends Application implements App {

    private AppDelegate mAppDelegate;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public static Context getInstance() {
        return mContext;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mContext = base;
        if (this.mAppDelegate == null) {
            this.mAppDelegate = new AppDelegate(base);
        }
        this.mAppDelegate.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (this.mAppDelegate != null) {
            this.mAppDelegate.onCreate(this);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (this.mAppDelegate != null) {
            this.mAppDelegate.onTerminate(this);
        }
    }

    @Override
    @NonNull
    public AppComponent getAppComponent() {
        Preconditions.checkNotNull(this.mAppDelegate, "%s cannot be null", new Object[]{AppDelegate.class.getName()});
        Preconditions.checkState(this.mAppDelegate instanceof App, "%s must be implements %s", new Object[]{AppDelegate.class.getName(), App.class.getName()});
        return ((App) this.mAppDelegate).getAppComponent();
    }

}
