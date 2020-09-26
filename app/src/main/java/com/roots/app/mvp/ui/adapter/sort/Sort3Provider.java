package com.roots.app.mvp.ui.adapter.sort;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.roots.app.R;

import org.jetbrains.annotations.NotNull;

public class Sort3Provider extends BaseNodeProvider {
    @Override
    public int getItemViewType() {
        return 3;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_sort_left_3;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {

    }
}
