package com.app.rural.workers.admin.view_holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.rural.workers.admin.R;

public class JobViewHolder extends RecyclerView.ViewHolder {

    public Button edit;
    public TextView name_english,name_tamil,status;
    public JobViewHolder(@NonNull View itemView) {
        super(itemView);

        edit =(Button)itemView.findViewById(R.id.job_item_edit);
        name_tamil =(TextView) itemView.findViewById(R.id.job_item_name_tamil);
        name_english =(TextView) itemView.findViewById(R.id.job_item_name_english);
        status =(TextView) itemView.findViewById(R.id.job_item_status);


    }
}
