package com.app.rural.workers.admin.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.rural.workers.admin.R;
import com.app.rural.workers.admin.listener.ClickListener;
import com.app.rural.workers.admin.model.AdModel;
import com.app.rural.workers.admin.view_holder.AdViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdViewAdapter extends RecyclerView.Adapter<AdViewHolder> {

    Context context;
    List<AdModel> contentList = new ArrayList<>();
    ClickListener clickListener;

    public AdViewAdapter(Context context, List<AdModel> contentList, ClickListener clickListener) {
        this.contentList = contentList;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public AdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_item_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdViewHolder holder, int position) {
        int index = holder.getAdapterPosition();

        Picasso.get().load(contentList.get(index).getFileName()).into(holder.image);
        holder.button.setOnClickListener(new View.OnClickListener() {
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
