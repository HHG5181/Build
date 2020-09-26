package com.roots.app.mvp.model.entity.goods;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GoodsCate implements Serializable {

    private int goods_cate_id;
    private String goods_cate_name;
    private int store_id;
    private int cart_count;
    private List<Goods> goods;
}
