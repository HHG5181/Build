package com.roots.app.mvp.model.entity.store;

import com.roots.app.mvp.model.entity.goods.Goods;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 购物车实体类
 */
@Getter
@Setter
public class Cart implements Serializable {

    private List<Goods> goods_list;

    private String total_price;

}
