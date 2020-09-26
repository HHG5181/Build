package com.roots.app.mvp.model.entity.index;

import com.roots.app.mvp.model.entity.goods.Goods;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : bird
 * @Description : TODO
 * @Date : 2020/8/28 13:46
 */
@Setter
@Getter
public class IndexBean implements Serializable {

    private PageItem page;

    private List<Goods> recommend_goods;

}
