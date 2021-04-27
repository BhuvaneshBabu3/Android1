package com.example.college.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.example.college.Attendance.Attendance_Fragment;
import com.example.college.Dashboard.Dashboard_fragment;
import com.example.college.R;
import com.example.college.Settings.Setting_Fragment;
import com.example.college.Update.Update_Fragment;
import com.google.android.material.navigation.NavigationView;

import static androidx.appcompat.app.AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    public void onBackPressed()
    {
        if (drawer_layout.isDrawerOpen(GravityCompat.START))
        {
            drawer_layout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
            startActivity(new Intent(HomeActivity.this,FrontActivity.class));
            finishAffinity();
        }
    }

    private DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(FEATURE_SUPPORT_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("College");
        }

        drawer_layout=findViewById(R.id.drawer_layout);
        NavigationView nav_view=findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer_layout,toolbar,R.string.naviation,
                R.string.navclose);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.dashboardicon);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Dashboard_fragment()).commit();
        nav_view.setCheckedItem(R.id.Dashboard);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.Dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Dashboard_fragment()).commit();
                break;
            case R.id.Attendance:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Attendance_Fragment()).commit();
                break;
            case R.id.Update:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Update_Fragment()).commit();
                break;
            case R.id.Settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Setting_Fragment()).commit();
                break;
        }
        drawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu_items, menu);
        return true;
    }*/
}