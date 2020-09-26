package com.roots.app.mvp.ui.adapter.sort;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.roots.app.R;
import com.roots.app.mvp.model.entity.sort.Sort2Node;

import org.jetbrains.annotations.NotNull;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Sort2Provider.java
 * @Description TODO
 * @createTime 2020年08月29日 13:14:00
 */

public class Sort2Provider extends BaseNodeProvider {

    private int selected = 0;
    private OnClickListener mOnClickListener;

    @Override
    public int getItemViewType() {
        return 2;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_sort_left_2;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @NotNull BaseNode baseNode) {
        Sort2Node entity = (Sort2Node) baseNode;
        TextView tv = helper.getView(R.id.tv_left_2);
        helper.setText(R.id.tv_left_2, entity.getTitle());
        if (entity.isExpanded()) {
            tv.getPaint().setFakeBoldText(true);
            helper.getView(R.id.rl_left_2).setBackgroundColor(getContext().getResources().getColor(R.color.white));
            helper.getView(R.id.iv_select2).setVisibility(View.VISIBLE);
        } else {
            tv.getPaint().setFakeBoldText(false);
            helper.getView(R.id.iv_select2).setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        Sort2Node entity = (Sort2Node) data;
        if (entity.isExpanded()) {
            getAdapter().collapse(position);
        } else {
            getAdapter().expandAndCollapseOther(position);
        }
        if (mOnClickListener != null) {
            mOnClickListener.itemOnClick(position, view, data);
        }
    }

    public interface OnClickListener{
        void itemOnClick(int position, View v, BaseNode data);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }
}
