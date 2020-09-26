package com.roots.app.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.roots.app.mvp.contract.OrderContract;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

@ActivityScope
public class OrderPresenter extends BasePresenter<OrderContract.Model, OrderContract.View> {

    private RxErrorHandler rxErrorHandler;

    @Inject
    public OrderPresenter(OrderContract.Model model, OrderContract.View rootView, RxErrorHandler rxErrorHandler) {
        super(model, rootView);
        this.rxErrorHandler = rxErrorHandler;
    }
}

