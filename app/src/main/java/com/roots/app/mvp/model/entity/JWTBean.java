package com.roots.app.mvp.model.entity;

import com.roots.app.mvp.model.entity.user.UserBean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : bird
 * @Classname : JWTBean
 * @Description : TODO
 * @Date : 2020/8/25 21:38
 */
@Getter
@Setter
public class JWTBean {

    private UserBean user;
    private String token;
}
