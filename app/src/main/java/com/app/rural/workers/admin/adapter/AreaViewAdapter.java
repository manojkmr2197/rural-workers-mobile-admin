package com.app.rural.workers.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.rural.workers.admin.R;
import com.app.rural.workers.admin.listener.ClickListener;
import com.app.rural.workers.admin.model.AreaModel;
import com.app.rural.workers.admin.view_holder.AreaViewHolder;

import java.util.ArrayList;
import java.util.List;

public class AreaViewAdapter extends RecyclerView.Adapter<AreaViewHolder> {

    Context context;
    List<AreaModel> contentList = new ArrayList<>();
    ClickListener clickListener;

    public AreaViewAdapter(Context context, List<AreaModel> contentList, ClickListener clickListener) {
        this.context = context;
        this.contentList = contentList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AreaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.area_item_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder holder, int position) {
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
