package com.roots.app.mvp.model;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.lljjcoder.bean.CustomCityData;
import com.roots.app.mvp.contract.AddressContract;
import com.roots.app.mvp.http.api.service.AddressService;
import com.roots.app.mvp.http.api.service.CommonService;
import com.roots.app.mvp.model.entity.AddressBean;
import com.roots.app.mvp.model.entity.BaseResponse;

import java.util.List;

import io.reactivex.Observable;

public class AddressModel extends BaseModel implements AddressContract.Model {
    public AddressModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseResponse<List<CustomCityData>>> cityData() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).cityData();
    }

    @Override
    public Observable<BaseResponse<List<AddressBean>>> list() {
        return mRepositoryManager.obtainRetrofitService(AddressService.class).list();
    }

    @Override
    public Observable<BaseResponse<List<AddressBean>>> addAddress(String username, String phone, String address, Integer isDefault) {
        return mRepositoryManager.obtainRetrofitService(AddressService.class).addAddress(username, phone, address, isDefault);
    }

}
