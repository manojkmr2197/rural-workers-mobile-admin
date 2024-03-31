package com.app.rural.workers.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.rural.workers.admin.R;
import com.app.rural.workers.admin.listener.ClickListener;
import com.app.rural.workers.admin.model.CityModel;
import com.app.rural.workers.admin.model.JobModel;
import com.app.rural.workers.admin.view_holder.CityViewHolder;
import com.app.rural.workers.admin.view_holder.JobViewHolder;

import java.util.ArrayList;
import java.util.List;

public class JobViewAdapter extends RecyclerView.Adapter<JobViewHolder> {

    Context context;
    List<JobModel> contentList = new ArrayList<>();
    ClickListener clickListener;

    public JobViewAdapter(Context context, List<JobModel> contentList, ClickListener clickListener) {
        this.context = context;
        this.contentList = contentList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JobViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        int index = holder.getAdapterPosition();

        holder.name_tamil.setText(contentList.get(index).getNameTamil());
        holder.name_english.setText(contentList.get(index).getNameEnglish());
        if("Y".equals(contentList.get(index).getStatus())){
            holder.status.setBackgroundResource(R.drawable.ic_baseline_lens_active);
        }else{
            holder.status.setBackgroundResource(R.drawable.ic_baseline_lens_not_active);
        }
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.click(index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
