package com.roots.app.mvp.ui.adapter.index;

import com.blankj.utilcode.util.ColorUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.roots.app.R;
import com.roots.app.mvp.model.entity.index.MenuBean;
import com.roots.app.mvp.utils.GlideImageUtils;

/**
 * @Classname IndexMenuAdapter
 * @Description 首页导航栏适配器
 * @Date 2020/8/23 15:14
 * @Created by bird
 */
public class IndexMenuAdapter extends BaseQuickAdapter<MenuBean, BaseViewHolder> {

    public IndexMenuAdapter() {
        super(R.layout.item_index);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuBean item) {
        helper.setText(R.id.tv_item_menu, item.getText());
        helper.setTextColor(R.id.tv_item_menu, ColorUtils.string2Int(item.getColor()));
        GlideImageUtils.display(helper.getView(R.id.iv_item_menu).getContext(), item.getImgUrl(), helper.getView(R.id.iv_item_menu));

    }
}