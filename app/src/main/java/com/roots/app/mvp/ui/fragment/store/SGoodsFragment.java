package com.roots.app.mvp.ui.fragment.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jess.arms.di.component.AppComponent;
import com.lxj.xpopup.XPopup;
import com.roots.app.R;
import com.roots.app.app.base.BaseSupportFragment;
import com.roots.app.di.component.DaggerStoreComponent;
import com.roots.app.di.module.StoreModule;
import com.roots.app.mvp.contract.StoreContract;
import com.roots.app.mvp.model.entity.goods.Goods;
import com.roots.app.mvp.model.entity.goods.GoodsCate;
import com.roots.app.mvp.model.entity.goods.Sku;
import com.roots.app.mvp.model.entity.goods.SkuSpec;
import com.roots.app.mvp.model.entity.store.StoreBean;
import com.roots.app.mvp.presenter.StorePresenter;
import com.roots.app.mvp.ui.activity.goods.GoodsDetailActivity;
import com.roots.app.mvp.ui.adapter.store.StoreSortLeftAdapter;
import com.roots.app.mvp.ui.adapter.store.StoreSortRightAdapter;
import com.roots.app.mvp.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author : bird
 * @Classname : SGoodsFragment
 * @Description : TODO
 * @Date : 2020/8/31 12:45
 */

public class SGoodsFragment extends BaseSupportFragment<StorePresenter> implements StoreContract.View {

    @BindView(R.id.store_sort_left)
    RecyclerView rvLeft;
    @BindView(R.id.store_sort_right)
    RecyclerView rvRight;
    private StoreSortLeftAdapter mLeftAdapter;
    private StoreSortRightAdapter mRightAdapter;

    List<GoodsCate> goodsCates = new ArrayList<>();

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerStoreComponent
                .builder()
                .appComponent(appComponent)
                .storeModule(new StoreModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_s_goods, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initLeft();
        initRight();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        int store_id = getActivity().getIntent().getIntExtra("store_id", 0);
        assert mPresenter != null;
        mPresenter.detail(store_id);
    }

    /**
     * 左边
     */
    public void initLeft() {
        mLeftAdapter = new StoreSortLeftAdapter(new ArrayList<>());
        rvLeft.setLayoutManager(new LinearLayoutManager(_mActivity));
        mLeftAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                mLeftAdapter.setSelectedPosition(position);
                mRightAdapter.setNewInstance(new ArrayList<>());
                List<Goods> goods = ((StoreSortLeftAdapter) adapter).getData().get(position).getGoods();

                mRightAdapter.setList(goods);
            }
        });

        rvLeft.setAdapter(mLeftAdapter);
    }

    public void initRight() {
        mRightAdapter = new StoreSortRightAdapter(new ArrayList<>());
        rvRight.setLayoutManager(new LinearLayoutManager(_mActivity));
        rvRight.setAdapter(mRightAdapter);
        mRightAdapter.setEmptyView(LayoutInflater.from(_mActivity).inflate(R.layout.view_empty, null));
        mRightAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Intent intent = new Intent(_mActivity, GoodsDetailActivity.class);
                startActivity(intent);
            }
        });
        mRightAdapter.addChildClickViewIds(R.id.tv_sku);
        mRightAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_sku:
                        List<SkuSpec> sku_spec = ((StoreSortRightAdapter) adapter).getData().get(position).getSku_spec();
                        List<Sku> skus = ((StoreSortRightAdapter) adapter).getData().get(position).getSku();
                        System.out.println(sku_spec);
                        String goods_name = ((StoreSortRightAdapter) adapter).getData().get(position).getGoods_name();
                        SkuPopup skuPopup = new SkuPopup(mContext, sku_spec, goods_name, skus);
                        skuPopup.setListerner(new SkuPopup.OnListerner() {
                            @Override
                            public void addToCart(int goods_id, int goods_sku_id, int num) {
                                ToastUtils.showCenterToast("添加购物车" + goods_sku_id);
                            }
                        });
                        new XPopup.Builder(mContext)
                                .asCustom(skuPopup)
                                .show();
                        break;
                    default:
                }
            }
        });
    }

    @Override
    public void detail(StoreBean data) {
        goodsCates = data.getGoods_cate();
        mLeftAdapter.setList(goodsCates);
        mLeftAdapter.setSelectedPosition(0);
        mRightAdapter.setList(goodsCates.get(0).getGoods());
    }

    @Override
    public void follow(String msg) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showCenterToast(message);
    }

}
