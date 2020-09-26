package com.roots.app.mvp.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ConvertUtils;
import com.roots.app.R;

@SuppressLint("AppCompatCustomView")
public class SkuItemView extends TextView {
    private String attributeValue;

    public SkuItemView(Context context) {
        super(context);
        init(context);
    }

    public SkuItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SkuItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setBackgroundResource(R.drawable.sku_item_selector);
        setTextColor(getResources().getColorStateList(R.color.sku_item_text_selector));
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        setSingleLine();
        setGravity(Gravity.CENTER);
        setPadding(ConvertUtils.dp2px(10), 0, ConvertUtils.dp2px(10), 0);
        setMinWidth(ConvertUtils.dp2px(45));
        setMaxWidth(ConvertUtils.dp2px(200));
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
        setText(attributeValue);
    }

}
