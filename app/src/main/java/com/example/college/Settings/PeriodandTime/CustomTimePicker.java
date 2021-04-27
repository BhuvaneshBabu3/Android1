package com.example.college.Settings.PeriodandTime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TimePicker;

import com.example.college.R;

public class CustomTimePicker extends Dialog {

    TimePicker timepicker_period;
    String timer;
    public CustomTimePicker(@NonNull Context context) {
        super(context);
//        this.timer=timer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_time_picker);

        timepicker_period=findViewById(R.id.timepicker_period);
        timepicker_period.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (minute>=0 && minute <=9)
                {
                    timer=hourOfDay+":0"+minute+":"+"00";
                }
                else
                {
                    timer=hourOfDay+":"+minute+":"+"00";
                }

                minute=minute+10;
                if (minute>=60)
                {
                    minute=minute-60;
                    hourOfDay = hourOfDay + 1;
                    timer = hourOfDay + ":0" + minute + ":" + "00";
                }
                else
                {
                    timer=hourOfDay+":"+minute+":"+"00";
                }
            }
        });

    }
}