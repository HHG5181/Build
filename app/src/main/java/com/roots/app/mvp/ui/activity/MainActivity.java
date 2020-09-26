package com.roots.app.mvp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.roots.app.R;
import com.roots.app.app.base.BaseSupportActivity;
import com.roots.app.app.base.BaseSupportFragment;
import com.roots.app.di.component.DaggerMainComponent;
import com.roots.app.mvp.contract.MainContract;
import com.roots.app.mvp.presenter.MainPresenter;
import com.roots.app.mvp.ui.adapter.BottomPagerAdapter;
import com.roots.app.mvp.ui.fragment.FindFragment;
import com.roots.app.mvp.ui.fragment.MsgFragment;
import com.roots.app.mvp.ui.fragment.SortFragment;
import com.roots.app.mvp.ui.fragment.index.IndexFragment;
import com.roots.app.mvp.ui.fragment.self.SelfFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import butterknife.BindView;
import timber.log.Timber;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/22/2020 23:54
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MainActivity extends BaseSupportActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.vp_main)
    ViewPager mPager;
    @BindView(R.id.ctl_tab)
    CommonTabLayout mTabLayout;

    private ArrayList<BaseSupportFragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"首页", "分类", "发现", "消息", "个人"};
    private int[] tabIconGray = new int[]{R.mipmap.index, R.mipmap.sort, R.mipmap.find, R.mipmap.message, R.mipmap.self};
    private int[] tabIcon = new int[]{R.mipmap.indexs, R.mipmap.sorts, R.mipmap.finds, R.mipmap.msgs, R.mipmap.selfs};

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initTab();
        initListenter();
        requestPermissions();
    }

    /**
     * 初始化底部导航栏
     */
    public void initTab() {
        mFragments.add(new IndexFragment());
        mFragments.add(new SortFragment());
        mFragments.add(new FindFragment());
        mFragments.add(new MsgFragment());
        mFragments.add(new SelfFragment());
        for (int i = 0; i < mTitles.length; i++) {
            final int fi = i;
            mTabEntities.add(new CustomTabEntity() {

                @Override
                public String getTabTitle() {
                    return mTitles[fi];
                }

                @Override
                public int getTabSelectedIcon() {
                    return tabIcon[fi];
                }

                @Override
                public int getTabUnselectedIcon() {
                    return tabIconGray[fi];
                }
            });
        }

        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setMsgMargin(0, -6, 5);
        mPager.setAdapter(new BottomPagerAdapter(getSupportFragmentManager(), mFragments));
    }

    @SuppressLint("CheckResult")
    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(MainActivity.this);
        rxPermission
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(permission -> {
                    if (permission.granted) {
                        // 用户已经同意该权限
                        Timber.e("%s is granted.", permission.name);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        Timber.d("%s is denied. More info should be provided.", permission.name);
                    } else {
                        // 用户拒绝了该权限，并且选中『不再询问』
                        Timber.e("%s is denied.", permission.name);
                    }
                });
    }


    /**
     * 初始化监听
     */
    private void initListenter() {
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

//        滑动监听
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mPager.setCurrentItem(0);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void post(Runnable runnable) {

    }
}
