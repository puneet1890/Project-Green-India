package com.example.projectgreenindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MyPlantActivity extends AppCompatActivity
{
    private static final String TAG = "MyPlantActivity";
    //Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    TabItem tabPlants;
    TabItem tabHistory;

    String email;
    FragmentManager fm;
    FragmentTransaction transaction;
    UserPlantsHistroyFragment fragment;

    //FragmentCommunicator fragmentCommunicator;
    //private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plant);

        setTitle("MyPlant");

        Intent intent = getIntent();
        email = intent.getStringExtra("user_email");
        Log.i(TAG,"Email in MyPlantActivity: "+email);

        //toolbar = findViewById(R.id.toolbar);
        //toolbar.setTitle("TabLayout & View Pager");
        //setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabPlants = findViewById(R.id.tabPlants);
        tabHistory = findViewById(R.id.tabHistroy);

        pageAdapter = new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        UserPlantsHistroyFragment.email = email;
        PlantDetailsFragment.email = email;


        //UserPlantsHistroyFragment fragment = (UserPlantsHistroyFragment) getSupportFragmentManager().findFragmentById(R.id.UPHF_LinearLayout);

        //fragment = (UserPlantsHistroyFragment) getSupportFragmentManager().findFragmentByTag("UserPlantsHistroy");

        //if (fragment != null)
        //{
            //fragment.setEmail(email);
        //}

/*
        Bundle bundle = new Bundle();
        bundle.putString("user_email", email);
        // set MyFragment Arguments

        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();

        UserPlantsHistroyFragment uphfObj = new UserPlantsHistroyFragment();
        uphfObj.setArguments(bundle);
        transaction.replace(R.id.tabHistroy, uphfObj);
        transaction.commit();

*/
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1)
                {
                    //toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                    //tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MyPlantActivity.this, R.color.colorPrimary));
                    }
                }
                else if (tab.getPosition() == 2)
                {
                    //toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MyPlantActivity.this, R.color.colorAccent));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MyPlantActivity.this, R.color.colorAccent));
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

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

/*
    public void passVal(FragmentCommunicator fragmentCommunicator)
    {
        this.fragmentCommunicator = fragmentCommunicator;
    }
*/

}

