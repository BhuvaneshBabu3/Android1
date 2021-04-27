package com.example.college;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.college.Settings.PeriodandTime.CustomTimePicker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetPeriodDetails extends AppCompatActivity implements View.OnClickListener{

    Spinner spinner_SetPeriodDetails_year,spinner_SetPeriodDetails_sec,spinner_SetPeriodDetails_Subject,spinner_SetPeriodDetails_Staff;
    TextView textview_time_start,textview_time_end,textview_confirmed;
    Spinner textview_periodnumber;

    Button btn_start_time,btn_end_time,btn_check,btn_save;
    RecyclerView reccyclerview_SetPeriodDetails_list;

    public static final String[] staffyear={"I","II","III","IV"};
    public static final String[] staffSec={"A","B","C"};
    public static final String[] staffPeriod={"1","2","3","4","5","6","7","8"};
    public static String YEAR,SEC,dept,STAFF,SUBJECT,PERIOD;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> sub = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_period_details);

        spinner_SetPeriodDetails_year=findViewById(R.id.spinner_SetPeriodDetails_year);
        spinner_SetPeriodDetails_sec=findViewById(R.id.spinner_SetPeriodDetails_sec);
        spinner_SetPeriodDetails_Subject=findViewById(R.id.spinner_SetPeriodDetails_Subject);
        spinner_SetPeriodDetails_Staff=findViewById(R.id.spinner_SetPeriodDetails_Staff);

        btn_start_time=findViewById(R.id.btn_start_time);
        btn_end_time=findViewById(R.id.btn_end_time);
        btn_check=findViewById(R.id.btn_check);
        btn_save=findViewById(R.id.btn_save);

        textview_periodnumber=findViewById(R.id.textview_periodnumber);
        textview_time_start=findViewById(R.id.textview_time_start);
        textview_time_end=findViewById(R.id.textview_time_end);
        textview_confirmed=findViewById(R.id.textview_confirmed);

        reccyclerview_SetPeriodDetails_list=findViewById(R.id.reccyclerview_SetPeriodDetails_list);

        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,staffSec);
        spinner_SetPeriodDetails_sec.setAdapter(arrayAdapter1);

        spinner_SetPeriodDetails_sec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0)
                {
                    SEC="A";
                }
                if (position==1)
                {
                    SEC="B";
                }
                if (position==2)
                {
                    SEC="C";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                SEC="A";
            }
        });

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,staffyear);
        spinner_SetPeriodDetails_year.setAdapter(arrayAdapter);

        spinner_SetPeriodDetails_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0)
                {
                    YEAR="I";
                }
                if (position==1)
                {
                    YEAR="II";
                }
                if (position==2)
                {
                    YEAR="III";
                }
                if (position==3)
                {
                    YEAR="IV";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                YEAR="I";
            }
        });


        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,staffPeriod);
        textview_periodnumber.setAdapter(arrayAdapter2);

        textview_periodnumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0)
                {
                    PERIOD="1";
                }
                if (position==1)
                {
                    PERIOD="2";
                }
                if (position==2)
                {
                    PERIOD="3";
                }
                if (position==3)
                {
                    PERIOD="4";
                }
                if (position==4)
                {
                    PERIOD="5";
                }
                if (position==5)
                {
                    PERIOD="6";
                }
                if (position==6)
                {
                    PERIOD="7";
                }
                if (position==7)
                {
                    PERIOD="8";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                PERIOD="1";
            }
        });


        Sessionmanagement sessionmanagement=new Sessionmanagement(getApplicationContext());
        dept=sessionmanagement.getdeptname();

        ApiInteface apiInteface=Api.getClient().create(ApiInteface.class);
        Call<List<PojoStaff>> call=apiInteface.getstaff(dept);

        call.enqueue(new Callback<List<PojoStaff>>() {
            @Override
            public void onResponse(Call<List<PojoStaff>> call, Response<List<PojoStaff>> response) {
                List<PojoStaff> datumlist= response.body();
                int i=0;
                for (PojoStaff datum:datumlist)
                {
                    names.add(i,datum.getStdname().toString());
                    i++;
                }

                ArrayAdapter<String> aa = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,names);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_SetPeriodDetails_Staff.setAdapter(aa);

                spinner_SetPeriodDetails_Staff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        STAFF=names.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        STAFF=names.get(0);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<PojoStaff>> call, Throwable t) {

            }
        });

        Call<List<SubjectDetails>> call1=apiInteface.getSubjects(dept);

        call1.enqueue(new Callback<List<SubjectDetails>>() {
            @Override
            public void onResponse(Call<List<SubjectDetails>> call, Response<List<SubjectDetails>> response) {
                List<SubjectDetails> datumlist= response.body();
                int i=0;
                for (SubjectDetails datum:datumlist)
                {
                    sub.add(i,datum.getSubjectname().toString());
                    i++;
                }

                ArrayAdapter<String> aa1 = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,sub);
                aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_SetPeriodDetails_Subject.setAdapter(aa1);

                spinner_SetPeriodDetails_Subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        SUBJECT=sub.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        SUBJECT=sub.get(0);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<SubjectDetails>> call, Throwable t) {

            }
        });

        btn_start_time.setOnClickListener(this);

        btn_end_time.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        CustomTimePicker customTimePicker=new CustomTimePicker(SetPeriodDetails.this);
        switch (v.getId())
        {
            case R.id.btn_start_time:
                customTimePicker.show();
                break;
            case R.id.btn_end_time:
                customTimePicker.show();
                break;

        }
    }

}