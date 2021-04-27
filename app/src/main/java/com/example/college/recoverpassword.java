package com.example.college;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;

public class recoverpassword extends Dialog implements
        android.view.View.OnClickListener {

    public recoverpassword(@NonNull Context context) {
        super(context);
    }

    TextInputLayout registered_email;
    Button sendbutton;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recoverpassword);
        setCancelable(true);
        setTitle("Recover password");

        registered_email=(TextInputLayout)findViewById(R.id.registered_email);
        sendbutton=(Button)findViewById(R.id.sendbutton);

        sendbutton.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.sendbutton:
                email=registered_email.getEditText().toString();
                
        }
    }
}