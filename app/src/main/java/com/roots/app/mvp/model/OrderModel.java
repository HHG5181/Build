package com.roots.app.mvp.model;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.roots.app.mvp.contract.OrderContract;

public class OrderModel extends BaseModel implements OrderContract.Model {

    public OrderModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
