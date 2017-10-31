package com.hosiluan.usermanager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hosiluan.usermanager.fragment.FirstScreenFragment;
import com.hosiluan.usermanager.fragment.SecondScreenFragment;
import com.hosiluan.usermanager.fragment.ThirdScreenFragment;

/**
 * Created by User on 10/31/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new FirstScreenFragment();
                break;
            case 1:
                fragment = new SecondScreenFragment();
                break;
            case 2:
                fragment = new ThirdScreenFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
