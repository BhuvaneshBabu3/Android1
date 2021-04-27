package com.example.college.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.college.Api;
import com.example.college.ApiInteface;
import com.example.college.ListPojostaff;
import com.example.college.R;
import com.example.college.Sessionmanagement;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Coordinatordetails extends AppCompatActivity {

    String dept,sec,year;
    Spinner spinnercoordinator;

    ArrayList<String> names = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatordetails);
        spinnercoordinator=findViewById(R.id.spinnercoordinator);

        Sessionmanagement sessionmanagement=new Sessionmanagement(getApplicationContext());
        dept=sessionmanagement.getdeptname();

        CircleMenu circleMen2 = (CircleMenu) findViewById(R.id.circle_menu2);
        CircleMenu circleMenu3 = (CircleMenu) findViewById(R.id.circle_menu3);
        TextView secid=findViewById(R.id.secid1);
        TextView yearid=findViewById(R.id.yearid1);

        ApiInteface apiInterface= Api.getClient().create(ApiInteface.class);
        Call<ListPojostaff> call=apiInterface.getcoordinator(dept);

        call.enqueue(new Callback<ListPojostaff>() {
            @Override
            public void onResponse(@NotNull Call<ListPojostaff> call, @NotNull Response<ListPojostaff> response) {
                ListPojostaff resources=response.body();
                List<ListPojostaff.PojoStaff1> datumlist=resources.getPojoStaffs();
                int i=0;
                for (ListPojostaff.PojoStaff1 pojoStaff1:datumlist)
                {
                    names.add(i,pojoStaff1.getStdname());
                    i++;
                }

                ArrayAdapter<String> aa = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,names);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnercoordinator.setAdapter(aa);
            }

            @Override
            public void onFailure(@NotNull Call<ListPojostaff> call, @NotNull Throwable t) {

            }
        });
        circleMen2.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.add_circle, R.drawable.account)
                .addSubMenu(Color.parseColor("#FFFF00"), R.drawable.one)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.two)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.three)
                .addSubMenu(Color.parseColor("#87CEEB"), R.drawable.four)
                .setOnMenuSelectedListener(index -> {
                    switch (index)
                    {
                        case 0:
                            yearid.setText("1");
                            year="1";
                            Toast.makeText(getApplicationContext(),"1st",Toast.LENGTH_LONG).show();
                            break;
                        case 1:
                            yearid.setText("2");
                            year="2";
                            break;
                        case 2:
                            yearid.setText("3");
                            year="3";
                            break;
                        case 3:
                            yearid.setText("4");
                            year="4";
                            break;
                    }
                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {
                if (circleMen2.isOpened())
                {
                    circleMen2.canScrollVertically(2);
                }
            }

            @Override
            public void onMenuClosed() {

            }

        });

        circleMenu3.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.add_circle, R.drawable.account)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.a)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.b)
                .addSubMenu(Color.parseColor("#FFFF00"), R.drawable.c)
                .setOnMenuSelectedListener(index -> {
                    switch (index) {
                        case 0:
                            secid.setText("A");
                            sec="A";
                            break;
                        case 1:
                            secid.setText("B");
                            sec="B";
                            break;
                        case 2:
                            secid.setText("C");
                            sec="C";
                            break;
                    }
                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {

            }

            @Override
            public void onMenuClosed() {

            }

        });

    }
}