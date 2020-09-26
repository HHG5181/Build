package com.roots.app.mvp.model;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.roots.app.mvp.contract.CouponContract;
import com.roots.app.mvp.http.api.service.CouponService;
import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.CouponBean;

import java.util.List;

import io.reactivex.Observable;

public class CouponModel extends BaseModel implements CouponContract.Model {

    public CouponModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseResponse<List<CouponBean>>> list() {
        return mRepositoryManager.obtainRetrofitService(CouponService.class).list();
    }
}
