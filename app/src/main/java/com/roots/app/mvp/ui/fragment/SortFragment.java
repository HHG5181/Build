package com.roots.app.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jess.arms.di.component.AppComponent;
import com.roots.app.R;
import com.roots.app.app.base.BaseSupportFragment;
import com.roots.app.di.component.DaggerSortComponent;
import com.roots.app.di.module.SortModule;
import com.roots.app.mvp.contract.SortContract;
import com.roots.app.mvp.model.entity.sort.Sort1Node;
import com.roots.app.mvp.model.entity.sort.Sort2Node;
import com.roots.app.mvp.model.entity.sort.Sort3Node;
import com.roots.app.mvp.model.entity.sort.SortBean;
import com.roots.app.mvp.presenter.SortPresenter;
import com.roots.app.mvp.ui.adapter.sort.Sort2Provider;
import com.roots.app.mvp.ui.adapter.sort.SortRightAdapter;
import com.roots.app.mvp.ui.adapter.sort.SortTreeAdapter;
import com.roots.app.mvp.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SortFragment extends BaseSupportFragment<SortPresenter> implements SortContract.View {

    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.rv_left)
    RecyclerView rvLeft;
    @BindView(R.id.rv_right)
    RecyclerView rvRight;
    @BindView(R.id.toolbar_back)
    RelativeLayout rl_back;

    private SortTreeAdapter mSortTreeAdapter = new SortTreeAdapter();
    private SortRightAdapter mRightAdapter = null;
    Sort2Provider mSort2Provider = new Sort2Provider();

    List<SortBean> rightList = new ArrayList<>();

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerSortComponent
                .builder()
                .appComponent(appComponent)
                .sortModule(new SortModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sort, container, false);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        assert mPresenter != null;
        mPresenter.getList();
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        title.setText("分类");
        rl_back.setVisibility(View.INVISIBLE);
        initRight();
        initLeft();
    }

    private void initLeft() {
        rvLeft.setLayoutManager(new LinearLayoutManager(mContext));
        mSort2Provider.setOnClickListener(new Sort2Provider.OnClickListener() {
            @Override
            public void itemOnClick(int position, View v, BaseNode data) {
                Sort2Node sort2Node = (Sort2Node) data;
                if (rightList !=null ) {
                    for (SortBean bean : rightList) {
                        if (bean.getCate_id() == sort2Node.getId()) {
                            mRightAdapter.setList(bean.getChild());
                        }
                    }
                }
            }
        });
        mSortTreeAdapter.addNodeProvider(mSort2Provider);
        rvLeft.setAdapter(mSortTreeAdapter);
        mSortTreeAdapter.setEmptyView(LayoutInflater.from(_mActivity).inflate(R.layout.view_empty, null));
    }

    /**
     * 初始化右边adapter
     */
    public void initRight() {
        mRightAdapter = new SortRightAdapter(new ArrayList<>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rvRight.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mRightAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                ToastUtils.showCenterToast("点击" + position);
            }
        });
        rvRight.setAdapter(mRightAdapter);
    }

    @Override
    public void getList(List<SortBean> data) {
        List<BaseNode> list = new ArrayList<>();
        int i = 0;
        for (SortBean sort : data) {
            List<BaseNode> secondList = new ArrayList<>();
            for (SortBean second : sort.getChild()) {
                List<BaseNode> thirdList = new ArrayList<>();
                Sort3Node sort3Node = new Sort3Node();
                thirdList.add(sort3Node);
                Sort2Node sort2Node = new Sort2Node(thirdList, second.getCate_name(), second.getCate_id());
                secondList.add(sort2Node);
                rightList.add(second);
            }
            Sort1Node sortNode = new Sort1Node(secondList, sort.getCate_name(), sort.getCate_id());
            sortNode.setExpanded(i == 0 );
            list.add(sortNode);
            i++;
        }
        mSortTreeAdapter.setList(list);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }


}
