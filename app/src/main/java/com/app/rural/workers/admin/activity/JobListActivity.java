package com.app.rural.workers.admin.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.rural.workers.admin.R;
import com.app.rural.workers.admin.adapter.JobViewAdapter;
import com.app.rural.workers.admin.listener.ClickListener;
import com.app.rural.workers.admin.model.JobModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class JobListActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        Context context = JobListActivity.this;

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.app_color));
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.job_recyclerView);
        TextView back = (TextView) findViewById(R.id.job_back);
        back.setOnClickListener(this);

        FloatingActionButton add_fab =(FloatingActionButton) findViewById(R.id.job_add_fab);
        add_fab.setOnClickListener(this);

        List<JobModel> itemList = new ArrayList<>();
        itemList.add(new JobModel(1,"Painter","திருச்சி-Painter","Y"));
        itemList.add(new JobModel(2,"Painter","திருச்சி-Painter","Y"));
        itemList.add(new JobModel(3,"Painter","திருச்சி-Painter","Y"));
        itemList.add(new JobModel(4,"Painter","திருச்சி-Painter","Y"));
        itemList.add(new JobModel(5,"Painter","திருச்சி-Painter","Y"));
        itemList.add(new JobModel(6,"Painter","திருச்சி-Painter","Y"));
        itemList.add(new JobModel(7,"Painter","திருச்சி-Painter","Y"));
        itemList.add(new JobModel(8,"Painter","திருச்சி-Painter","Y"));


        ClickListener listener = new ClickListener() {
            @Override
            public void click(int index){
                Toast.makeText(context,"clicked item main class "+index, Toast.LENGTH_LONG).show();
                createDialogBox(JobListActivity.this,itemList.get(index));
            }
        };

        JobViewAdapter adapter = new JobViewAdapter(context,itemList,listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }

    @Override
    public void onBackPressed()
    {
        finish();
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.job_back :
                finish();
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                break;
            case R.id.job_add_fab:
                createDialogBox(JobListActivity.this,null);
                break;
        }
    }

    private void createDialogBox(Context context,JobModel jobModel) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialog.setContentView(R.layout.dialog_city_create);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        TextInputEditText englishName = (TextInputEditText) dialog.findViewById(R.id.city_add_english);
        TextInputEditText tamilName = (TextInputEditText) dialog.findViewById(R.id.city_add_tamil);
        Button submit = (Button) dialog.findViewById(R.id.city_add_submit);
        TextView close = (TextView) dialog.findViewById(R.id.city_add_close);
        if(jobModel != null) {
            englishName.setText(jobModel.getNameEnglish());
            tamilName.setText(jobModel.getNameTamil());
        }
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jobModel != null) {
                    Toast.makeText(JobListActivity.this, "Update city ID - " + jobModel.getId(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(JobListActivity.this, "New city - " + englishName.getText() + "-" + tamilName.getText(), Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}