package com.example.college;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

public class Sessionmanagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String SHARED_PREF_NAME="session";
    String SESSION_KEY="session_user";
    String SESSION_USER_NAME="sess_name";
    String SESSION_DEPT="sess_dept";
    String SESSION_POST="sess_post";
    String name,dept,posting;

    @SuppressLint("CommitPrefEdits")
    public Sessionmanagement(@NotNull Context context)
    {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public SharedPreferences.Editor getEditor()
    {
        return editor;
    }

    public void saveSession(@NotNull User user)
    {
        int id=user.getId();
        editor.putInt(SESSION_KEY,id).commit();
        name=user.getName();
        editor.putString(SESSION_USER_NAME,name).commit();
    }

    public int getSession()
    {
        return sharedPreferences.getInt(SESSION_KEY,-1);
    }

    public int getdept()
    {
        return sharedPreferences.getInt(SESSION_DEPT,-1);
    }

    public String getdeptname()
    {
        return sharedPreferences.getString(SESSION_DEPT,"dept");
    }

    public String getUSer()
    {
        return name;
    }

    public String getPosting() {
        return sharedPreferences.getString(SESSION_POST,"post");
    }

    public void savedept(User deptname) {
        dept=deptname.getDept();
        editor.putString(SESSION_DEPT,dept).commit();
    }

    public void savepost(User postname)
    {
        posting=postname.getPost();
        editor.putString(SESSION_POST,posting).commit();
    }

    public void removeSession()
    {
        editor.putInt(SESSION_KEY,-1).commit();
    }

}
