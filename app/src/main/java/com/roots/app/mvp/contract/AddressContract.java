package com.roots.app.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.lljjcoder.bean.CustomCityData;
import com.roots.app.mvp.model.entity.AddressBean;
import com.roots.app.mvp.model.entity.BaseResponse;

import java.util.List;

import io.reactivex.Observable;

public interface AddressContract {

    interface View extends IView {
        void cityData(List<CustomCityData> data);

        void addressList(List<AddressBean> data);
    }


    interface Model extends IModel {

        Observable<BaseResponse<List<CustomCityData>>> cityData();


        Observable<BaseResponse<List<AddressBean>>> list();

        Observable<BaseResponse<List<AddressBean>>> addAddress(
                String username,
                String phone,
                String address,
                Integer isDefault
        );
    }
}

