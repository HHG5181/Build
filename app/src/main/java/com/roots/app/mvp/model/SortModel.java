package com.roots.app.mvp.model;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.roots.app.mvp.contract.SortContract;
import com.roots.app.mvp.http.api.service.SortService;
import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.sort.SortBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author : bird
 * @Classname : SortModel
 * @Description : TODO
 * @Date : 2020/8/27 2:48
 */

public class SortModel extends BaseModel implements SortContract.Model {

    public SortModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseResponse<List<SortBean>>> getList() {
        return mRepositoryManager.obtainRetrofitService(SortService.class).getList();
    }
}
