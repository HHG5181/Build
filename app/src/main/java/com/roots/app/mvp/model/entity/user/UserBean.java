package com.roots.app.mvp.model.entity.user;

import com.roots.app.mvp.model.entity.common.City;
import com.roots.app.mvp.model.entity.common.Province;
import com.roots.app.mvp.model.entity.common.Region;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户实体类
 */
@Setter
@Getter
public class UserBean implements Serializable {

    private int user_id;
    private String phone;
    private int vocation;
    private String full_name;
    private List<String> prove_imgs;
    private String create_time;
    private String update_time;
    private int status;
    private ExamineStatus examine_status;
    private Lavel lavel;
    private Province province;
    private City city;
    private Region region;
}
