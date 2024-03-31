package com.app.rural.workers.admin.view_holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.rural.workers.admin.R;

public class ProofTypeViewHolder extends RecyclerView.ViewHolder {

    public Button edit;
    public TextView name_english;

    public ProofTypeViewHolder(@NonNull View itemView) {
        super(itemView);

        edit =(Button)itemView.findViewById(R.id.proof_type_item_edit);
        name_english =(TextView) itemView.findViewById(R.id.proof_type_item_name);

    }
}
