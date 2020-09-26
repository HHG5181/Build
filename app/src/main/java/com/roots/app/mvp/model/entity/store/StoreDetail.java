package com.roots.app.mvp.model.entity.store;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StoreDetail implements MultiItemEntity {

    public static final int FIRST_TITLE = 0;
    public static final int TITLE = 1;
    public static final int COUPON = 2;
    public static final int PUBLISH=3;

    private int type;

    private String title;
    private String couponTitle;
    private String couponContent;
    private String publishContent;



    public StoreDetail(int type) {
        this.type = type;
    }

    public StoreDetail(String publishContent) {
        this.type = PUBLISH;
        this.publishContent = publishContent;
    }

    public StoreDetail(int type, String title) {
        this.type = type;
        this.title = title;
    }
    
    public StoreDetail(String couponTitle, String couponContent) {
        this.type = COUPON;
        this.couponTitle = couponTitle;
        this.couponContent = couponContent;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
