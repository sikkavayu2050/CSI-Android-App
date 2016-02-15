package com.dtu.csi.csi_dtu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dtu.csi.csi_dtu.fragments.EventDetailsFragment;

public class MainPagerAdapter extends FragmentStatePagerAdapter{
    CharSequence tabTitles[];
    int numOfTabs;
    public MainPagerAdapter(FragmentManager fm, CharSequence titles[], int numOfTabs) {
        super(fm);
        this.tabTitles = titles;
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new EventDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", tabTitles[position].toString().toLowerCase());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
