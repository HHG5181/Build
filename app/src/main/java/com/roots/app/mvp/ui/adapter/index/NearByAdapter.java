package com.roots.app.mvp.ui.adapter.index;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.roots.app.R;
import com.roots.app.mvp.model.entity.store.StoreBean;
import com.roots.app.mvp.utils.GlideImageUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName NearByAdapter.java
 * @Description TODO
 * @createTime 2020年08月29日 18:24:00
 */

public class NearByAdapter extends BaseQuickAdapter<StoreBean, BaseViewHolder> implements LoadMoreModule {

    public NearByAdapter(@Nullable List<StoreBean> data) {
        super(R.layout.item_nearby, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, StoreBean storeBean) {
        GlideImageUtils.DisplayRoundCorner(baseViewHolder.getView(R.id.iv_store_logo).getContext(), storeBean.getLogo(), baseViewHolder.getView(R.id.iv_store_logo), 5);
        baseViewHolder.setText(R.id.store_name, storeBean.getStore_name());
        baseViewHolder.setText(R.id.tv_distance, String.valueOf(storeBean.getDistance()) + storeBean.getDistance_unit());
    }
}
