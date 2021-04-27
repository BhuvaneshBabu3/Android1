package com.example.college.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.college.R;

public class FrontActivity extends AppCompatActivity {

    boolean b;
    LinearLayout linearlayoutid1;

    @Override
    protected void onStart() {
        super.onStart();
        InternetConnection internetConnection=new InternetConnection(getApplicationContext());
        b=internetConnection.isConnected(getApplicationContext());
        if (!b)
        {
            internetConnection.snackbar(linearlayoutid1);
        }
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        InternetConnection internetConnection=new InternetConnection(getApplicationContext());
        b=internetConnection.isConnected(getApplicationContext());
        if (!b)
        {
            internetConnection.snackbar(linearlayoutid1);
        }
    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);


        Button front_login,front_register;
        front_login=(Button)findViewById(R.id.front_login);
        front_register=(Button)findViewById(R.id.front_register);
        linearlayoutid1=findViewById(R.id.linearlayoutid1);
        InternetConnection internetConnection=new InternetConnection(getApplicationContext());



        front_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b=internetConnection.isConnected(getApplicationContext());
                if (!b)
                {
                    internetConnection.snackbar(linearlayoutid1);
                }
                else
                {
                    Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                    startActivity(intent);
                }
            }
        });

        front_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b=internetConnection.isConnected(getApplicationContext());
                if (!b)
                {
                    internetConnection.snackbar(linearlayoutid1);
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                }
            }
        });
    }


}