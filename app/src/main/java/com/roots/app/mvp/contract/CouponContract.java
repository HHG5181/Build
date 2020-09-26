package com.roots.app.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.CouponBean;

import java.util.List;

import io.reactivex.Observable;

public interface CouponContract {

    interface View extends IView {
        void couponList(List<CouponBean> data);
    }


    interface Model extends IModel {
        Observable<BaseResponse<List<CouponBean>>> list();
    }
}
