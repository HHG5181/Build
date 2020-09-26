package com.roots.app.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.roots.app.mvp.model.entity.BaseResponse;
import com.roots.app.mvp.model.entity.sort.SortBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author : bird
 * @Classname : SortContract
 * @Description : TODO
 * @Date : 2020/8/27 2:47
 */

public interface SortContract {
    interface View extends IView {
        void getList(List<SortBean> data);
    }


    interface Model extends IModel {
        Observable<BaseResponse<List<SortBean>>> getList();
    }
}
