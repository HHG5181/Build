package com.roots.app.mvp.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.RegexUtils;
import com.jess.arms.di.component.AppComponent;
import com.roots.app.R;
import com.roots.app.app.base.BaseSupportActivity;
import com.roots.app.di.component.DaggerLoginComponent;
import com.roots.app.di.module.LoginModule;
import com.roots.app.mvp.contract.LoginContract;
import com.roots.app.mvp.model.entity.JWTBean;
import com.roots.app.mvp.presenter.LoginPresenter;
import com.roots.app.mvp.utils.SendSmsTimerUtils;
import com.roots.app.mvp.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : bird
 * @Classname : ForgotActivity
 * @Description : TODO
 * @Date : 2020/8/26 10:08
 */

public class ForgotActivity extends BaseSupportActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_comfirm)
    EditText etComfirm;
    @BindView(R.id.et_code)
    EditText etCode;

    @BindView(R.id.btn_code)
    TextView btnCode;

    SendSmsTimerUtils mSmsTimerUtils;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_forgot;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mSmsTimerUtils = new SendSmsTimerUtils(btnCode, 60000, 1000, 0, 0);
    }

    @OnClick({R.id.btn_code, R.id.btn_retrieve})
    public void onCLicked(View view) {
        switch (view.getId()) {
            case R.id.btn_code:
                sendSms();
                break;
            case R.id.btn_retrieve:
                retrieve();
                break;
            default:
                break;
        }
    }

    /**
     * 发送验证码ui
     */
    public void sendSms() {
        String phone = etPhone.getText().toString();
        if (isPhone(phone)) {
            if (mPresenter != null) {
                mPresenter.sendSms(phone);
                mSmsTimerUtils.start();
            }
        }
    }

    /**
     * 找回密码
     */
    public void retrieve() {
        String password = etPassword.getText().toString();
        String phone = etPhone.getText().toString();
        String comfirm = etComfirm.getText().toString();
        String code = etCode.getText().toString();
        if (!password.equals(comfirm)) {
            ToastUtils.showCenterToast("两次输入密码不一样");
        }
        if (password.equals(comfirm) && isPass(password) && isPhone(phone) && isCode(code)) {
            if (mPresenter != null) {
                mPresenter.retrieve(phone, code, password);
                startActivity(new Intent(this, LoginActivity.class));
            }
        }
    }

    /**
     * 验证手机号码
     * @param phone
     * @return
     */
    private boolean isPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showCenterToast("手机号码不能为空");
            return false;
        }
        if (!TextUtils.isEmpty(phone) && !RegexUtils.isMobileExact(phone)) {
            ToastUtils.showCenterToast("请输入正确的手机号码");
            return false;
        }
        return true;
    }

    /**
     * 判断密码长度
     */
    private boolean isPass(String password) {
        if (password.length() < 6) {
            ToastUtils.showCenterToast("密码长度错误！");
            return false;
        }
        return true;
    }

    /**
     * 判断验证码
     * @param password
     * @return
     */
    private boolean isCode(String password) {
        if (password.length() < 4) {
            ToastUtils.showCenterToast("验证码长度错误！");
            return false;
        }
        return true;
    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void loginResult(JWTBean data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}

