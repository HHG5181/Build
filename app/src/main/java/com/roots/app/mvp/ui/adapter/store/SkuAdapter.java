package com.roots.app.mvp.ui.adapter.store;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.collection.SimpleArrayMap;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.roots.app.R;
import com.roots.app.mvp.model.entity.goods.Sku;
import com.roots.app.mvp.model.entity.goods.SkuSpec;
import com.roots.app.mvp.model.entity.goods.SpecValue;
import com.roots.app.mvp.ui.widget.SkuViewGroup;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class SkuAdapter extends BaseQuickAdapter<SkuSpec, BaseViewHolder> {

    private SKUInterface mInterface;
    private SimpleArrayMap<Integer, String> saveClick;

    private List<Sku> mSkus;//商品数据集合
    private String[] selectedValue;   //选中的属性
    private TextView[][] childrenViews;    //二维 装所有属性

    private final int SELECTED = 0x100;
    private final int CANCEL = 0x101;

    public SkuAdapter(@Nullable List<SkuSpec> data, @Nullable List<Sku> skus) {
        super(R.layout.item_sku, data);
        this.mSkus = skus;
        saveClick = new SimpleArrayMap<>();
        childrenViews = new TextView[data.size()][0];
        selectedValue = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            selectedValue[i] = "";
        }
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, SkuSpec skuSpec) {
        baseViewHolder.setText(R.id.tv_spec_name, skuSpec.getSpec_name());
        SkuViewGroup skuItem = (SkuViewGroup) baseViewHolder.getView(R.id.svg_sku);
        List<SpecValue> children = skuSpec.getSpec_value();
        TextView[] tvs = new TextView[children.size()];
        for (int i = 0; i < children.size(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(15, 5, 15, 0);
            TextView textView = new TextView(getContext());
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(15, 5, 15, 5);
            textView.setLayoutParams(params);
            textView.setText(children.get(i).getSpec_value());
            textView.setTextColor(Color.BLACK);
            tvs[i] = textView;
            skuItem.addView(tvs[i]);
        }
        childrenViews[baseViewHolder.getAdapterPosition()] = tvs;
        initOptions();
        canClickOptions();
        getSelected();
    }

    private int focusPositionG, focusPositionC;

    /**
     * 初始化选项（不可点击，焦点消失）
     */
    private void initOptions() {
        for (int y = 0; y < childrenViews.length; y++) {
            for (int z = 0; z < childrenViews[y].length; z++) {//循环所有属性
                TextView textView = childrenViews[y][z];
                textView.setEnabled(false);
                textView.setFocusable(false);
                textView.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));//变灰
            }
        }
    }

    /**
     * 获取符合选项并设置可选
     */
    private void canClickOptions() {
        for (int i = 0; i < childrenViews.length; i++) {
            for (int j = 0; j < mSkus.size(); j++) {
                boolean filter = false;
                List<String> list = Arrays.asList(mSkus.get(j).getSku_text().split(";"));
                for (int k = 0; k < selectedValue.length; k++) {
                    if (i == k || TextUtils.isEmpty(selectedValue[k])) {
                        continue;
                    }
                    if (!selectedValue[k].equals(list
                            .get(k))) {
                        filter = true;
                        break;
                    }
                }
                if (!filter) {
                    for (int n = 0; n < childrenViews[i].length; n++) {
                        TextView textView = childrenViews[i][n];//拿到所有属性TextView
                        String name = textView.getText().toString();
                        //拿到属性名称
                        if (list.get(i).equals(name)) {
                            textView.setEnabled(true);//符合就变成可点击
//                            textView.setFocusable(true); //设置可以获取焦点
//                            //不要让焦点乱跑
//                            if (focusPositionG == i && focusPositionC == n) {
//                                textView.setTextColor(Color.GREEN);
//                                textView.requestFocus();
//                            } else {
//                                textView.setTextColor(Color.BLACK);
//                            }
                            textView.setTextColor(Color.BLACK);
                            textView.setBackgroundResource(R.drawable.btn_white);
                            textView.setPadding(ConvertUtils.dp2px(10), ConvertUtils.dp2px(5), ConvertUtils.dp2px(10), ConvertUtils.dp2px(5));
                            textView.setOnClickListener(new MyOnClickListener(SELECTED, i, n) {});
                            textView.setOnFocusChangeListener(new MyOnFocusChangeListener(i, n) {});
                        }
                    }
                }
            }
        }
    }

    private class MyOnClickListener implements View.OnClickListener {
        //点击操作 选中SELECTED   取消CANCEL
        private int operation;

        private int positionG;

        private int positionC;

        public MyOnClickListener(int operation, int positionG, int positionC) {
            this.operation = operation;
            this.positionG = positionG;
            this.positionC = positionC;
        }

        @Override
        public void onClick(View v) {
            focusPositionG = positionG;
            focusPositionC = positionC;
            String value = childrenViews[positionG][positionC].getText().toString();
            switch (operation) {
                case SELECTED:
                    saveClick.put(positionG, positionC + "");
                    selectedValue[positionG] = value;
                    mInterface.selectedAttribute(selectedValue);
                    break;
                case CANCEL:
                    saveClick.put(positionG, "");
                    for (int l = 0; l < selectedValue.length; l++) {
                        if (selectedValue[l].equals(value)) {
                            selectedValue[l] = "";
                            break;
                        }
                    }
                    mInterface.uncheckAttribute(selectedValue);
                    break;
                default:
            }
            initOptions();
            canClickOptions();
            getSelected();
        }
    }

    class MyOnFocusChangeListener implements View.OnFocusChangeListener {

        private int positionG;

        private int positionC;


        public MyOnFocusChangeListener(int positionG, int positionC) {
            this.positionG = positionG;
            this.positionC = positionC;
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            String clickpositionC = saveClick.get(positionG);
            if (hasFocus) {
                v.setBackgroundColor(Color.YELLOW);
                if (TextUtils.isEmpty(clickpositionC)) {
                    ((TextView) v).setTextColor(Color.BLUE);
                } else if (clickpositionC.equals(positionC + "")) {

                } else {
                    ((TextView) v).setTextColor(Color.BLUE);
                }
            } else {
                v.setBackgroundColor(Color.GREEN);
                if (TextUtils.isEmpty(clickpositionC)) {
                    ((TextView) v).setTextColor(Color.BLUE);
                } else if (clickpositionC.equals(positionC + "")) {

                } else {
                    ((TextView) v).setTextColor(Color.BLUE);
                }
            }
        }

    }

    public void setInterface(SKUInterface anInterface) {
        mInterface = anInterface;
    }

    /**
     * 找到已经选中的选项，让其变红
     */
    private void getSelected() {
        for (int i = 0; i < childrenViews.length; i++) {
            for (int j = 0; j < childrenViews[i].length; j++) {//拿到每行属性Item
                TextView textView = childrenViews[i][j];//拿到所有属性TextView
                String value = textView.getText().toString();
                for (int m = 0; m < selectedValue.length; m++) {
                    if (selectedValue[m].equals(value)) {
                        textView.setTextColor(Color.GREEN);
                        textView.setBackgroundResource(R.drawable.bg_sku_item);
                        textView.setPadding(ConvertUtils.dp2px(10), ConvertUtils.dp2px(5), ConvertUtils.dp2px(10), ConvertUtils.dp2px(5));
//                        textView.setOnClickListener(new MyOnClickListener(CANCEL, i, j) {});
                    }
                }
            }
        }
    }


}
