package com.vishesh.placement.dashboard.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.HashMap;
import java.util.Map;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private final Map<Integer, Fragment> fragmentMap;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentMap = new HashMap<>();
    }

    public void addFragment(Integer position, Fragment fragment) {
        fragmentMap.put(position, fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentMap.get(position);
    }

    @Override
    public int getCount() {
        return fragmentMap.size();
    }
}
