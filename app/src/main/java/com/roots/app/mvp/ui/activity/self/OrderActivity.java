package com.roots.app.mvp.ui.activity.self;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jess.arms.di.component.AppComponent;
import com.roots.app.R;
import com.roots.app.app.base.BaseSupportActivity;
import com.roots.app.app.base.BaseSupportFragment;
import com.roots.app.di.component.DaggerOrderComponent;
import com.roots.app.di.module.OrderModule;
import com.roots.app.mvp.contract.OrderContract;
import com.roots.app.mvp.presenter.OrderPresenter;
import com.roots.app.mvp.ui.adapter.BottomPagerAdapter;
import com.roots.app.mvp.ui.fragment.self.OrderFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class OrderActivity extends BaseSupportActivity<OrderPresenter> implements OrderContract.View {

    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.ctl_order)
    CommonTabLayout ctlTab;
    @BindView(R.id.vp_order)
    ViewPager vpOrder;

    private String[] mTitles = {"全部", "待付款", "待使用", "待评价", "退款/售后"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<BaseSupportFragment> mFragments = new ArrayList<>();
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerOrderComponent.builder()
                .appComponent(appComponent)
                .orderModule(new OrderModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_order;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        title.setText("订单");
        toolbarBack.setVisibility(View.VISIBLE);
        initTab();
        initListenter();
    }

    public void initTab() {
        mFragments.add(OrderFragment.newInstance(1));
        mFragments.add(OrderFragment.newInstance(2));
        mFragments.add(OrderFragment.newInstance(3));
        mFragments.add(OrderFragment.newInstance(4));
        mFragments.add(OrderFragment.newInstance(5));

        for (int i = 0; i < mTitles.length; i++) {
            final int fi = i;
            mTabEntities.add(new CustomTabEntity() {
                @Override
                public String getTabTitle() {
                    return mTitles[fi];
                }

                @Override
                public int getTabSelectedIcon() {
                    return 0;
                }

                @Override
                public int getTabUnselectedIcon() {
                    return 0;
                }
            });
        }

        ctlTab.setTabData(mTabEntities);
        vpOrder.setOffscreenPageLimit(mFragments.size());
        vpOrder.setAdapter(new BottomPagerAdapter(getSupportFragmentManager(), mFragments));
    }

    private void initListenter() {
        Bundle bundle = new Bundle();
        ctlTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpOrder.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        vpOrder.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ctlTab.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        int args = mContext.getIntent().getIntExtra("args", 1);
        vpOrder.setCurrentItem(args);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void post(Runnable runnable) {

    }
}
