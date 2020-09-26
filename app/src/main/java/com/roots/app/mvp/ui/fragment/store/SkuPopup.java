package com.roots.app.mvp.ui.fragment.store;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lxj.xpopup.core.CenterPopupView;
import com.roots.app.R;
import com.roots.app.mvp.model.entity.goods.Sku;
import com.roots.app.mvp.model.entity.goods.SkuSpec;
import com.roots.app.mvp.ui.adapter.store.SKUInterface;
import com.roots.app.mvp.ui.adapter.store.SkuAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SkuPopup extends CenterPopupView implements SKUInterface {

    @BindView(R.id.rv_sku_spec)
    RecyclerView rvSku;
    @BindView(R.id.tv_goods_name)
    TextView goodsName;
    @BindView(R.id.tv_selected)
    TextView skuName;
    @BindView(R.id.tv_goods_price)
    TextView tvPrice;
    @BindView(R.id.tv_add_cart)
    TextView addCart;

    private SkuAdapter mSkuSpecAdapter;

    private List<SkuSpec> datas;
    private List<Sku> mSkuList;

    private String goods_name;

    private OnListerner mListerner = null;

    int goods_id;
    int goods_sku_id;
    int num;

    public SkuPopup(@NonNull Context context, List<SkuSpec> datas, String goods_name, List<Sku> skuList) {
        super(context);
        this.datas = datas;
        this.goods_name = goods_name;
        this.mSkuList = skuList;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_sku;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        initView();
    }


    public void initView() {
        goodsName.setText(goods_name);
        rvSku.setLayoutManager(new LinearLayoutManager(getContext()));
        mSkuSpecAdapter = new SkuAdapter(datas, mSkuList);
        rvSku.setAdapter(mSkuSpecAdapter);
        rvSku.setFocusable(false);
        mSkuSpecAdapter.setInterface(this);
        mSkuSpecAdapter.setList(datas);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void selectedAttribute(String[] attr) {
        String str = "";
        String ss = "";
        for (int i = 0; i < attr.length; i++) {
            str += " " + datas.get(i).getSpec_name() + "：";
            ss = TextUtils.isEmpty(attr[i]) ? "无" : attr[i];
            str += ss + " ";
        }
        skuName.setText("已选规格：" + str);
        for (Sku sku : mSkuList) {
            String[] s = sku.getSku_text().split(";");
            if (Arrays.equals(attr, s)) {
                tvPrice.setText("￥" + sku.getGoods_price());
                addCart.setVisibility(VISIBLE);
                goods_id = sku.getGoods_id();
                goods_sku_id = sku.getGoods_sku_id();
            }
        }
    }

    @OnClick(R.id.tv_add_cart)
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_add_cart:
                if (mListerner != null) {
                    mListerner.addToCart(goods_id, goods_sku_id, 1);
                }
                break;
            default:
        }
    }

    @Override
    public void uncheckAttribute(String[] attr) {
        String str = "";
        String ss = "";
        for (int i = 0; i < attr.length; i++) {
            str += " " + datas.get(i).getSpec_name() + "：";
            ss = TextUtils.isEmpty(attr[i]) ? "无" : attr[i];
            str += ss + " ";
        }
        skuName.setText("已选规格：" + str);
    }

    public void setListerner(OnListerner listerner) {
        mListerner = listerner;
    }

    public interface OnListerner{
        void addToCart(int goods_id, int goods_sku_id, int num );
    }
}
