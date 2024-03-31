package com.app.rural.workers.admin.view_holder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.rural.workers.admin.R;

public class CityViewHolder extends RecyclerView.ViewHolder {

    public Button edit;
    public TextView name_english,name_tamil,status;
    public CityViewHolder(@NonNull View itemView) {
        super(itemView);

        edit =(Button)itemView.findViewById(R.id.city_item_edit);
        name_tamil =(TextView) itemView.findViewById(R.id.city_item_name_tamil);
        name_english =(TextView) itemView.findViewById(R.id.city_item_name_english);
        status =(TextView) itemView.findViewById(R.id.city_item_status);


    }
}
