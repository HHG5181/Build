package com.roots.app.mvp.model.entity.sort;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : bird
 * @Classname : SortBean
 * @Description : 分类实体类
 * @Date : 2020/8/27 2:56
 */
@Setter
@Getter
public class SortBean implements Serializable {

    private int cate_id;
    private String cate_name;
    private String cover;
    private List<SortBean> child;
}
