package com.roots.app.mvp.ui.activity.self;

import android.content.Intent;
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
import com.lljjcoder.bean.CustomCityData;
import com.roots.app.R;
import com.roots.app.app.base.BaseSupportActivity;
import com.roots.app.di.component.DaggerAddressComponent;
import com.roots.app.di.module.AddressModule;
import com.roots.app.mvp.contract.AddressContract;
import com.roots.app.mvp.model.entity.AddressBean;
import com.roots.app.mvp.presenter.AddressPresenter;
import com.roots.app.mvp.ui.adapter.AddressAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressActivity extends BaseSupportActivity<AddressPresenter> implements AddressContract.View {

    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.rv_address)
    RecyclerView rvAddress;

    private AddressAdapter addressAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerAddressComponent.builder()
                .appComponent(appComponent)
                .addressModule(new AddressModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_address;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        title.setText("地址");
        toolbarBack.setVisibility(View.VISIBLE);
        initAddress();
    }

    public void initAddress() {
        rvAddress.setLayoutManager(new LinearLayoutManager(mContext));
        addressAdapter = new AddressAdapter();
        rvAddress.setAdapter(addressAdapter);
        addressAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.view_empty, null));
        if (mPresenter != null) {
            mPresenter.list();
        }
    }

    @OnClick(R.id.btn_add_address)
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add_address:
                startActivity(new Intent(mContext, AddAddressActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void cityData(List<CustomCityData> data) {

    }

    @Override
    public void addressList(List<AddressBean> data) {
        addressAdapter.setNewData(data);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void post(Runnable runnable) {

    }
}
