package com.roots.app.mvp.ui.adapter.store;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.roots.app.R;
import com.roots.app.mvp.model.entity.goods.Goods;
import com.roots.app.mvp.utils.GlideImageUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author : bird
 * @Classname : StoreSortRightAdapter
 * @Description : TODO
 * @Date : 2020/9/1 1:29
 */

public class StoreSortRightAdapter extends BaseQuickAdapter<Goods, BaseViewHolder> {

    public StoreSortRightAdapter(@Nullable List<Goods> data) {
        super(R.layout.item_store_sort_right, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Goods data) {
        GlideImageUtils.DisplayRoundCorner(baseViewHolder.getView(R.id.iv_goods_logo).getContext(), data.getGoods_image(), baseViewHolder.getView(R.id.iv_goods_logo), 5);
        baseViewHolder.setText(R.id.tv_goods_name, data.getGoods_name());
        baseViewHolder.setText(R.id.selling_point, data.getSelling_point());
        baseViewHolder.setText(R.id.tv_goods_sales, "售量" + data.getGoods_sales());
        baseViewHolder.setText(R.id.tv_goods_price, "￥" + data.getGoods_sku().getGoods_price());
        baseViewHolder.setText(R.id.tv_member_price, "会员价：￥" + data.getGoods_sku().getMember_price());
        if (data.getSku_spec().size() == 0) {
            baseViewHolder.setText(R.id.tv_sku, "+");
        } else {
            baseViewHolder.setText(R.id.tv_sku, "选规格");
        }
    }
}
