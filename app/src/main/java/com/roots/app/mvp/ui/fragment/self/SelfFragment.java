package com.roots.app.mvp.ui.fragment.self;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.jess.arms.di.component.AppComponent;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.roots.app.R;
import com.roots.app.app.base.BaseSupportFragment;
import com.roots.app.di.component.DaggerSelfComponent;
import com.roots.app.di.module.SelfModule;
import com.roots.app.mvp.contract.SelfContract;
import com.roots.app.mvp.presenter.SelfPresenter;
import com.roots.app.mvp.ui.activity.self.AddressActivity;
import com.roots.app.mvp.ui.activity.self.CouponActivity;
import com.roots.app.mvp.ui.activity.self.OrderActivity;
import com.roots.app.mvp.utils.ToastUtils;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class SelfFragment extends BaseSupportFragment<SelfPresenter> implements SelfContract.View, TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.abl_self)
    AppBarLayout barLayout;
    @BindView(R.id.rl_self)
    RelativeLayout selfBar;
    @BindView(R.id.tv_name)
    TextView username;
    @BindView(R.id.civ_header)
    CircleImageView civHeader;

    // 滑动到什么地方完全变色
    private int height;
    //滑动的距离总和
    private int ScrollUnm = 0;

    private InvokeParam mInvokeParam;
    private TakePhoto mTakePhoto;
    private String navUri;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerSelfComponent.builder()
                .appComponent(appComponent)
                .selfModule(new SelfModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_self, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initStyle();
    }

    @OnClick({R.id.civ_header, R.id.ll_my_order, R.id.rl_order_unpay,R.id.rl_order_send,R.id.rl_order_evaluate, R.id.rl_order_after, R.id.rl_coupon, R.id.rl_address})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.civ_header:
                getTakePhoto().onPickFromGallery();
                break;
            case R.id.ll_my_order:
                Intent intent0 = new Intent(_mActivity, OrderActivity.class);
                intent0.putExtra("args", 0);
                startActivity(intent0);
                break;
            case R.id.rl_order_unpay:
                Intent intent1 = new Intent(_mActivity,OrderActivity.class);
                intent1.putExtra("args", 1);
                startActivity(intent1);
                break;
            case R.id.rl_order_send:
                Intent intent2 = new Intent(_mActivity,OrderActivity.class);
                intent2.putExtra("args", 2);
                startActivity(intent2);
                break;
            case R.id.rl_order_evaluate:
                Intent intent3 = new Intent(_mActivity,OrderActivity.class);
                intent3.putExtra("args", 3);
                startActivity(intent3);
                break;
            case R.id.rl_order_after:
                Intent intent4 = new Intent(_mActivity,OrderActivity.class);
                intent4.putExtra("args", 4);
                startActivity(intent4);
                break;
            case R.id.rl_coupon:
                startActivity(new Intent(mContext, CouponActivity.class));
                break;
            case R.id.rl_address:
                startActivity(new Intent(mContext, AddressActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 初始化样式
     */
    public void initStyle() {
        height = UIUtil.dip2px(mContext,200-45);
        barLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                ScrollUnm = -verticalOffset; //滑动距离总合
                if (ScrollUnm<=0){  //在顶部时完全透明
                    selfBar.setBackgroundColor(Color.argb((int) 0, 255,41,76));
                }else if (ScrollUnm>0&&ScrollUnm<= height){  //在滑动高度中时，设置透明度百分比（当前高度/总高度）
                    double d = (double) ScrollUnm / height;
                    double alpha = (d*255);
                    selfBar.setBackgroundColor(Color.argb((int) alpha, 84,255,159));
                }else{ //滑出总高度 完全不透明
                    selfBar.setBackgroundColor(Color.argb((int) 255, 84,255,159));
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

    /**
     * 更换头像
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void takeSuccess(TResult result) {
        if (mPresenter != null) {
            mPresenter.updateUserImage(result.getImage().getOriginalPath());
        }
        String path = result.getImage().getOriginalPath();
        Glide.with(this).load(path).into(civHeader);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        //设置失败
    }

    @Override
    public void takeCancel() {

    }

    public TakePhoto getTakePhoto() {
        if (mTakePhoto==null){
            mTakePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        //设置压缩规则，最大500kb
        mTakePhoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(500 * 1024).create(), true);
        return mTakePhoto;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(_mActivity,type,mInvokeParam,this);
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.mInvokeParam=invokeParam;
        }
        return type;
    }

    @Override
    public void updateUserImageSuccess() {

    }
}
