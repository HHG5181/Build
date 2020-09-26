package com.roots.app.mvp.model;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.roots.app.mvp.contract.StoreContract;
import com.roots.app.mvp.http.api.service.StoreService;
import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.store.StoreBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author : bird
 * @Classname : StoreModel
 * @Description : TODO
 * @Date : 2020/8/31 14:33
 */

public class StoreModel extends BaseModel implements StoreContract.Model {

    public StoreModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseResponse<StoreBean>> detail(int store_id) {
        return mRepositoryManager.obtainRetrofitService(StoreService.class).detail(store_id);
    }

    @Override
    public Observable<BaseResponse<List<String>>> follow(int store_id) {
        return mRepositoryManager.obtainRetrofitService(StoreService.class).follow(store_id);
    }

    @Override
    public Observable<BaseResponse<List<String>>> clear(int store_id) {
        return mRepositoryManager.obtainRetrofitService(StoreService.class).clear(store_id);
    }
}
