package com.roots.app.mvp.presenter;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.roots.app.app.utils.RxUtils;
import com.roots.app.mvp.contract.IndexContract;
import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.index.IndexBean;
import com.roots.app.mvp.model.entity.store.StoreBean;

import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * @Classname IndexPresenter
 * @Description TODO
 * @Date 2020/8/23 15:09
 * @Created by bird
 */
@FragmentScope
public class IndexPresenter extends BasePresenter<IndexContract.Model, IndexContract.View> {

    @Inject
    RxErrorHandler rxErrorHandler;

    @Inject
    public IndexPresenter(IndexContract.Model model, IndexContract.View rootView) {
        super(model, rootView);
    }

    public void index() {
        mModel.index()
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<IndexBean>>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<IndexBean> baseResponse) {
                        if (baseResponse.isSuccess()) {
                            mRootView.index(baseResponse.getData());
                        } else {
                            mRootView.showMessage(baseResponse.getMsg());
                        }
                    }
                });
    }

    public void getNearBy() {
        mModel.getNearBy()
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<List<StoreBean>>>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<List<StoreBean>> baseResponse) {
                        if (baseResponse.isSuccess()) {
                            mRootView.getNearBy(baseResponse.getData());
                        } else {
                            mRootView.showMessage(baseResponse.getMsg());
                        }
                    }
                });
    }
}


