package com.roots.app.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.roots.app.mvp.contract.GoodsContract;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

@ActivityScope
public class GoodsPresenter extends BasePresenter<GoodsContract.Model, GoodsContract.View> {

    private RxErrorHandler rxErrorHandler;

    @Inject
    public GoodsPresenter(GoodsContract.Model model, GoodsContract.View rootView, RxErrorHandler rxErrorHandler) {
        super(model, rootView);
        this.rxErrorHandler = rxErrorHandler;
    }
}
