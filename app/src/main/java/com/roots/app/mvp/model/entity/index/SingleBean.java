package com.roots.app.mvp.model.entity.index;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : bird
 * @Description : TODO
 * @Date : 2020/8/29 0:37
 */
@Setter
@Getter
public class SingleBean implements Serializable {

    private String imgUrl;
    private UrlBean url;
}
