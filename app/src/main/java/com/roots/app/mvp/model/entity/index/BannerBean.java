package com.roots.app.mvp.model.entity.index;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : bird
 * @Description : TODO
 * @Date : 2020/8/28 19:06
 */
@Setter
@Getter
public class BannerBean implements Serializable {

    private String imgUrl;
    private UrlBean url;
}
