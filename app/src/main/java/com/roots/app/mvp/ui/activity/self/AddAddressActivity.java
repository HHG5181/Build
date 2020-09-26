package com.roots.app.mvp.ui.activity.self;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.RegexUtils;
import com.jess.arms.di.component.AppComponent;
import com.lljjcoder.Interface.OnCustomCityPickerItemClickListener;
import com.lljjcoder.bean.CustomCityData;
import com.lljjcoder.citywheel.CustomConfig;
import com.lljjcoder.style.citycustome.CustomCityPicker;
import com.roots.app.R;
import com.roots.app.app.base.BaseSupportActivity;
import com.roots.app.di.component.DaggerAddressComponent;
import com.roots.app.di.module.AddressModule;
import com.roots.app.mvp.contract.AddressContract;
import com.roots.app.mvp.model.entity.AddressBean;
import com.roots.app.mvp.presenter.AddressPresenter;
import com.roots.app.mvp.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddAddressActivity extends BaseSupportActivity<AddressPresenter> implements AddressContract.View {

    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.tv_select_area)
    TextView tvSelectArea;
    @BindView(R.id.et_username)
    EditText etUserName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.is_default)
    CheckBox isDefault;

    private CustomCityPicker mPicker = null;
    private CustomConfig config = null;

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
        return R.layout.activity_add_address;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        title.setText("新建地址");
        toolbarBack.setVisibility(View.VISIBLE);
        mPicker = new CustomCityPicker(this);
    }

    @OnClick({R.id.btn_save, R.id.tv_select_area})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                save();
                break;
            case R.id.tv_select_area:
                showPickerView();
                break;
            default:
                break;
        }
    }

    /**
     * 保存地址
     */
    public void save() {
        String username = etUserName.getText().toString();
        String phone = etPhone.getText().toString();
        String address = tvSelectArea.getText().toString() + etAddress.getText().toString();

        if (username.length() == 0) {
            ToastUtils.showCenterToast("收货人不能为空");
            return;
        }

        if (TextUtils.isEmpty(phone) || !RegexUtils.isMobileExact(phone)) {
            ToastUtils.showCenterToast("请输入正确的手机号码");
            return;
        }
        if (tvSelectArea.getText().toString().length() == 0 || etAddress.getText().toString().length() == 0) {
            ToastUtils.showCenterToast("地址不能为空");
            return;
        }
        if (mPresenter != null) {
            mPresenter.addAddress(username, phone, address, isDefault.isChecked() ? 1: 0);
        }
    }

    /**
     * 弹出地址选择器
     */
    public void showPickerView() {
        if (mPresenter != null) {
            mPresenter.cityData();
        }
        mPicker.setCustomConfig(config);
        mPicker.setOnCustomCityPickerItemClickListener(new OnCustomCityPickerItemClickListener() {
            @Override
            public void onSelected(CustomCityData province, CustomCityData city, CustomCityData district) {
                tvSelectArea.setText(province.getName() + city.getName() + district.getName());
            }
        });
    }


    @Override
    public void cityData(List<CustomCityData> data) {
         config = new CustomConfig.Builder()
                .title("地区选择")
                .visibleItemsCount(5)
                .setCityData(data)
                .provinceCyclic(false)
                .cityCyclic(false)
                .districtCyclic(false)
                .build();
    }

    @Override
    public void addressList(List<AddressBean> data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void post(Runnable runnable) {

    }
}
