package com.example.college.Settings.PeriodandTime;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.college.Api;
import com.example.college.ApiInteface;
import com.example.college.R;
import com.example.college.Sessionmanagement;
import com.example.college.SubjectDetails;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTimetable#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTimetable extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String dept;
    private String mParam2;
    RecyclerView recyclersubject;
    ArrayList<SubjectDetails> subjectDetailsArrayList;
    SubjectListAdapter subjectListAdapter;
    SwipeRefreshLayout swipeRefreshsubjects;
    View view;
    Toolbar toolbaraddstudent;

    public FragmentTimetable() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTimetable.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTimetable newInstance(String param1, String param2) {
        FragmentTimetable fragment = new FragmentTimetable();
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
//            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_timetable, container, false);
//        view.setSupportActionBar(toolbar);
        toolbaraddstudent=view.findViewById(R.id.toolbaraddstudent1);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbaraddstudent);
        setHasOptionsMenu(true);
        Button subjectdetaills=view.findViewById(R.id.subjectdetaills);
        recyclersubject=view.findViewById(R.id.recyclersubject);
        swipeRefreshsubjects=view.findViewById(R.id.swipeRefreshsubjects);

        subjectdetaills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PeriodDialogueBox periodDialogueBox=new PeriodDialogueBox(view.getContext());
                periodDialogueBox.show();
            }
        });
        Sessionmanagement sessionmanagement=new Sessionmanagement(view.getContext());
        dept=sessionmanagement.getdeptname();

        Recycle();

        swipeRefreshsubjects.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshsubjects.setRefreshing(false);
                Recycle();
            }
        });

        return view;
    }

    private void Recycle() {
        ApiInteface apiInterface= Api.getClient().create(ApiInteface.class);
        Call<ArrayList<SubjectDetails>> call=apiInterface.getSubjectdetails(dept);
        call.enqueue(new Callback<ArrayList<SubjectDetails>>() {
            private LinearLayoutManager VerticalLinierLayout;
            @Override
            public void onResponse(Call<ArrayList<SubjectDetails>> call, Response<ArrayList<SubjectDetails>> response) {
                recyclersubject.setHasFixedSize(true);
                subjectDetailsArrayList=response.body();
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false);
                recyclersubject.setLayoutManager(VerticalLinierLayout);

                subjectListAdapter=new SubjectListAdapter(view.getContext(),subjectDetailsArrayList);
                VerticalLinierLayout=new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false);
                recyclersubject.setLayoutManager(VerticalLinierLayout);
                recyclersubject.setAdapter(subjectListAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<SubjectDetails>> call, Throwable t) {

            }
        });
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.student_menu_items,menu);
        MenuItem item=menu.findItem(R.id.action_search);

        SearchView searchView=(SearchView)item.getActionView();
        searchView.setQueryHint("Search Name");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater=new MenuInflater(view.getContext());
        inflater.inflate(R.menu.student_menu_items1,menu);


        MenuItem mSearchMenuItem = menu.findItem(R.id.action_search1);
        SearchView searchView = (SearchView) mSearchMenuItem.getActionView();

//        menu.clear();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                subjectListAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.student_menu_items1,menu);
        MenuItem item=menu.findItem(R.id.action_search1);

        SearchView searchView=(SearchView)item.getActionView();
        searchView.setQueryHint("Search Name");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                subjectListAdapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}