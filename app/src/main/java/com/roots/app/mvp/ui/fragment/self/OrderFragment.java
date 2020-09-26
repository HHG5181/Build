package com.roots.app.mvp.ui.fragment.self;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.di.component.AppComponent;
import com.roots.app.R;
import com.roots.app.app.base.BaseSupportFragment;
import com.roots.app.mvp.contract.OrderContract;
import com.roots.app.mvp.presenter.OrderPresenter;
import com.roots.app.mvp.utils.AppConstants;

import butterknife.BindView;

public class OrderFragment extends BaseSupportFragment<OrderPresenter> implements OrderContract.View {

    @BindView(R.id.rv_order)
    RecyclerView rvOrder;

    public static OrderFragment newInstance(int status) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putInt(AppConstants.INTENT_KEY1, status);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }


    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void post(Runnable runnable) {

    }
}
