package com.example.college.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.college.Api;
import com.example.college.ApiInteface;
import com.example.college.PojoStudent;
import com.example.college.R;
import com.example.college.Sessionmanagement;
import com.example.college.StudentListAdapter;
import com.example.college.SwipeToDeleteCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddStudent extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView AddrecycleIst;
    ArrayList<PojoStudent> listPojoStudent;
    StudentListAdapter adapter;
    Toolbar toolbaraddstudent;
    SwipeRefreshLayout swipeRefreshLayout;
    String dept;
    RelativeLayout rel1;
        int i=1,a=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        fab=findViewById(R.id.fab);
        fab.setImageResource(R.drawable.add_circle);
        fab.setBackgroundResource(R.color.white);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddStudent.this,Studentadd.class));
            }
        });

        rel1=findViewById(R.id.rel1);

        AddrecycleIst=findViewById(R.id.AddrecycleIst);
        toolbaraddstudent=findViewById(R.id.toolbaraddstudent);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
//        setSupportActionBar(toolbaraddstudent);

        setSupportActionBar(toolbaraddstudent);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!= null) {
            getSupportActionBar().setTitle("Student");
        }
//        toolbaraddstudent.inflateMenu(R.menu.drawer_menu_items);

//        Window window = this.getWindow();
//
//// clear FLAG_TRANSLUCENT_STATUS flag:
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//
//// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//
//// finally change the color
//        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.style1));
        }

        AddrecycleIst.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                }
            }
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    fab.show();
                }
                if (newState==RecyclerView.SCROLL_STATE_SETTLING)
                {
                    fab.setTranslationX(2);
                    fab.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

        });


        Sessionmanagement sessionmanagement=new Sessionmanagement(getApplicationContext());
        dept=sessionmanagement.getdeptname();

        /*ApiInteface apiInterface= Api.getClient().create(ApiInteface.class);
        Call<ArrayList<PojoStudent>> call=apiInterface.getStudentdetails(dept);
        call.enqueue(new Callback<ArrayList<PojoStudent>>() {
            private LinearLayoutManager VerticalLinierLayout;
            @Override
            public void onResponse(@NotNull Call<ArrayList<PojoStudent>> call, @NotNull Response<ArrayList<PojoStudent>> response) {
                AddrecycleIst.setHasFixedSize(true);
                listPojoStudent=response.body();
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
                AddrecycleIst.setLayoutManager(linearLayoutManager);

                adapter=new StudentListAdapter(getApplicationContext(),listPojoStudent,i);
                VerticalLinierLayout=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
                AddrecycleIst.setLayoutManager(VerticalLinierLayout);
                AddrecycleIst.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<PojoStudent>> call, @NotNull Throwable t) {

            }
        });*/
        Recycle();
        enableSwipeToDelete();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                Recycle();
            }
        });
    }

    private void enableSwipeToDelete() {
        SwipeToDeleteCallback swipeToDeleteCallback=new SwipeToDeleteCallback(this)
        {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final int position = viewHolder.getAdapterPosition();
                adapter.removeItem(position);
               /* Snackbar snackbar = Snackbar
                        .make(rel1, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

//                        adapter.restoreItem(item, position);
                        AddrecycleIst.scrollToPosition(position);
                    }
                });
*/
//                snackbar.setActionTextColor(Color.YELLOW);
//                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(AddrecycleIst);
    }




    private void Recycle() {
        ApiInteface apiInterface= Api.getClient().create(ApiInteface.class);
        Call<ArrayList<PojoStudent>> call=apiInterface.getStudentdetails(dept);
        call.enqueue(new Callback<ArrayList<PojoStudent>>() {
            private LinearLayoutManager VerticalLinierLayout;
            @Override
            public void onResponse(Call<ArrayList<PojoStudent>> call, Response<ArrayList<PojoStudent>> response) {
                AddrecycleIst.setHasFixedSize(true);
                listPojoStudent=response.body();
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
                AddrecycleIst.setLayoutManager(linearLayoutManager);

                adapter=new StudentListAdapter(getApplicationContext(),listPojoStudent,i);
                VerticalLinierLayout=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
                AddrecycleIst.setLayoutManager(VerticalLinierLayout);
                AddrecycleIst.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ArrayList<PojoStudent>> call, Throwable t) {

            }
        });
    }

    @Override
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
    }

    public void setStatusBarColor(View statusBar,int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //status bar height
            int actionBarHeight = getActionBarHeight();
            int statusBarHeight = getStatusBarHeight();
            //action bar height
            statusBar.getLayoutParams().height = actionBarHeight + statusBarHeight;
            statusBar.setBackgroundColor(color);
        }
    }
    public int getActionBarHeight() {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
