package com.roots.app.mvp.ui.activity.self;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.di.component.AppComponent;
import com.roots.app.R;
import com.roots.app.app.base.BaseSupportActivity;
import com.roots.app.di.component.DaggerCouponComponent;
import com.roots.app.di.module.CouponModule;
import com.roots.app.mvp.contract.CouponContract;
import com.roots.app.mvp.model.entity.CouponBean;
import com.roots.app.mvp.presenter.CouponPresenter;
import com.roots.app.mvp.ui.adapter.CouponAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * @Classname Coupon
 * @Description TODO
 * @Date 2020/8/25 17:55
 * @Created by bird
 */

public class CouponActivity extends BaseSupportActivity<CouponPresenter> implements CouponContract.View {

    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.toolbar_back)
    RelativeLayout back;
    @BindView(R.id.rv_coupon)
    RecyclerView rvCoupon;

    private CouponAdapter mAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCouponComponent.builder()
                .appComponent(appComponent)
                .couponModule(new CouponModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_coupon;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        title.setText("优惠券");
        back.setVisibility(View.VISIBLE);
        initCoupon();
    }



    private void initCoupon() {
        rvCoupon.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new CouponAdapter();
        rvCoupon.setAdapter(mAdapter);
        mAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.view_empty, null));
        if (mPresenter != null) {
            mPresenter.list();
        }
    }

    @Override
    public void couponList(List<CouponBean> data) {
        mAdapter.setNewData(data);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }


    @Override
    public void post(Runnable runnable) {

    }
}
