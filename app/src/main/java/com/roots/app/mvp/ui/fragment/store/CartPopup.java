package com.roots.app.mvp.ui.fragment.store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lxj.xpopup.core.BottomPopupView;
import com.roots.app.R;
import com.roots.app.mvp.model.entity.goods.Goods;
import com.roots.app.mvp.ui.adapter.store.CartAdapter;
import com.roots.app.mvp.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartPopup extends BottomPopupView {
    @BindView(R.id.rv_cart)
    RecyclerView rvCart;
    private CartAdapter mCartAdapter;
    private List<Goods> data;

    private OnListerner mListerner;

    public CartPopup(@NonNull Context context, List<Goods> data) {
        super(context);
        this.data = data;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_cart;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mCartAdapter = new CartAdapter(new ArrayList<>());
        rvCart.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCart.setAdapter(mCartAdapter);
        mCartAdapter.setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.view_empty, null));
        mCartAdapter.setList(data);
    }

    @OnClick(R.id.tv_clear_cart)
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_clear_cart:
                ToastUtils.showCenterToast("清除购物车");
                if (mListerner != null) {
                    mListerner.clearCart();
                }
                break;
            default:
        }
    }

    public void setListerner(OnListerner listerner) {
        mListerner = listerner;
    }

    public interface OnListerner{
        void clearCart();
    }
}
