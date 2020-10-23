package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class DiscoverActivity extends AppCompatActivity
{
    private static final String TAG = "DiscoverActivity";

    TabLayout d_tabLayout;
    ViewPager d_viewPager;
    D_PageAdapter d_pageAdapter;
    TabItem tabTrees;
    TabItem tabFacts;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        setTitle("Discover");

        //plantName = "neem";
        //fetchPlantDetails(plantName);

        Log.i(TAG,"Discover_Activity");

        d_tabLayout = findViewById(R.id.d_tabLayout);
        d_viewPager = findViewById(R.id.d_viewPager);
        tabTrees = findViewById(R.id.tabTrees);
        tabFacts = findViewById(R.id.tabFacts);

        d_pageAdapter = new D_PageAdapter(getSupportFragmentManager(),d_tabLayout.getTabCount());
        d_viewPager.setAdapter(d_pageAdapter);

        d_tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                d_viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1)
                {
                    //toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                    //tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    {
                        getWindow().setStatusBarColor(ContextCompat.getColor(DiscoverActivity.this, R.color.colorPrimary));
                    }
                }
                else if (tab.getPosition() == 2)
                {
                    //toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                    d_tabLayout.setBackgroundColor(ContextCompat.getColor(DiscoverActivity.this, R.color.colorAccent));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    {
                        getWindow().setStatusBarColor(ContextCompat.getColor(DiscoverActivity.this, R.color.colorAccent));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });

        d_viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(d_tabLayout));
    }
}
