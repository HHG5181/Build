package com.roots.app.mvp.ui.fragment.index;

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
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.jess.arms.di.component.AppComponent;
import com.roots.app.R;
import com.roots.app.app.base.BaseSupportFragment;
import com.roots.app.di.component.DaggerIndexComponent;
import com.roots.app.di.module.IndexModule;
import com.roots.app.mvp.contract.IndexContract;
import com.roots.app.mvp.model.entity.index.IndexBean;
import com.roots.app.mvp.model.entity.store.StoreBean;
import com.roots.app.mvp.presenter.IndexPresenter;
import com.roots.app.mvp.ui.activity.store.StoreActivity;
import com.roots.app.mvp.ui.adapter.index.NearByAdapter;
import com.roots.app.mvp.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Classname NearbyFragment
 * @Description TODO
 * @Date 2020/8/23 15:24
 * @Created by bird
 */

public class NearbyFragment extends BaseSupportFragment<IndexPresenter> implements IndexContract.View {

    @BindView(R.id.rv_nearby)
    RecyclerView rvNearBy;

    private NearByAdapter mNearByAdapter = new NearByAdapter(new ArrayList<>());

    private PageInfo mPage = new PageInfo();

    private List<StoreBean> datas;

    private static final int PAGE_SIZE = 5;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerIndexComponent
                .builder()
                .appComponent(appComponent)
                .indexModule(new IndexModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nearby, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        init();
        initLoadMore();
    }



    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        assert mPresenter != null;
        mPresenter.getNearBy();
    }

    public void init() {
        rvNearBy.setLayoutManager(new LinearLayoutManager(mContext));
        rvNearBy.setAdapter(mNearByAdapter);

    }

    @Override
    public void getNearBy(List<StoreBean> data) {
        datas = data;
        mNearByAdapter.setList(data);
        mNearByAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Intent intent = new Intent(_mActivity, StoreActivity.class);
                intent.putExtra("store_id", data.get(position).getStore_id());
                startActivity(intent);
            }
        });
    }

    /**
     * 上拉加载更多
     */
    private void initLoadMore() {
        mNearByAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMore();
            }
        });
        mNearByAdapter.getLoadMoreModule().setAutoLoadMore(true);
        mNearByAdapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(false);
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        mNearByAdapter.getLoadMoreModule().setEnableLoadMore(true);
        if (mPage.isFirstPage()) {
            mNearByAdapter.setList(datas);
        } else {
            mNearByAdapter.addData(datas);
        }
        if (datas.size() < PAGE_SIZE) {
            mNearByAdapter.getLoadMoreModule().loadMoreEnd();
            ToastUtils.showCenterToast("到底啦");
        } else {
            mNearByAdapter.getLoadMoreModule().loadMoreComplete();
        }
        mPage.nextPage();
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void index(IndexBean data) {

    }


    @Override
    public void showMessage(@NonNull String message) {

    }

    public class PageInfo {

        int page = 0;

        void nextPage() {
            page++;
        }

        void reset() {
            page = 0;
        }

        boolean isFirstPage() {
            return page == 0;
        }
    }

}

