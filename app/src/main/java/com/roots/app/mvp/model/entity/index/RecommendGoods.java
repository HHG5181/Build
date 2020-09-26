package com.roots.app.mvp.model.entity.index;

import com.roots.app.mvp.model.entity.goods.Cate;
import com.roots.app.mvp.model.entity.goods.GoodsStatus;
import com.roots.app.mvp.model.entity.goods.Sku;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecommendGoods implements Serializable {

    private int goods_id;
    private String goods_name;
    private String selling_point;
    private int goods_cate_id;
    private int spec_type;
    private int sales_initial;
    private int sales_actual;
    private int quota;
    private GoodsStatus goods_status;
    private int store_id;
    private int cate_id;
    private int limit_buy;
    private int rise_buy;
    private List<String> coupon_list;
    private int goods_sales;
    private String goods_image;
    private Cate cate;
}
