package com.roots.app.mvp.ui.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import com.roots.app.R;
import com.roots.app.app.base.BaseSupportActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : bird
 * @Classname : RegisterTypeActivity
 * @Description : TODO
 * @Date : 2020/8/25 21:54
 */
public class RegisterTypeActivity extends BaseSupportActivity {

    @BindView((R.id.toolbar_title))
    TextView title;
    @BindView((R.id.toolbar_back))
    RelativeLayout rlBack;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_register_type;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        title.setText("注册");
        rlBack.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_register_common)
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register_common:
//                startActivity(new Intent(mContext, RegisterCommonActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void post(Runnable runnable) {

    }
}

