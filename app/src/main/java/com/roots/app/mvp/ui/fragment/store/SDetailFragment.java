package com.roots.app.mvp.ui.fragment.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.PhoneUtils;
import com.jess.arms.di.component.AppComponent;
import com.roots.app.R;
import com.roots.app.app.base.BaseSupportFragment;
import com.roots.app.di.component.DaggerStoreComponent;
import com.roots.app.di.module.StoreModule;
import com.roots.app.mvp.contract.StoreContract;
import com.roots.app.mvp.model.entity.store.StoreBean;
import com.roots.app.mvp.presenter.StorePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : bird
 * @Classname : DetailFragment
 * @Description : TODO
 * @Date : 2020/8/31 12:46
 */

public class SDetailFragment extends BaseSupportFragment<StorePresenter> implements StoreContract.View {

    @BindView(R.id.tv_located)
    TextView address;

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
        return inflater.inflate(R.layout.fragment_s_detail, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        assert mPresenter != null;
        int store_id = getActivity().getIntent().getIntExtra("store_id", 0);
        assert mPresenter != null;
        mPresenter.detail(store_id);
    }

    @OnClick({R.id.iv_phone})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_phone:
                phone();
                break;
            default:
                break;
        }
    }

    /**
     * 联系商家
     */
    private void phone() {
        new MaterialDialog.Builder(mContext)
                .title("联系商家")
                .content("商家电话：15626285181")
                .positiveText("确认")
                .negativeText("取消")
                .btnSelector(R.color.color_green, DialogAction.NEGATIVE)
                .btnSelector(R.color.color_green, DialogAction.POSITIVE)
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            PhoneUtils.dial("15626285181");
                        } else if (which == DialogAction.NEGATIVE) {

                        }
                    }
                })
                .show();
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void detail(StoreBean data) {
        address.setText(data.getAddress());
    }

    @Override
    public void follow(String msg) {

    }

    @Override
    public void showMessage(@NonNull String message) {
    }
}
