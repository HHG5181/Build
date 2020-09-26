package com.roots.app.mvp.ui.fragment.index;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.material.appbar.AppBarLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.roots.app.R;
import com.roots.app.app.base.BaseSupportFragment;
import com.roots.app.di.component.DaggerIndexComponent;
import com.roots.app.di.module.IndexModule;
import com.roots.app.mvp.contract.IndexContract;
import com.roots.app.mvp.model.entity.index.BannerBean;
import com.roots.app.mvp.model.entity.index.IndexBean;
import com.roots.app.mvp.model.entity.index.MenuBean;
import com.roots.app.mvp.model.entity.index.SingleBean;
import com.roots.app.mvp.model.entity.index.WindowBean;
import com.roots.app.mvp.model.entity.store.StoreBean;
import com.roots.app.mvp.presenter.IndexPresenter;
import com.roots.app.mvp.ui.adapter.index.ImageBannerAdapter;
import com.roots.app.mvp.ui.adapter.index.IndexMenuAdapter;
import com.roots.app.mvp.ui.adapter.NotConflictViewPager;
import com.roots.app.mvp.utils.AntiShakeUtils;
import com.roots.app.mvp.utils.GlideImageUtils;
import com.roots.app.mvp.utils.LocationUtils;
import com.roots.app.mvp.utils.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class IndexFragment extends BaseSupportFragment<IndexPresenter> implements IndexContract.View {

    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.banner_index)
    Banner banner;
    @BindView(R.id.ll_index_bar)
    LinearLayout llIndexBar;
    @BindView(R.id.mi_index)
    MagicIndicator indicator;
    @BindView(R.id.vp_nav)
    NotConflictViewPager mPager;
    @BindView(R.id.nsv)
    NestedScrollView nsv;
    @BindView(R.id.tv_located)
    TextView tvLocated;
    @BindView(R.id.rv_nav)
    RecyclerView rvNav;
    @BindView(R.id.home_ads1)
    ImageView ads1;
    @BindView(R.id.home_ads2)
    ImageView ads2;
    @BindView(R.id.home_ads3)
    ImageView ads3;
    @BindView(R.id.home_ads4)
    ImageView ads4;
    @BindView(R.id.iv_single)
    ImageView imgSingle;

    private List<String> mDataList = Arrays.asList("附近商家", "发现好物");
    private List<BaseSupportFragment> mFragments;

    List<BannerBean> mBannerBeans = new ArrayList<>();
    ImageBannerAdapter mImageBannerAdapter = new ImageBannerAdapter(mBannerBeans);

    IndexMenuAdapter mItemAdapter = null;

    private List<String> menu = new ArrayList<>();

    /**
     * 滑动到什么地方完全变色
     */
    private int height;
    /**
     * 滑动的距离总和
     */
    private int ScrollUnm = 0;

    /**
     * 热门城市
     */
    private List<HotCity> mHotCities;

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
        return inflater.inflate(R.layout.fragment_index, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.titleBar(llIndexBar).statusBarDarkFont(false).init();
        //滑动冲突
        nsv.setFillViewport(true);
        initLocated();
        initBanner();
        initNavigator();
        initListener();

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        assert mPresenter != null;
        mPresenter.index();
    }

    /**
     * 动态添加导航栏
     */
    private void initItem(List<MenuBean> menu) {
        rvNav.setLayoutManager(new GridLayoutManager(mContext, 5));
        mItemAdapter = new IndexMenuAdapter();
        rvNav.setAdapter(mItemAdapter);
        mItemAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                ToastUtils.showCenterToast("点击" + position);
            }
        });
        mItemAdapter.setNewData(menu);
    }

    @OnClick({R.id.ll_located, R.id.ll_search})
    public void onClicked(View view) {
        if (AntiShakeUtils.isInvalidClick(view)) {
            return;
        }
        switch (view.getId()) {
            case R.id.ll_located:
                located();
                break;
            case R.id.ll_search:
                break;
            default:
                break;
        }
    }

    /**
     * 初始化Banner
     */
    public void initBanner() {
        banner.setAdapter(mImageBannerAdapter)
                .addBannerLifecycleObserver(this)
                .setIndicator(new CircleIndicator(mContext));
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                ToastUtils.showCenterToast("点击" + position);
            }
        });
    }

    /**
     * 初始化Toolbar
     */
    public void initListener() {
        mFragments = new ArrayList<>();
        mFragments.add(new NearbyFragment());
        mFragments.add(new ReommendFragment());
        mPager.setAdapter(new Myadapter(getChildFragmentManager()));
        //Toolbar颜色渐变
        height = UIUtil.dip2px(mContext,200-45);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                ScrollUnm = -verticalOffset; //滑动距离总合
                if (ScrollUnm<=0){  //在顶部时完全透明
                    llIndexBar.setBackgroundColor(Color.argb((int) 0, 0,255,0));
                }else if (ScrollUnm>0&&ScrollUnm<= height){  //在滑动高度中时，设置透明度百分比（当前高度/总高度）
                    double d = (double) ScrollUnm / height;
                    double alpha = (d*255);
                    llIndexBar.setBackgroundColor(Color.argb((int) alpha, 84,255,159));
                }else{ //滑出总高度 完全不透明
                    llIndexBar.setBackgroundColor(Color.argb((int) 255, 84,255,159));
                }
                if (verticalOffset == 0) {
                    //展开
                    //swipeRefresh.setEnabled(true);
//                    mImmersionBar.statusBarDarkFont(false,0.2f).init();
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
//                // 闭合
//                    mImmersionBar.statusBarDarkFont(true,0.2f).init();
                } else {
                    //swipeRefresh.setEnabled(false);
                }
            }
        });
    }

    /**
     * 初始化
     */
    public void initNavigator() {
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        //是否充满屏幕
        commonNavigator.setAdjustMode(false);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                ClipPagerTitleView titleView = new ClipPagerTitleView(context);
                titleView.setText(mDataList.get(index));
                titleView.setTextColor(Color.parseColor("#424242"));
                titleView.setClipColor(Color.parseColor("#FF294C"));
                titleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPager.setCurrentItem(index);
                    }
                });
                return titleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT); //下划线的长度和字体相同
                indicator.setColors(Color.parseColor("#FF294C"));//下划线颜色
                return indicator;
            }
        });
        indicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(indicator, mPager);
    }

    /**
     * 初始化定位
     */
    private void initLocated() {
        LocationUtils.start(_mActivity, new LocationUtils.onLocationListener() {
            @Override
            public void getData(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    tvLocated.setText(aMapLocation.getCity());
                }
            }
        });
    }

    /**
     * 定位
     */
    private void located() {
        LocationUtils.start(_mActivity, new LocationUtils.onLocationListener() {
            @Override
            public void getData(AMapLocation aMapLocation) {
                mHotCities = new ArrayList<>();
                mHotCities.add(new HotCity("广州", "广东", "101280101"));
                CityPicker.from(_mActivity)
                        .enableAnimation(true)
                        .setAnimationStyle(R.style.CustomAnim)
                        .setLocatedCity(null)
                        .setHotCities(mHotCities)
                        .setOnPickListener(new OnPickListener() {
                            @Override
                            public void onPick(int position, City data) {
                                tvLocated.setText(String.format("%s", data.getName()));
                            }

                            @Override
                            public void onLocate() {
                                //开始定位，这里模拟一下定位
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        CityPicker.from(_mActivity).locateComplete(new LocatedCity(aMapLocation.getCity(), aMapLocation.getProvince(), aMapLocation.getCityCode()), LocateState.SUCCESS);
                                    }
                                }, 1000);
                            }

                            @Override
                            public void onCancel() {
                                ToastUtils.showCenterToast("取消选择");
                            }
                        }).show();
            }
        });
    }

    /**
     * 初始化数据
     * @param data
     */
    @Override
    public void index(IndexBean data) {
        mBannerBeans = data.getPage().getBanner();
        banner.setDatas(mBannerBeans);

        //导航栏
        initItem(data.getPage().getNavBar());

        //图片橱窗
        List<WindowBean> windows = data.getPage().getWindow();
        GlideImageUtils.DisplayRoundCorner(mContext, windows.get(0).getImgUrl(), ads1, 5);
        GlideImageUtils.DisplayRoundCorner(mContext, windows.get(1).getImgUrl(), ads2, 5);
        GlideImageUtils.DisplayRoundCorner(mContext, windows.get(2).getImgUrl(), ads3, 5);
        GlideImageUtils.DisplayRoundCorner(mContext, windows.get(3).getImgUrl(), ads4, 5);

        //商城
        List<SingleBean> imageSingle = data.getPage().getImageSingle();
        GlideImageUtils.DisplayRoundCorner(mContext, imageSingle.get(0).getImgUrl(), imgSingle, 5);

    }

    @Override
    public void getNearBy(List<StoreBean> data) {

    }

    class Myadapter extends FragmentPagerAdapter {

        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }


    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showCenterToast(message);
    }


    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void post(Runnable runnable) {

    }
}
