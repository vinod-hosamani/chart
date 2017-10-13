package com.example.mindgate.mpchart;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


class PagerAdapter extends FragmentStatePagerAdapter {
    private int mNumberOfTabs;
    PagerAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.mNumberOfTabs=NumberOfTabs;

    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OverviewFragment();
            case 1:
                return new InsightFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumberOfTabs;
    }
}
