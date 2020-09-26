package com.roots.app.mvp.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.roots.app.app.base.BaseSupportFragment;

import java.util.ArrayList;

public class BottomPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseSupportFragment> fragments;

    public BottomPagerAdapter(FragmentManager fm, ArrayList<BaseSupportFragment> fs) {
        super(fm);
        this.fragments = fs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
