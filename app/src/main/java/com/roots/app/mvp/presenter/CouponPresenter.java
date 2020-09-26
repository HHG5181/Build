package com.roots.app.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.roots.app.mvp.contract.CouponContract;
import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.CouponBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * @Classname CouponActivity
 * @Description TODO
 * @Date 2020/8/25 18:02
 * @Created by bird
 */
@ActivityScope
public class CouponPresenter extends BasePresenter<CouponContract.Model, CouponContract.View> {

    private RxErrorHandler rxErrorHandler;

    @Inject
    public CouponPresenter(CouponContract.Model model, CouponContract.View rootView, RxErrorHandler rxErrorHandler) {
        super(model, rootView);
        this.rxErrorHandler = rxErrorHandler;
    }

    public void list() {
        mModel.list()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏进度条
                }).compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<List<CouponBean>>>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<List<CouponBean>> baseResponse) {
                        mRootView.couponList(baseResponse.getData());
                    }
                });
    }
}


