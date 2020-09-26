package com.roots.app.mvp.ui.adapter.sort;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.roots.app.R;
import com.roots.app.mvp.model.entity.sort.SortBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SortRightAdapter.java
 * @Description TODO
 * @createTime 2020年08月29日 14:18:00
 */

public class SortRightAdapter extends BaseQuickAdapter<SortBean, BaseViewHolder> {

    public SortRightAdapter(@Nullable List<SortBean> data) {
        super(R.layout.item_sort_right_nav, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, SortBean sortBean) {
        baseViewHolder.setText(R.id.tv_right_nav, sortBean.getCate_name());
    }
}
