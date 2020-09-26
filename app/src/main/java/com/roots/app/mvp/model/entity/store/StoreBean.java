package com.roots.app.mvp.model.entity.store;

import com.roots.app.mvp.model.entity.goods.GoodsCate;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName StoreBean.java
 * @Description TODO
 * @createTime 2020年08月29日 18:11:00
 */
@Setter
@Getter
public class StoreBean implements Serializable {

    private int store_id;
    private String store_name;
    private String logo;
    private String latitude;
    private String longitude;
    private String address;
    private String distance;
    private String distance_unit;
    private String notice;
    private List<SalesBean> sales_list;
    private List<GoodsCate> goods_cate;
    private int is_follow;
    private Cart cart;
}
