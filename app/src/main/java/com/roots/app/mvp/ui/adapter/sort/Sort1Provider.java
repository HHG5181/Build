package com.roots.app.mvp.ui.adapter.sort;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.roots.app.R;
import com.roots.app.mvp.model.entity.sort.Sort1Node;

import org.jetbrains.annotations.NotNull;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SortProvider.java
 * @Description TODO
 * @createTime 2020年08月29日 13:10:00
 */
public class Sort1Provider extends BaseNodeProvider {


    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_sort_left;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {
        Sort1Node entity = (Sort1Node) baseNode;
        TextView tv = baseViewHolder.getView(R.id.tv_left);
        baseViewHolder.setText(R.id.tv_left, entity.getTitle());
        if (entity.isExpanded()) {
            tv.getPaint().setFakeBoldText(true);
            baseViewHolder.getView(R.id.rl_left).setBackgroundColor(getContext().getResources().getColor(R.color.white));
        } else {
            tv.getPaint().setFakeBoldText(false);
            baseViewHolder.getView(R.id.rl_left).setBackgroundColor(getContext().getResources().getColor(R.color.divider_color));
        }
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        Sort1Node sort1Node = (Sort1Node) data;
        if (sort1Node.isExpanded()) {
            getAdapter().collapse(position);
        } else {
            getAdapter().expandAndCollapseOther(position);
        }
    }

}
