package com.example.college.Login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.college.R;
import com.google.android.material.snackbar.Snackbar;

public class InternetConnection extends Dialog {
    Context applicationContext;
    AlertDialog.Builder builder;

    public InternetConnection(Context applicationContext)
    {
        super(applicationContext);
        this.applicationContext=applicationContext;
    }
    public boolean isConnected(Context applicationContext) {
        ConnectivityManager connectivityManager=(ConnectivityManager)applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wificonnect=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileconnect=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wificonnect!=null && wificonnect.isConnected()) || (mobileconnect!=null && mobileconnect.isConnected()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void displayalertmessage(Context applicationContext)
    {
        builder=new AlertDialog.Builder(applicationContext);
        builder.setMessage("please connect to the Internet to proceed further");
        builder.setCancelable(false);
        builder.setPositiveButton("connect", (dialog, which) -> {
            getContext().startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        });
        builder.setNegativeButton("cancel", (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void displayalertbox()
    {
        Intent internet=new Intent(Settings.ACTION_WIFI_SETTINGS);
        getContext().startActivity(internet);
    }

    public void snackbar(LinearLayout layout)
    {
        Snackbar snackbar = Snackbar
                .make(layout, "No Internet ", Snackbar.LENGTH_LONG).setAction("Connect", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent internet=new Intent(Settings.ACTION_WIFI_SETTINGS);
                        internet.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        getContext().startActivity(internet);
                    }
                });
        View snackBarView = snackbar.getView();
        snackBarView.animate();

        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    public void snackbar(RelativeLayout layout)
    {
        Snackbar snackbar = Snackbar
                .make(layout, "No Internet ", Snackbar.LENGTH_LONG).setAction("Connect", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent internet=new Intent(Settings.ACTION_WIFI_SETTINGS);
                        internet.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        getContext().startActivity(internet);
                    }
                });
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

}
