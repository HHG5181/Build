package com.roots.app.mvp.model.entity.index;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecommendItem implements MultiItemEntity {

    private int goods_id;
    private String goods_name;
    private String selling_point;
    private int spec_type;
    private int goods_cate_id;
    private int deduct_stock_type;
    private int itemType;

    public RecommendItem(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
