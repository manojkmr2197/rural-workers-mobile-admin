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
import com.app.rural.workers.admin.view_holder.CityViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CityViewAdapter extends RecyclerView.Adapter<CityViewHolder> {

    Context context;
    List<CityModel> contentList = new ArrayList<>();
    ClickListener clickListener;

    public CityViewAdapter(Context context, List<CityModel> contentList, ClickListener clickListener) {
        this.context = context;
        this.contentList = contentList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CityViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
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
