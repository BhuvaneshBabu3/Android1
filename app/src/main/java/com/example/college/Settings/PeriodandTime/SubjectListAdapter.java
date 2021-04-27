package com.example.college.Settings.PeriodandTime;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.college.PojoStudent;
import com.example.college.R;
import com.example.college.Settings.Edit;
import com.example.college.StudentListAdapter;
import com.example.college.SubjectDetails;

import java.util.ArrayList;




public class SubjectListAdapter extends RecyclerView.Adapter<SubjectListAdapter.ViewHolder> implements Filterable {
    Context context;
    ArrayList<SubjectDetails> subjectDetailsArrayList;
    ArrayList<SubjectDetails> backup;
    String querytext="";

    public SubjectListAdapter(Context context, ArrayList<SubjectDetails> subjectDetailsArrayList) {
        this.context = context;
        this.subjectDetailsArrayList = subjectDetailsArrayList;
        backup=new ArrayList<>(subjectDetailsArrayList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.subjectdata,null);
        SubjectListAdapter.ViewHolder userViewHolder=new SubjectListAdapter.ViewHolder(view);
        return userViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.subjectname.setText(subjectDetailsArrayList.get(position).getSubjectname());
        holder.subjectId.setText(subjectDetailsArrayList.get(position).getId()+"  ");
        holder.subjectdept.setText(subjectDetailsArrayList.get(position).getDept());
        holder.subjectregulation.setText(subjectDetailsArrayList.get(position).getRegulation());
        holder.Subjectcode.setText(subjectDetailsArrayList.get(position).getSubjectcode());

        holder.Subjectcode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });

//        holder.lecturebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(v.getContext(), Edit.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//                context.getApplicationContext().startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return subjectDetailsArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<SubjectDetails> filtereddata=new ArrayList<>();
            if(constraint.toString().isEmpty())
                filtereddata.addAll(backup);
            else
            {
                querytext=constraint.toString();
                for(SubjectDetails obj : backup) {
                    if (obj.Subjectname.toLowerCase().contains(constraint.toString().toLowerCase())
                        || obj.getSubjectcode().toLowerCase().contains(constraint.toString().toLowerCase()))
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
            subjectDetailsArrayList.clear();
            subjectDetailsArrayList.addAll((ArrayList<SubjectDetails>)results.values);
            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView subjectId,subjectname,Subjectcode,subjectdept,subjectregulation;
        EditText edit_Subjectcode;
//        Button lecturebtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectname=(TextView) itemView.findViewById(R.id.subjectname);
            subjectId=(TextView) itemView.findViewById(R.id.subjectId);
            Subjectcode=(TextView) itemView.findViewById(R.id.Subjectcode);
            subjectdept=(TextView) itemView.findViewById(R.id.subjectdept);
            subjectregulation=(TextView) itemView.findViewById(R.id.subjectregulation);
//            lecturebtn=(Button) itemView.findViewById(R.id.lecturebtn);
        }
    }
}
