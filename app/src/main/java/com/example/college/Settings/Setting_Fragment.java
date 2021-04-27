package com.example.college.Settings;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.college.R;
import com.example.college.Settings.PeriodandTime.PeriodTime;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Setting_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Setting_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Setting_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Setting_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Setting_Fragment newInstance(String param1, String param2) {
        Setting_Fragment fragment = new Setting_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    CardView card1st,card2nd,card3rd,card4th;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_setting, container, false);
        card1st=view.findViewById(R.id.card1st);
        card2nd=view.findViewById(R.id.card2nd);
        card3rd=view.findViewById(R.id.card3rd);
        card4th=view.findViewById(R.id.card4th);

        card1st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(),AddStudent.class);
                startActivity(intent);
            }
        });

        card2nd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(), PeriodTime.class);
                startActivity(intent);
            }
        });

        card3rd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(),Coordinatordetails.class);
                startActivity(intent);
            }
        });

        return view;
    }
}