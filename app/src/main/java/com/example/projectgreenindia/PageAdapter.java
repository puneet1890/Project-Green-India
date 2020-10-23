package com.example.projectgreenindia;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter
{
    private int numb_of_tabs;

    PageAdapter(@NonNull FragmentManager fm, int numb_of_tabs)
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
            case 0: return new PlantDetailsFragment();
            case 1: return new UserPlantsHistroyFragment();
            default: return null;
        }
    }

    @Override
    public int getCount()
    {
        return numb_of_tabs;
    }
}
