package com.roots.app.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.lljjcoder.bean.CustomCityData;
import com.roots.app.app.utils.RxUtils;
import com.roots.app.mvp.contract.AddressContract;
import com.roots.app.mvp.model.entity.AddressBean;
import com.roots.app.mvp.model.entity.BaseResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@ActivityScope
public class AddressPresenter extends BasePresenter<AddressContract.Model, AddressContract.View> {

    private RxErrorHandler rxErrorHandler;

    @Inject
    public AddressPresenter(AddressContract.Model model, AddressContract.View rootView, RxErrorHandler rxErrorHandler) {
        super(model, rootView);
        this.rxErrorHandler = rxErrorHandler;
    }

    public void cityData() {
        mModel.cityData()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {})
                .subscribeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();
                }).compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<List<CustomCityData>>>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<List<CustomCityData>> baseResponse) {
                        mRootView.cityData(baseResponse.getData());
                    }
                });
    }

    public void list() {
        mModel.list()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {})
                .subscribeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();
                }).compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<List<AddressBean>>>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<List<AddressBean>> baseResponse) {
                        mRootView.addressList(baseResponse.getData());
                    }
                });
    }

    public void addAddress(String username, String phone, String address, Integer isDefault) {
        mModel.addAddress(username, phone, address, isDefault)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<List<AddressBean>>>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<List<AddressBean>> listBaseResponse) {
                        if (listBaseResponse.isSuccess()) {
                            mRootView.addressList(listBaseResponse.getData());
                        } else {
                            mRootView.showMessage(listBaseResponse.getMsg());
                        }
                    }
                });
    }
}
