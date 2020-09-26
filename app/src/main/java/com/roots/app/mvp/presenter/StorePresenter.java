package com.roots.app.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.roots.app.app.utils.RxUtils;
import com.roots.app.mvp.contract.StoreContract;
import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.store.StoreBean;

import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * @author : bird
 * @Classname : StorePresenter
 * @Description : TODO
 * @Date : 2020/8/31 14:32
 */
@ActivityScope
public class StorePresenter extends BasePresenter<StoreContract.Model, StoreContract.View> {

    private RxErrorHandler rxErrorHandler;

    @Inject
    public StorePresenter(StoreContract.Model model, StoreContract.View view, RxErrorHandler rxErrorHandler) {
        super(model, view);
        this.rxErrorHandler = rxErrorHandler;
    }

    public void detail(int store_id) {
        mModel.detail(store_id)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<StoreBean>>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<StoreBean> listBaseResponse) {
                        if (listBaseResponse.isSuccess()) {
                            mRootView.detail(listBaseResponse.getData());
                        } else {
                            mRootView.showMessage(listBaseResponse.getMsg());
                        }
                    }
                });
    }

    public void follow(int store_id) {
        mModel.follow(store_id)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<List<String>>>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<List<String>> listBaseResponse) {
                        mRootView.follow(listBaseResponse.getMsg());
                    }
                });
    }

    public void clear(int store_id) {
        mModel.clear(store_id)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<List<String>>>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<List<String>> listBaseResponse) {
                        mRootView.showMessage(listBaseResponse.getMsg());
                    }
                });
    }
}
