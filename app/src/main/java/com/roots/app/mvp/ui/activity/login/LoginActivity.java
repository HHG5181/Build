package com.roots.app.mvp.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
import com.roots.app.mvp.ui.activity.MainActivity;
import com.roots.app.mvp.utils.AntiShakeUtils;
import com.roots.app.mvp.utils.AppConstants;
import com.roots.app.mvp.utils.SendSmsTimerUtils;
import com.roots.app.mvp.utils.SpUtils;
import com.roots.app.mvp.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Classname LoginActivity
 * @Description TODO
 * @Date 2020/8/25 20:45
 * @Created by bird
 */

public class LoginActivity extends BaseSupportActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.tv_login_by_code)
    TextView tvByCode;
    @BindView(R.id.tv_login_by_password)
    TextView tvByPassword;
    @BindView(R.id.rl_code)
    RelativeLayout rlCode;
    @BindView(R.id.rl_password)
    RelativeLayout rlPassword;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_send_sms)
    TextView btnCode;


    String login_type = "pwd";

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
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        rlCode.setVisibility(View.INVISIBLE);
        tvByPassword.setVisibility(View.INVISIBLE);
    }

    @OnClick({R.id.btn_login, R.id.tv_login_by_code, R.id.tv_login_by_password, R.id.btn_send_sms, R.id.btn_forgot, R.id.btn_register})
    public void onClicked(View view) {
        if (AntiShakeUtils.isInvalidClick(view)) {
            return;
        }
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_login_by_code:
                loginByCode();
                break;
            case R.id.tv_login_by_password:
                loginByPass();
                break;
            case R.id.btn_send_sms:
                sendSms();
                break;
            case R.id.btn_forgot:
                startActivity(new Intent(mContext, ForgotActivity.class));
                break;
            case R.id.btn_register:
                startActivity(new Intent(mContext, RegisterTypeActivity.class));
                break;
            default:
                break;
        }
    }

    public void login() {
        String phone = etPhone.getText().toString();
        String password = etPassword.getText().toString();
        String code = etCode.getText().toString();
        if (isPhone(phone)) {
            if (login_type.equals("code")) {
                if (mPresenter != null && isCode(code)) {
                    mPresenter.login(login_type, phone, password, code);
                }
            }
            if (login_type.equals("pwd")) {
                if (mPresenter != null && isPass(password)) {
                    mPresenter.login(login_type, phone, password, code);
                }
            }
        }
    }

    @Override
    public void loginResult(JWTBean data) {
        SpUtils.put(mContext, AppConstants.Api.TOKEN, data.getToken());
        startActivity(new Intent(mContext, MainActivity.class));
        finish();
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
     * 验证码登陆ui
     */
    public void loginByCode() {
        rlPassword.setVisibility(View.INVISIBLE);
        rlCode.setVisibility(View.VISIBLE);
        tvByCode.setVisibility(View.INVISIBLE);
        tvByPassword.setVisibility(View.VISIBLE);
        login_type = "code";
        mSmsTimerUtils = new SendSmsTimerUtils(btnCode, 60000, 1000, 0, 0);
    }

    /**
     * 密码登陆ui
     */
    public void loginByPass() {
        rlPassword.setVisibility(View.VISIBLE);
        rlCode.setVisibility(View.INVISIBLE);
        tvByCode.setVisibility(View.VISIBLE);
        tvByPassword.setVisibility(View.INVISIBLE);
        login_type = "pwd";
    }

    /**
     * 验证手机号码
     * @param phone
     * @return
     */
    private boolean isPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            showMessage("手机号码不能为空");
            return false;
        }
        if (!TextUtils.isEmpty(phone) && !RegexUtils.isMobileExact(phone)) {
            showMessage("请输入正确的手机号码");
            return false;
        }
        return true;
    }

    /**
     * 判断密码长度
     */
    private boolean isPass(String password) {
        if (password.length() < 6) {
            showMessage("密码长度错误！");
            return false;
        }
        return true;
    }

    /**
     * 判断验证码长度
     * @param code
     * @return
     */
    private boolean isCode(String code) {
        if (code.length() != 6) {
            showMessage("验证码长度错误！");
            return false;
        }
        return true;
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showCenterToast(message);
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void post(Runnable runnable) {

    }


}

