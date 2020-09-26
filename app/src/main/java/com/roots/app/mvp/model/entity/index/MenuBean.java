package com.roots.app.mvp.model.entity.index;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : bird
 * @Description : TODO
 * @Date : 2020/8/28 16:43
 */
@Setter
@Getter
public class MenuBean implements Serializable {

    private String imgUrl;
    private String text;
    private String color;
    private UrlBean url;
}
