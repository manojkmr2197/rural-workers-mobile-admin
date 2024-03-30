package com.app.rural.workers.admin.view_holder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.rural.workers.admin.R;

public class AdViewHolder extends RecyclerView.ViewHolder {

    public Button button;
    public ImageView image;
    public AdViewHolder(@NonNull View itemView) {
        super(itemView);

        button =(Button)itemView.findViewById(R.id.ad_edit);
        image =(ImageView) itemView.findViewById(R.id.ad_image_view);

    }
}
