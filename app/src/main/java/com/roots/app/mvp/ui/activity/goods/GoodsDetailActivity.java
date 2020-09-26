package com.roots.app.mvp.ui.activity.goods;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import com.roots.app.R;
import com.roots.app.app.base.BaseSupportActivity;
import com.roots.app.di.component.DaggerGoodsComponent;
import com.roots.app.di.module.GoodsModule;
import com.roots.app.mvp.contract.GoodsContract;
import com.roots.app.mvp.presenter.GoodsPresenter;

public class GoodsDetailActivity extends BaseSupportActivity<GoodsPresenter> implements GoodsContract.View {
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerGoodsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .goodsModule(new GoodsModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_goods_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void post(Runnable runnable) {

    }
}
