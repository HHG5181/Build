package com.roots.app.mvp.ui.activity.store;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.jaeger.library.StatusBarUtil;
import com.jess.arms.di.component.AppComponent;
import com.lxj.xpopup.XPopup;
import com.roots.app.R;
import com.roots.app.app.base.BaseSupportActivity;
import com.roots.app.app.base.BaseSupportFragment;
import com.roots.app.di.component.DaggerStoreComponent;
import com.roots.app.di.module.StoreModule;
import com.roots.app.mvp.contract.StoreContract;
import com.roots.app.mvp.model.entity.goods.Goods;
import com.roots.app.mvp.model.entity.store.StoreBean;
import com.roots.app.mvp.model.entity.store.StoreDetail;
import com.roots.app.mvp.presenter.StorePresenter;
import com.roots.app.mvp.ui.adapter.store.StoreCouponAdapter;
import com.roots.app.mvp.ui.adapter.store.StoreDetailAdapter;
import com.roots.app.mvp.ui.fragment.store.SAppraiseFragment;
import com.roots.app.mvp.ui.fragment.store.SDetailFragment;
import com.roots.app.mvp.ui.fragment.store.SGoodsFragment;
import com.roots.app.mvp.ui.fragment.store.CartPopup;
import com.roots.app.mvp.ui.widget.MyStore;
import com.roots.app.mvp.utils.GlideImageUtils;
import com.roots.app.mvp.utils.ToastUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import lombok.SneakyThrows;

public class StoreActivity extends BaseSupportActivity<StorePresenter> implements StoreContract.View {

    @BindView(R.id.stl)
    SlidingTabLayout mSl;
    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.my_store)
    MyStore mMyStore;
    @BindView(R.id.cl_shop_bar)
    ConstraintLayout mClShopBar;

    int store_id = 0;

    @BindView(R.id.iv_follow)
    ImageView isFollow;

    @BindView(R.id.iv_logo)
    ImageView logo;
    @BindView(R.id.store_name)
    TextView storeName;
    @BindView(R.id.tv_notice)
    TextView notice;

    @BindView(R.id.rv_coupon)
    RecyclerView rvCoupon;
    @BindView(R.id.rv_collasped)
    RecyclerView rvCollasped;

    @BindView(R.id.tv_total_price)
    TextView totlaPrice;

    private ArrayList<BaseSupportFragment> mFragments = new ArrayList<>();
    private MyFragmentAdapter mFragmentAdapter;
    private StoreCouponAdapter mStoreCouponAdapter;
    private StoreDetailAdapter mDetailAdapter;

    private final String[] mTitles = {"商品", "评价", "商家"};

    private List<Goods> goods_list;

    private List<StoreDetail> mDetails = new ArrayList<>();

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerStoreComponent
                .builder()
                .appComponent(appComponent)
                .storeModule(new StoreModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_store;
    }

    @SneakyThrows
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        store_id = getIntent().getIntExtra("store_id", 0);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void init() {
        mFragments.add(new SGoodsFragment());
        mFragments.add(new SAppraiseFragment());
        mFragments.add(new SDetailFragment());
        mFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        mDetailAdapter = new StoreDetailAdapter(new ArrayList<>());

        mViewPager.setAdapter(mFragmentAdapter);
        mSl.setViewPager(mViewPager, mTitles);

        mStoreCouponAdapter = new StoreCouponAdapter(new ArrayList<>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvCoupon.setLayoutManager(layoutManager);
        rvCoupon.setAdapter(mStoreCouponAdapter);

        rvCollasped.setLayoutManager(new LinearLayoutManager(mContext));
        rvCollasped.setAdapter(mDetailAdapter);

        mViewPager.setOffscreenPageLimit(mFragments.size());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int visi = i == 0 ? View.VISIBLE : View.GONE;
                mClShopBar.setVisibility(visi);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mMyStore.setProgressUpdateListener(new MyStore.ProgressUpdateListener() {
            @Override
            public void onUpCollapsedContentTransProUpdate(float pro) {

            }

            @Override
            public void onUpAlphaScaleProUpdate(float pro) {

            }

            @Override
            public void onUpAlphaGradientProUpdate(float pro) {
                if (pro > 0.5f) {
                    StatusBarUtil.setLightMode(StoreActivity.this);
                } else {
                    StatusBarUtil.setDarkMode(StoreActivity.this);
                }
            }

            @Override
            public void onDownCollapsedAlphaProUpdate(float pro) {

            }

            @Override
            public void onDownContentAlphaProUpdate(float pro) {

            }

            @Override
            public void onDownShopBarTransProUpdate(float pro) {

            }
        });

        //反射修改最少滑动距离
        try {
            Field mTouchSlop = ViewPager.class.getDeclaredField("mTouchSlop");
            mTouchSlop.setAccessible(true);
            mTouchSlop.setInt(mViewPager,dp2px(50));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        mViewPager.setOffscreenPageLimit(mFragments.size());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int visi = i == 0 ? View.VISIBLE : View.GONE;
                mClShopBar.setVisibility(visi);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        if (mPresenter != null) {
            mPresenter.detail(store_id);
        }

    }

    @OnClick({R.id.tv_coupon_count, R.id.iv_back, R.id.phone_store, R.id.iv_follow, R.id.iv_cart})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_coupon_count:
                mMyStore.expand(MyStore.ANIM_DURATION_FRACTION);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_follow:
                //收藏
                collect();
                break;
            case R.id.phone_store:
                phone();
                break;
            case R.id.iv_cart:
                CartPopup cartPopup = new CartPopup(mContext, goods_list);
                cartPopup.setListerner(new CartPopup.OnListerner() {
                    @Override
                    public void clearCart() {
                        if (mPresenter != null) {
                            mPresenter.clear(store_id);
                            mPresenter.detail(store_id);
                        }
                    }
                });
                new XPopup.Builder(mContext)
                        .asCustom(cartPopup)
                        .show();
                break;
            default:
                break;
        }
    }

    /**
     * 联系商家
     */
    private void phone() {

    }

    /**
     * 收藏店铺
     */
    private void collect() {
        if (mPresenter != null) {
            mPresenter.follow(store_id);
            mPresenter.detail(store_id);
        }
    }

    @Override
    public void detail(StoreBean data) {
        GlideImageUtils.DisplayRoundCorner(this, data.getLogo(), logo, 5);
        storeName.setText(data.getStore_name());
        notice.setText("公告：" + data.getNotice());
        if (data.getIs_follow() == 0) {
            isFollow.setImageResource(R.mipmap.collect);
        } else {
            isFollow.setImageResource(R.mipmap.collected);
        }
        goods_list = data.getCart().getGoods_list();
        if (goods_list.size() > 0) {
            totlaPrice.setText("￥" + data.getCart().getTotal_price());
            totlaPrice.setTextColor(Color.WHITE);
        }

        mDetails.add(new StoreDetail(StoreDetail.FIRST_TITLE, "优惠"));
        mDetails.add(new StoreDetail("会员", "超级会员领7元无门槛红包"));
        mDetails.add(new StoreDetail(StoreDetail.TITLE, "公告"));
        mDetails.add(new StoreDetail(data.getNotice()));
        mDetailAdapter.setList(mDetails);

    }

    @Override
    public void follow(String msg) {
        ToastUtils.showCenterToast(msg);
    }


    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showCenterToast(message);
    }

    @Override
    public void post(Runnable runnable) {

    }

    public int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal,getResources().getDisplayMetrics());
    }

    private class MyFragmentAdapter extends FragmentPagerAdapter {

        MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

}
