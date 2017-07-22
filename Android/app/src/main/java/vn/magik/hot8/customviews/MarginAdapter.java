package vn.magik.hot8.customviews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;


/**
 * Created by dkdarkness on 3/4/2016.
 */
public class MarginAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;

    public MarginAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public float getPageWidth(int position) {
        return 0.93f;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


}