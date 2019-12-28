package com.example.tabtwo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount){
        super(fm);
        this.tabCount=tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TabFragment1 Fragment1 = new TabFragment1();
                return Fragment1;
            case 1:
                TabFragment2 Fragment2 = new TabFragment2();
                return Fragment2;
            case 2:
                TabFragment3 Fragment3=new TabFragment3();
                return Fragment3;
            default:
                return null;

        }
    }

    @Override
    public int getCount(){
        return tabCount;
    }
}


