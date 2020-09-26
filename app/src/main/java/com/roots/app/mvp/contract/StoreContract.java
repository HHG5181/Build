package com.roots.app.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.store.StoreBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author : bird
 * @Classname : StorePresenter
 * @Description : TODO
 * @Date : 2020/8/31 14:33
 */

public interface StoreContract {

    interface View extends IView {
        void detail(StoreBean data);

        void follow(String msg);
    }


    interface Model extends IModel {
        Observable<BaseResponse<StoreBean>> detail(int store_id);

        /**
         * 关注店铺
         * @param store_id
         * @return
         */
        Observable<BaseResponse<List<String>>> follow(int store_id);

        /**
         * 清空商户购物车
         * @param store_id
         * @return
         */
        Observable<BaseResponse<List<String>>> clear(int store_id);
    }
}
