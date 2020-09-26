package com.roots.app.mvp.model.entity.goods;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Goods implements Serializable {

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
    private List<Counpon> coupon_list;
    private int goods_sales;
    private String goods_image;
    private Sku goods_sku;
    private List<SkuSpec> sku_spec;

    private List<Rel> rel;
    private List<Sku> sku;
    private GoodsCate goods_cate;
    private String goods_price;
    private int total_num;
    private String total_price;
//    private Cate cate;
}
