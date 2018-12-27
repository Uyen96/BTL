package com.example.hongu.btl.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hongu.btl.fragment.AddCosmeticFragment;
import com.example.hongu.btl.fragment.AddEffectFragment;

import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {
    private List<String> mTitles;
    private AddEffectFragment mAddEffectFragment;
    private AddCosmeticFragment mAddCosmeticFragment;

    public static final int NUM_PAGER = 2;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        mAddEffectFragment = new AddEffectFragment();
        mAddCosmeticFragment = new AddCosmeticFragment();
    }

    @Override
    public Fragment getItem(int i){
        switch (i) {
            case 0:
                return mAddEffectFragment;

            case 1:
                return mAddCosmeticFragment;

            default:
                return mAddEffectFragment;

        }
    }

    @Override
    public int getCount() {
        return NUM_PAGER;
    }
    public CharSequence getPageTitle(int i) {
        switch (i) {
            case 0:
                return "Thêm công dụng";
            case 1:
                return "Thêm mỹ phẩm";
            default:
                return "Thêm công dụng";
        }
    }

}
