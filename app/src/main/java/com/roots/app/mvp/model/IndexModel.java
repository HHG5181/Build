package com.roots.app.mvp.model;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.roots.app.mvp.contract.IndexContract;
import com.roots.app.mvp.http.api.service.IndexService;
import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.index.IndexBean;
import com.roots.app.mvp.model.entity.store.StoreBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @Classname IndexModel
 * @Description TODO
 * @Date 2020/8/23 15:08
 * @Created by bird
 */

public class IndexModel extends BaseModel implements IndexContract.Model {

    public IndexModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseResponse<IndexBean>> index() {
        return mRepositoryManager.obtainRetrofitService(IndexService.class).index();
    }

    @Override
    public Observable<BaseResponse<List<StoreBean>>> getNearBy() {
        return mRepositoryManager.obtainRetrofitService(IndexService.class).getNearBy();

    }
}

