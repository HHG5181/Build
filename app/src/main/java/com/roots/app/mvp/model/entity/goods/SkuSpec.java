package com.roots.app.mvp.model.entity.goods;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SkuSpec implements Serializable {

    private int spec_id;
    private String spec_name;
    private int store_id;
    private List<SpecValue> spec_value;

}
