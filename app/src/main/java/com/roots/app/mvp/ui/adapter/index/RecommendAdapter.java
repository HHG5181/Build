package com.roots.app.mvp.ui.adapter.index;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.roots.app.R;
import com.roots.app.mvp.model.entity.goods.Goods;
import com.roots.app.mvp.utils.GlideImageUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecommendAdapter extends BaseQuickAdapter<Goods, BaseViewHolder> implements LoadMoreModule {

    public RecommendAdapter(List<Goods> data) {
        super(R.layout.item_recommend, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Goods goods) {
        baseViewHolder.setText(R.id.tv_goods_name, goods.getGoods_name());
        GlideImageUtils.DisplayRoundCorner(baseViewHolder.getView(R.id.iv_goods).getContext(), goods.getGoods_image(), baseViewHolder.getView(R.id.iv_goods), 5);
        baseViewHolder.setText(R.id.tv_goods_price, "￥" + goods.getGoods_sku().getGoods_price());
        baseViewHolder.setText(R.id.tv_goods_sales, "月售量" + goods.getGoods_sku().getGoods_sales());
        baseViewHolder.setText(R.id.tv_member_price, "￥" + goods.getGoods_sku().getMember_price());
    }
}
