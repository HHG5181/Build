package com.roots.app.mvp.model.entity.goods;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GoodsStatus implements Serializable {
    private String text;
    private int vale;
}
