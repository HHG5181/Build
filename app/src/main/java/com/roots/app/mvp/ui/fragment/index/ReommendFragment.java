package com.roots.app.mvp.ui.fragment.index;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.roots.app.mvp.model.entity.goods.Goods;
import com.roots.app.mvp.model.entity.index.IndexBean;
import com.roots.app.mvp.model.entity.store.StoreBean;
import com.roots.app.mvp.presenter.IndexPresenter;
import com.roots.app.mvp.ui.activity.store.StoreActivity;
import com.roots.app.mvp.ui.adapter.index.RecommendAdapter;
import com.roots.app.mvp.ui.widget.SpaceItemDecoration;
import com.roots.app.mvp.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Classname FindGoodsFragment
 * @Description TODO
 * @Date 2020/8/23 15:25
 * @Created by bird
 */

public class ReommendFragment extends BaseSupportFragment<IndexPresenter> implements IndexContract.View {

    @BindView(R.id.rv_recommends)
    RecyclerView mRecommendsView;

    RecommendAdapter mAdapter = null;

    private PageInfo mPageInfo = new PageInfo();

    private static final int PAGE_SIZE = 5;

    List<Goods> goods = null;

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
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initRecommends();
        initLoadMore();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        assert mPresenter != null;
        mPresenter.index();
    }

    private void initRecommends() {
        mAdapter = new RecommendAdapter(new ArrayList<>());
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2);
        mRecommendsView.setLayoutManager(layoutManager);
        mRecommendsView.addItemDecoration(new SpaceItemDecoration(10));
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                ToastUtils.showCenterToast(position + "");
//                return 2;
//            }
//        });
        mRecommendsView.setAdapter(mAdapter);

    }

    @Override
    public void index(IndexBean data) {
        goods = data.getRecommend_goods();
        mAdapter.setList(goods);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Intent intent = new Intent(_mActivity, StoreActivity.class);
                intent.putExtra("store_id", goods.get(position).getStore_id());
                startActivity(intent);
            }
        });
    }

    /**
     * 上拉加载更多
     */
    private void initLoadMore() {
        mAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMore();
            }
        });
        mAdapter.getLoadMoreModule().setAutoLoadMore(true);
        mAdapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(false);
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        mAdapter.getLoadMoreModule().setEnableLoadMore(true);
        if (mPageInfo.isFirstPage()) {
            mAdapter.setList(goods);
        } else {
            mAdapter.addData(goods);
        }
        if (goods.size() < PAGE_SIZE) {
            mAdapter.getLoadMoreModule().loadMoreEnd();
            ToastUtils.showCenterToast("到底啦");
        } else {
            mAdapter.getLoadMoreModule().loadMoreComplete();
        }
        mPageInfo.nextPage();
    }

    @Override
    public void getNearBy(List<StoreBean> data) {

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

