package com.roots.app.mvp.presenter;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.roots.app.app.utils.RxUtils;
import com.roots.app.mvp.contract.SortContract;
import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.sort.SortBean;

import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * @author : bird
 * @Classname : SortPresenter
 * @Description : TODO
 * @Date : 2020/8/27 2:49
 */
@FragmentScope
public class SortPresenter extends BasePresenter<SortContract.Model, SortContract.View> {

    private RxErrorHandler rxErrorHandler;

    @Inject
    public SortPresenter(SortContract.Model model, SortContract.View rootView, RxErrorHandler rxErrorHandler) {
        super(model, rootView);
        this.rxErrorHandler = rxErrorHandler;
    }

    public void getList() {
        mModel.getList()
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<List<SortBean>>>(rxErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<List<SortBean>> listBaseResponse) {
                        if (listBaseResponse.isSuccess()) {
                            mRootView.getList(listBaseResponse.getData());
                        } else {
                            mRootView.showMessage(listBaseResponse.getMsg());
                        }
                    }
                });
    }
}
