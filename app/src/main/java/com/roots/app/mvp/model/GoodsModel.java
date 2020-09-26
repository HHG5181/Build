package com.roots.app.mvp.model;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.roots.app.mvp.contract.GoodsContract;

public class GoodsModel extends BaseModel implements GoodsContract.Model {

    public GoodsModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
