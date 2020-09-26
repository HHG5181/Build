package com.roots.app.mvp.ui.adapter.store;

public interface SKUInterface {

    /**
     * 选中属性
     * @param attr
     */
    void selectedAttribute(String[] attr);


    /**
     * 取消属性选择
     * @param attr
     */
    void uncheckAttribute(String[] attr);

}