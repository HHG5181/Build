package com.roots.app.mvp.ui.adapter.store;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.roots.app.R;
import com.roots.app.mvp.model.entity.store.StoreDetail;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.roots.app.mvp.model.entity.store.StoreDetail.COUPON;
import static com.roots.app.mvp.model.entity.store.StoreDetail.FIRST_TITLE;
import static com.roots.app.mvp.model.entity.store.StoreDetail.PUBLISH;
import static com.roots.app.mvp.model.entity.store.StoreDetail.TITLE;

public class StoreDetailAdapter extends BaseMultiItemQuickAdapter<StoreDetail, BaseViewHolder> {

    public StoreDetailAdapter(@Nullable List<StoreDetail> data) {
        super(data);
        addItemType(FIRST_TITLE, R.layout.item_store_first_title);
        addItemType(TITLE, R.layout.item_store_notice);
        addItemType(COUPON, R.layout.item_store_detail_coupon);
        addItemType(PUBLISH, R.layout.item_store_publish);

    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, StoreDetail storeDetail) {
        switch (storeDetail.getItemType()) {
            case FIRST_TITLE:
                baseViewHolder.setText(R.id.tv_item_text_first_title,storeDetail.getTitle());
                break;
            case TITLE:
                baseViewHolder.setText(R.id.tv_store_notice,storeDetail.getTitle());
                break;
            case COUPON:
                baseViewHolder.setText(R.id.tv_item_text_coupon_title,storeDetail.getCouponTitle());
                baseViewHolder.setText(R.id.tv_item_text_coupon_content,storeDetail.getCouponContent());
                break;
            case PUBLISH:
                baseViewHolder.setText(R.id.tv_item_text_publish, storeDetail.getPublishContent());
                break;
            default:
        }
    }
}
