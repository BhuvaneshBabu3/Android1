package com.example.college;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.college.Settings.Edit;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> implements Filterable {
    Context context;
    ArrayList<PojoStudent> listPojoStudent;
    ArrayList<PojoStudent> backup;
    String querytext="";
    String rollnum;
    String posiition;
    int i;
    public StudentListAdapter(Context context, ArrayList<PojoStudent> listPojoStudent, int i)
    {
        this.context=context;
        this.listPojoStudent=listPojoStudent;
        backup=new ArrayList<>(listPojoStudent);
        this.i=i;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.student_list,null);
        ViewHolder userViewHolder=new ViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String dataText=listPojoStudent.get(position).getStudentname().toString();
        Random rnd = new Random();

        if (querytext!=null && !querytext.isEmpty())
        {
            int startPos = dataText.toLowerCase().indexOf(querytext.toLowerCase());
            int endPos = startPos+querytext.length();
            if(startPos!=-1)
            {
                Spannable spannable = new SpannableString(dataText);
                ColorStateList colorStateList = new ColorStateList(new int [][]{new int []{}},new int []{Color.BLACK});
                ColorStateList colorStateList1 = new ColorStateList(new int [][]{new int []{}},new int []{Color.RED});
                TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(null,Typeface.NORMAL,-1,colorStateList,colorStateList1   );
                spannable.setSpan(textAppearanceSpan,startPos,endPos,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.namestudent.setText(spannable);
                holder.phonestudent.setText(listPojoStudent.get(position).getPhone());
                holder.rollnumstudent.setText(listPojoStudent.get(position).getRollnum());
                holder.Sectionstudent.setText(listPojoStudent.get(position).getSec());
                holder.yearstudent.setText(listPojoStudent.get(position).getYear());
               /* int currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                holder.lin1.setBackgroundColor(currentColor);*/
                /*holder.stdedit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(v.getContext(), Edit.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        context.getApplicationContext().startActivity(i);
                    }
                });*/
            }
            else
            {
                holder.namestudent.setText(listPojoStudent.get(position).getStudentname());
                holder.phonestudent.setText(listPojoStudent.get(position).getPhone());
                holder.rollnumstudent.setText(listPojoStudent.get(position).getRollnum());
                holder.Sectionstudent.setText(listPojoStudent.get(position).getSec());
                holder.yearstudent.setText(listPojoStudent.get(position).getYear());
                /*holder.stdedit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(v.getContext(), Edit.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        context.getApplicationContext().startActivity(i);
                    }
                });*/
            }
        }
        else
        {
            holder.namestudent.setText(listPojoStudent.get(position).getStudentname());
            holder.phonestudent.setText(listPojoStudent.get(position).getPhone());
            holder.rollnumstudent.setText(listPojoStudent.get(position).getRollnum());
            holder.Sectionstudent.setText(listPojoStudent.get(position).getSec());
            holder.yearstudent.setText(listPojoStudent.get(position).getYear());
            /*holder.stdedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(v.getContext(), Edit.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    context.getApplicationContext().startActivity(i);

                }
            });*/
        }
    }


    @Override
    public int getItemCount() {
        return listPojoStudent.size();
    }

    public void removeItem(int position)
    {
        rollnum=listPojoStudent.get(position).getRollnum();
        this.posiition= String.valueOf(position+1);
        Toast.makeText(context.getApplicationContext(),rollnum+"  "+position,Toast.LENGTH_LONG).show();
        ApiInteface apiInterface= Api.getClient().create(ApiInteface.class);
        PojoStudent pojoStudent=new PojoStudent(rollnum);
        Call<PojoStudent> call=apiInterface.deleteStudent(pojoStudent);
        call.enqueue(new Callback<PojoStudent>() {
            @Override
            public void onResponse(Call<PojoStudent> call, Response<PojoStudent> response) {

            }

            @Override
            public void onFailure(Call<PojoStudent> call, Throwable t) {

            }
        });
        listPojoStudent.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
           ArrayList<PojoStudent> filtereddata=new ArrayList<>();
            if(constraint.toString().isEmpty())
                filtereddata.addAll(backup);
            else
            {
                querytext=constraint.toString();
                for(PojoStudent obj : backup) {
                    if (obj.getStudentname().toLowerCase().contains(constraint.toString().toLowerCase()))
                    {
                        filtereddata.add(obj);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filtereddata;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listPojoStudent.clear();
            listPojoStudent.addAll((ArrayList<PojoStudent>)results.values);
            notifyDataSetChanged();
        }
    };



    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView namestudent,rollnumstudent,phonestudent,emailstudent,yearstudent,Sectionstudent;
        ImageView stdedit,stddelete;
        RelativeLayout lin1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.namestudent=(TextView)itemView.findViewById(R.id.namestudent);
            this.rollnumstudent=(TextView)itemView.findViewById(R.id.rollnumstudent);
            this.phonestudent=(TextView)itemView.findViewById(R.id.phonestudent);
            this.yearstudent=(TextView)itemView.findViewById(R.id.yearstudent);
            this.Sectionstudent=(TextView)itemView.findViewById(R.id.Sectionstudent);
            this.lin1=(RelativeLayout)itemView.findViewById(R.id.rel1);
//            this.stdedit=(ImageView) itemView.findViewById(R.id.stdedit);
//            this.stddelete=(ImageView)itemView.findViewById(R.id.stddelete);
        }
    }
}
