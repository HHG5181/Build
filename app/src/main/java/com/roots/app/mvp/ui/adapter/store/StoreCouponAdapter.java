package com.roots.app.mvp.ui.adapter.store;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.roots.app.R;
import com.roots.app.mvp.model.entity.goods.Counpon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StoreCouponAdapter extends BaseQuickAdapter<Counpon, BaseViewHolder> {


    public StoreCouponAdapter(@Nullable List<Counpon> data) {
        super(R.layout.item_store_coupon, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Counpon counpon) {
        baseViewHolder.setText(R.id.tv_coupon, counpon.getCoupen_text());
    }
}
