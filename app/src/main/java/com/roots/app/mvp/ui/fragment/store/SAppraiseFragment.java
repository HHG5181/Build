package com.roots.app.mvp.ui.fragment.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import com.roots.app.R;
import com.roots.app.app.base.BaseSupportFragment;

/**
 * @author : bird
 * @Classname : SAppraiseFragment
 * @Description : TODO
 * @Date : 2020/8/31 12:45
 */

public class SAppraiseFragment extends BaseSupportFragment {
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_s_appraise, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void post(Runnable runnable) {

    }
}
