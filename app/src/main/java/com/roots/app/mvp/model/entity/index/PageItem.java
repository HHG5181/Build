package com.roots.app.mvp.model.entity.index;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : bird
 * @Description : TODO
 * @Date : 2020/8/28 16:47
 */
@Setter
@Getter
public class PageItem implements Serializable {
    private List<BannerBean> banner;
    private List<MenuBean> navBar;
    private List<WindowBean> window;
    private List<SingleBean> imageSingle;
}
