package com.roots.app.mvp.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import com.roots.app.app.base.BaseSupportActivity;
import com.roots.app.mvp.ui.activity.MainActivity;
import com.roots.app.mvp.utils.AppConstants;
import com.roots.app.mvp.utils.SpUtils;

/**
 * @Classname SplashAvtivity
 * @Description TODO
 * @Date 2020/8/25 20:59
 * @Created by bird
 */
public class SplashActivity extends BaseSupportActivity {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return 0;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //判断是否第一次开启应用
        boolean isFirstOpen = (boolean) SpUtils.get(this, AppConstants.FIRST_OPEN, false);
        // 如果是第一次启动，则先进入 功能引导页
        if (isFirstOpen) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        // 如果不是第一次启动app，则正常显示启动屏
        new Handler().postDelayed(this::enterHomeActivity, 1000);
    }

    private void enterHomeActivity() {
        String token = (String) SpUtils.get(mContext, AppConstants.Api.TOKEN, "");
        if (!TextUtils.isEmpty(token)) {
            startActivity(new Intent(mContext, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(mContext, LoginActivity.class));
            finish();
        }
    }

    @Override
    public void post(Runnable runnable) {

    }
}
