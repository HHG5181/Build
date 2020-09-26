package com.roots.app.mvp.ui.widget;

import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.loadmore.BaseLoadMoreView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.roots.app.R;

import org.jetbrains.annotations.NotNull;

/**
 * @author : bird
 * @Classname : CustomLoadMoreView
 * @Description : TODO
 * @Date : 2020/8/31 17:34
 */

public final class CustomLoadMoreView extends BaseLoadMoreView {
    @NotNull
    @Override
    public View getLoadComplete(@NotNull BaseViewHolder baseViewHolder) {
        return baseViewHolder.findView(R.id.load_more_load_complete_view);
    }

    @NotNull
    @Override
    public View getLoadEndView(@NotNull BaseViewHolder baseViewHolder) {
        return baseViewHolder.findView(R.id.load_more_load_end_view);
    }

    @NotNull
    @Override
    public View getLoadFailView(@NotNull BaseViewHolder baseViewHolder) {
        return null;
    }

    @NotNull
    @Override
    public View getLoadingView(@NotNull BaseViewHolder baseViewHolder) {
        return null;
    }

    @NotNull
    @Override
    public View getRootView(@NotNull ViewGroup viewGroup) {
        return null;
    }
}
