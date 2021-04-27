package com.example.college.Login;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.college.R;

public class internetservice extends Dialog implements View.OnClickListener{

    Context context;
    public internetservice(@NonNull Context context) {
        super(context);
    }

    TextView connectinternet,cancelinternet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_internetservice);
        setCancelable(true);

        connectinternet=findViewById(R.id.connectinternet);
        cancelinternet=findViewById(R.id.cancelinternet);

        connectinternet.setOnClickListener(this);
        cancelinternet.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.connectinternet:
                InternetConnection internetConnection=new InternetConnection(getContext());
                internetConnection.displayalertbox();

            case R.id.cancelinternet:
                cancel();
        }
    }
}