package com.roots.app.mvp.model.entity.goods;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Sku implements Serializable {

    private int goods_sku_id;
    private int goods_id;
    private String spec_sku_id;
    private String image;
    private String goods_no;
    private String goods_price;
    private String member_price;
    private int stock_num;
    private int goods_sales;
    private int goods_unit_num;
    private String goods_unit;
    private int store_id;
    private String volume_long;
    private String volume_width;
    private String valume_height;
    private String sku_text;
}
