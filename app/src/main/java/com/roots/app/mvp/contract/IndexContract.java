package com.roots.app.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.index.IndexBean;
import com.roots.app.mvp.model.entity.store.StoreBean;

import java.util.List;

import io.reactivex.Observable;

public interface IndexContract {

    interface View extends IView {
        void index(IndexBean data);

        void getNearBy(List<StoreBean> data);
    }


    interface Model extends IModel {
        Observable<BaseResponse<IndexBean>> index();

        Observable<BaseResponse<List<StoreBean>>> getNearBy();

    }
}
