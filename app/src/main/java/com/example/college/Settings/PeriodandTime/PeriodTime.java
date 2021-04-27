package com.example.college.Settings.PeriodandTime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.college.R;
import com.google.android.material.tabs.TabLayout;

public class PeriodTime extends AppCompatActivity {
    ViewPager simpleViewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_time);

        simpleViewPager = (ViewPager)findViewById(R.id.simpleViewPager);
        tabLayout = (TabLayout) findViewById(R.id.simpleTabLayout);

        TabLayout.Tab firstTab=tabLayout.newTab();
        firstTab.setText("Period");

        tabLayout.addTab(firstTab);

        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("Timing");

        tabLayout.addTab(secondTab);

        TabLayout.Tab thirdTab = tabLayout.newTab();
        thirdTab.setText("Subject");

        tabLayout.addTab(thirdTab);

        /*tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {
                    case 0:
                        FragmentPeriod tab1=new FragmentPeriod();
                        tab1.getFragmentManager().beginTransaction().commit();

                    case 1:
                        FragmentTiming tab2=new FragmentTiming();
                        tab2.getFragmentManager().beginTransaction().commit();
                    case 2:
                        FragmentTimetable tab3=new FragmentTimetable();
                        tab3.getFragmentManager().beginTransaction().commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

        PagerAdapter1 adapter = new PagerAdapter1
                (getSupportFragmentManager(), tabLayout.getTabCount());

        simpleViewPager.setAdapter(adapter);
        simpleViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



    }
}