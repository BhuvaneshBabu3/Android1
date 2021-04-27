package com.example.college.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.college.Api;
import com.example.college.ApiInteface;
import com.example.college.ListPojostaff;
import com.example.college.PojoStaff;
import com.example.college.R;
import com.example.college.Sessionmanagement;
import com.example.college.User;
import com.example.college.recoverpassword;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class Login extends AppCompatActivity implements
        android.view.View.OnClickListener{

    @Override
    protected void onStart() {
        super.onStart();
        checkSession();
    }

    private void checkSession() {
        Sessionmanagement sessionManagement=new Sessionmanagement(Login.this);
        int isUserLoggedin=sessionManagement.getSession();

        if (isUserLoggedin!=-1)
        {

            moveToMainActivity();
        }
        else
        {

        }
    }

    CheckBox checkboxlogin;
    TextView remembermecheck;
    boolean b=true;
    String rollnum,password;
    TextInputLayout loginname,lognipassword;
    Button loginsubmit,logincancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginname=(TextInputLayout) findViewById(R.id.loginname);
        lognipassword=(TextInputLayout)findViewById(R.id.lognipassword);
        checkboxlogin=findViewById(R.id.checkboxlogin);
        rollnum=loginname.getEditText().getText().toString();
        password=lognipassword.getEditText().getText().toString();

        TextView fgtpass=findViewById(R.id.fgtpass);
        remembermecheck=findViewById(R.id.remembermecheck);
        fgtpass.setOnClickListener(this);
        remembermecheck.setOnClickListener(this);

        loginsubmit=findViewById(R.id.loginsubmit);
        logincancel=findViewById(R.id.logincancel);

        loginsubmit.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fgtpass:
                recoverpassword recoverpassword=new recoverpassword(Login.this);
                recoverpassword.show();
                break;

            case R.id.remembermecheck:
                boolean c=!b;
                if (checkboxlogin.isChecked())
                {
                    checkboxlogin.setChecked(false);
                }
                else
                {
                    checkboxlogin.setChecked(b);
                    b=c^b;
                }
                break;

            case R.id.loginsubmit:
                rollnum=loginname.getEditText().getText().toString();
                password=lognipassword.getEditText().getText().toString();
                ApiInteface apiInterface= Api.getClient().create(ApiInteface.class);
                Call<ListPojostaff> call=apiInterface.getstafflogin(rollnum);
                call.enqueue(new Callback<ListPojostaff>() {
                    @Override
                    public void onResponse(@NotNull Call<ListPojostaff> call, @NotNull Response<ListPojostaff> response) {
                        if (response.body().getPojoStaffs().get(0).getPassword_().equals(password))
                        {
                            Toast.makeText(getApplicationContext(),"Correct",Toast.LENGTH_LONG).show();
                            if (checkboxlogin.isChecked())
                            {
                                User user=new User(1,response.body().getPojoStaffs().get(0).getStdname(),response.body().getPojoStaffs().get(0)
                                .getDept(),response.body().getPojoStaffs().get(0).getPost());

                                Sessionmanagement sessionmanagement=new Sessionmanagement(Login.this);
                                sessionmanagement.saveSession(user);
                                sessionmanagement.savedept(user);
                                sessionmanagement.savepost(user);

                                moveToMainActivity();
                            }

                            else
                            {
                                Intent i=new Intent(Login.this,HomeActivity.class);
                                Sessionmanagement sessionManagement=new Sessionmanagement(Login.this);
                                User user=new User(1,response.body().getPojoStaffs().get(0).getStdname(),response.body().getPojoStaffs().get(0)
                                        .getDept(),response.body().getPojoStaffs().get(0).getPost());
                                sessionManagement.savedept(user);
                                sessionManagement.savepost(user);
                                startActivity(i);
                            }
                        }
                        else if ((!response.body().getPojoStaffs().get(0).getPassword_().equals(password)))
                        {
                            Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<ListPojostaff> call, @NotNull Throwable t) {
                        Toast.makeText(getApplicationContext(),"failed"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }
                });
                break;

        }
    }

    private void moveToMainActivity() {
        Intent intent=new Intent(Login.this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}