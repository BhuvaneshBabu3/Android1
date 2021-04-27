package com.example.college.Settings.PeriodandTime;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter1 extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String dept,attendername;
    public PagerAdapter1(@NonNull FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs=NumOfTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                FragmentPeriod tab1=new FragmentPeriod();
                return tab1;
            case 1:
                FragmentTiming tab2=new FragmentTiming();
                return tab2;
            case 2:
                FragmentTimetable tab3=new FragmentTimetable();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        int i= super.getItemPosition(object);
        switch (i)
        {
            case 0:
                FragmentPeriod tab1=new FragmentPeriod();
//                tab1.getFragmentManager().beginTransaction().replace().commit();

            case 1:
                FragmentTiming tab2=new FragmentTiming();

            /*case 2:
                FragmentTimetable tab3=new FragmentTimetable();*/
        }
        return i;
    }


    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

