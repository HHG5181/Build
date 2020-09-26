package com.roots.app.mvp.ui.adapter.store;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.roots.app.R;
import com.roots.app.mvp.model.entity.goods.Goods;
import com.roots.app.mvp.utils.GlideImageUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CartAdapter extends BaseQuickAdapter<Goods, BaseViewHolder> {


    public CartAdapter(@Nullable List<Goods> data) {
        super(R.layout.item_popup_cart, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Goods goods) {
        GlideImageUtils.DisplayRoundCorner(baseViewHolder.getView(R.id.iv_cart).getContext(), goods.getGoods_sku().getImage(), baseViewHolder.getView(R.id.iv_cart), 5);
        baseViewHolder.setText(R.id.tv_goods_name, goods.getGoods_name());
        baseViewHolder.setText(R.id.tv_sku_text, goods.getGoods_sku().getSku_text());
        baseViewHolder.setText(R.id.tv_goods_price, "￥" + goods.getTotal_price());
    }
}
