package com.roots.app.mvp.model.entity.goods;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpecValue implements Serializable {

    private int spec_value_id;
    private String spec_value;
    private int spec_id;
    private int store_id;

    public SpecValue() {

    }

    public SpecValue(int spec_value_id, String spec_value) {
        this.spec_value_id = spec_value_id;
        this.spec_value = spec_value;
    }
}
