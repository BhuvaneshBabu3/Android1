package com.example.college.Settings.PeriodandTime;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.college.Api;
import com.example.college.ApiInteface;
import com.example.college.R;
import com.example.college.Sessionmanagement;
import com.example.college.SubjectDetails;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.time.Year;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeriodDialogueBox extends Dialog implements View.OnClickListener{

    public PeriodDialogueBox(@NonNull Context context) {
        super(context);
    }

    TextInputLayout SubjectText,SubjectCode,SubjectRegulation;
    Button subjectadd,subjectcancel;
    private String subjectname,subjectcode,dept,regulation,year;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onStart() {
        Calendar calendar = Calendar.getInstance();
        int yr = calendar.get(Calendar.YEAR);
        year=String.valueOf(yr);
        SubjectRegulation.getEditText().setText(year);
        regulation=year;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_period_dialogue_box);
        setCanceledOnTouchOutside(false);

        SubjectText=findViewById(R.id.SubjectText);
        SubjectCode=findViewById(R.id.SubjectCode);
        SubjectRegulation=findViewById(R.id.SubjectRegulation);

        subjectadd=findViewById(R.id.subjectadd);
        subjectcancel=findViewById(R.id.subjectcancel);

        subjectadd.setOnClickListener(this);
        subjectcancel.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.subjectadd:

//                SubjectRegulation.setHelperText(year);
//                regulation=year;
                Calendar calendar = Calendar.getInstance();
                int yr = calendar.get(Calendar.YEAR);
                year=String.valueOf(yr);
                SubjectRegulation.getEditText().setText(year);
                regulation=year;
                subjectname=SubjectText.getEditText().getText().toString();
                subjectcode=SubjectCode.getEditText().getText().toString();
                regulation=SubjectRegulation.getEditText().getText().toString();
                Sessionmanagement sessionmanagement=new Sessionmanagement(getContext());
                dept=sessionmanagement.getdeptname();

                ApiInteface apiInterface= Api.getClient().create(ApiInteface.class);
                SubjectDetails subjectDetails=new SubjectDetails(subjectname,subjectcode,dept,regulation);
                Call<SubjectDetails> call=apiInterface.addSubject(subjectDetails);
                call.enqueue(new Callback<SubjectDetails>() {
                    @Override
                    public void onResponse(@NotNull Call<SubjectDetails> call, @NotNull Response<SubjectDetails> response) {
                        SubjectDetails message=response.body();
                        Toast.makeText(getContext(),message.getResponse()
                                ,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(@NotNull Call<SubjectDetails> call,@NotNull  Throwable t) {

                    }
                });

                break;

            case R.id.subjectcancel:
                cancel();
                break;
        }
    }
}