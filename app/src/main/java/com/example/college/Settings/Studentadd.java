package com.example.college.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.college.Api;
import com.example.college.ApiInteface;
import com.example.college.Login.InternetConnection;
import com.example.college.PojoStudent;
import com.example.college.R;
import com.example.college.Sessionmanagement;
import com.example.college.Settings.PeriodandTime.PeriodDialogueBox;
import com.google.android.material.textfield.TextInputLayout;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Studentadd extends AppCompatActivity {

    CircleMenu circleMenu,circleMenu1;
    TextView secid,yearid;
    TextInputLayout studentname,studentrollnum,parentsphonenumber,studentemail;
    Button submitstudent;
    ProgressDialog studentsSaveProgress;
    LinearLayout Linearlayout_student;

    String name,rollnum,phonenumber,email,year,sec,dept;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentadd);

        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        circleMenu1 = (CircleMenu) findViewById(R.id.circle_menu1);
        secid=findViewById(R.id.secid);
        yearid=findViewById(R.id.yearid);

        studentname=findViewById(R.id.studentname);
        studentrollnum=findViewById(R.id.studentrollnum);
        parentsphonenumber=findViewById(R.id.parentsphonenumber);
        studentemail=findViewById(R.id.studentemail);

        Linearlayout_student=findViewById(R.id.Linearlayout_student);


        submitstudent=findViewById(R.id.submitstudent);


        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.add_circle, R.drawable.account)
                .addSubMenu(Color.parseColor("#FFFF00"), R.drawable.one)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.two)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.three)
                .addSubMenu(Color.parseColor("#87CEEB"), R.drawable.four)
                .setOnMenuSelectedListener(index -> {
                    switch (index)
                    {
                        case 0:
                            yearid.setText("1");
                            year="I";
                            Toast.makeText(getApplicationContext(),"1st",Toast.LENGTH_LONG).show();
                            break;
                        case 1:
                            yearid.setText("2");
                            year="II";
                            break;
                        case 2:
                            yearid.setText("3");
                            year="III";
                            break;
                        case 3:
                            yearid.setText("4");
                            year="IV";
                            break;
                    }
                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {
                if (circleMenu.isOpened())
                {
                    circleMenu.canScrollVertically(2);
                }
            }

            @Override
            public void onMenuClosed() {

            }

        });

            circleMenu1.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.add_circle, R.drawable.account)
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

        Sessionmanagement sessionmanagement=new Sessionmanagement(getApplicationContext());
        dept=sessionmanagement.getdeptname();

         submitstudent.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Linearlayout_student.setVisibility(View.GONE);
                 name=studentname.getEditText().getText().toString();
                 rollnum=studentrollnum.getEditText().getText().toString();
                 phonenumber=parentsphonenumber.getEditText().getText().toString();
                 email=studentemail.getEditText().getText().toString();

                 studentsSaveProgress=new ProgressDialog(Studentadd.this);
                 studentsSaveProgress.setMax(100);
                 studentsSaveProgress.setMessage("Saving");
                 studentsSaveProgress.show();


                 ApiInteface apiInterface= Api.getClient().create(ApiInteface.class);
                 PojoStudent pojoStudent=new PojoStudent(name,rollnum,dept,phonenumber,email,year,sec);
                 Call<PojoStudent> call=apiInterface.insertStudent(pojoStudent);

                 call.enqueue(new Callback<PojoStudent>() {
                     @Override
                     public void onResponse(@NotNull Call<PojoStudent> call, @NotNull Response<PojoStudent> response) {
                         if (response.isSuccessful())
                         {
                             if (response.body().getResponse().matches("Inserted Successfully"))
                             {
                                 studentsSaveProgress.dismiss();
                                 Studentadd.super.onBackPressed();
                                 startActivity(new Intent(Studentadd.this,AddStudent.class));
                                 finishAffinity();
                             }
                             else if (response.body().getResponse().matches("Subject code already available"))
                             {
                                 studentsSaveProgress.dismiss();
                                 Linearlayout_student.setVisibility(View.VISIBLE);
                                 studentrollnum.setError(response.body().getResponse());
                             }
                         }
                         if (!response.isSuccessful())
                         {
                             if (call.isCanceled())
                             {
                                 studentsSaveProgress.dismiss();
                                 Linearlayout_student.setVisibility(View.VISIBLE);
                                 InternetConnection internetConnection=new InternetConnection(getApplicationContext());
                                 boolean b=internetConnection.isConnected(getApplicationContext());
                                 if (!b)
                                 {
                                     internetConnection.snackbar(Linearlayout_student);
                                 }
                             }
                         }
                     }

                     @Override
                     public void onFailure(@NotNull Call<PojoStudent> call, @NotNull Throwable t) {

                     }
                 });
             }
         });
    }
}