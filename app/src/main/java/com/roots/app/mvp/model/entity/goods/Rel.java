package com.roots.app.mvp.model.entity.goods;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Rel implements Serializable {

    private int spec_rel_id;
    private int goods_id;
    private int spec_id;
    private int spec_value_id;
    private int store_id;
    private SkuSpec spec;
    private List<Sku> sku;
}
