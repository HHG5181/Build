package com.roots.app.mvp.ui.adapter.store;

import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.roots.app.R;
import com.roots.app.mvp.model.entity.goods.GoodsCate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author : bird
 * @Classname : StoreSortLeftAdapter
 * @Description : TODO
 * @Date : 2020/8/31 19:53
 */

public class StoreSortLeftAdapter extends BaseQuickAdapter<GoodsCate, BaseViewHolder> {


    private int selectedPosition = 0;

    public StoreSortLeftAdapter(@Nullable List<GoodsCate> data) {
        super(R.layout.item_store_sort_left, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, GoodsCate goodsCate) {
        TextView tv = baseViewHolder.getView(R.id.tv_sort_name);
        baseViewHolder.setText(R.id.tv_sort_name, goodsCate.getGoods_cate_name());
        if (baseViewHolder.getAdapterPosition() == selectedPosition) {
            tv.getPaint().setFakeBoldText(true);
            tv.setTextColor(Color.GREEN);
            tv.setBackgroundColor(Color.WHITE);
        } else {
            tv.getPaint().setFakeBoldText(false);
            tv.setTextColor(Color.BLACK);
        }
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }
}

