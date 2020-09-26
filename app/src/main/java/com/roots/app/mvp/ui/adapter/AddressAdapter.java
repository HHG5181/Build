package com.roots.app.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.roots.app.R;
import com.roots.app.mvp.model.entity.AddressBean;

public class AddressAdapter extends BaseQuickAdapter<AddressBean, BaseViewHolder> {
    public AddressAdapter() {
        super(R.layout.item_address);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean item) {
        helper.setText(R.id.tv_username, item.getUsername());
        helper.setText(R.id.tv_phone, item.getPhone());
        helper.setText(R.id.tv_address, item.getAddress());
    }
}
