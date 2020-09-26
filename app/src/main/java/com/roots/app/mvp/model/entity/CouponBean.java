package com.roots.app.mvp.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponBean {
    private Integer couponId;
    /**
     * 优惠金额
     */
    private Long couponMoney;
    /**
     * 优惠券类型
     */
    private String couponType;
    /**
     * 优惠对象
     */
    private String couponObject;
    /**
     * 优惠券有效时间
     */
    private String couponTime;
    /**
     * 优惠条件、范围
     */
    private String couponScope;
}
