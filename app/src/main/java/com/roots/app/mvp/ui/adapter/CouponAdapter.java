package com.roots.app.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.roots.app.R;
import com.roots.app.mvp.model.entity.CouponBean;

/**
 * @Classname CouponAdapter
 * @Description TODO
 * @Date 2020/8/25 17:56
 * @Created by bird
 */

public class CouponAdapter extends BaseQuickAdapter<CouponBean, BaseViewHolder> {

    public CouponAdapter() {
        super(R.layout.item_coupon);
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponBean item) {
        initInnerAdapter(helper, item);
    }

    private void initInnerAdapter(BaseViewHolder helper, CouponBean item) {
        helper.setText(R.id.tv_coupon_money, String.format("%s", item.getCouponMoney()));
        helper.setText(R.id.tv_coupon_type, item.getCouponType());
        helper.setText(R.id.tv_coupon_scope, item.getCouponScope());
        helper.setText(R.id.tv_coupon_time, item.getCouponTime());
        helper.setText(R.id.tv_coupon_object, item.getCouponObject());
    }
}

