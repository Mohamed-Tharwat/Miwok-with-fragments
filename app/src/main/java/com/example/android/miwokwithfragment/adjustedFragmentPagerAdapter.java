package com.example.android.miwokwithfragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by m.sarwat on 3/20/2018.
 */

public class adjustedFragmentPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[]{"Colors", "Family", "Numbers", "Phrases"};

    public adjustedFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ColorsFragment();
            case 1:
                return new FamilyFragment();
            case 2:
                return new NumbersFragment();
            default:
                return new PhrasesFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return 4;
    }
}
