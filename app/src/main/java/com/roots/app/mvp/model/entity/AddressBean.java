package com.roots.app.mvp.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressBean {

    /**
     * 地址Id
     */
    long id;

    /**
     * 会员Id
     */
    public Long memberId;

    /**
     * 收货人
     */
    private String username;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 是否默认
     */
    private Integer isDefault;

    /**
     * 地区
     */
    public Long areaId;

    /**
     * 地区名称
     */
    public String areaName;
}

