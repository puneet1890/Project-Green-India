package com.example.projectgreenindia;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class D_PageAdapter extends FragmentPagerAdapter
{
    private int numb_of_tabs;

    D_PageAdapter(@NonNull FragmentManager fm, int numb_of_tabs)
    {
        super(fm);
        this.numb_of_tabs = numb_of_tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0: return new TreeDetailsFragment();
            case 1: return new TreeFactsFragment();
            default: return null;
        }
    }

    @Override
    public int getCount()
    {
        return numb_of_tabs;
    }
}
