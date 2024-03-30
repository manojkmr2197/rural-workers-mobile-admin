package com.app.rural.workers.admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.rural.workers.admin.R;
import com.app.rural.workers.admin.adapter.AreaViewAdapter;
import com.app.rural.workers.admin.adapter.CityViewAdapter;
import com.app.rural.workers.admin.listener.ClickListener;
import com.app.rural.workers.admin.model.AreaModel;
import com.app.rural.workers.admin.model.CityModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AreaListActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_list);
        Context context = AreaListActivity.this;

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.app_color));
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.area_recyclerView);
        TextView back = (TextView) findViewById(R.id.area_back);
        back.setOnClickListener(this);

        FloatingActionButton add_fab =(FloatingActionButton) findViewById(R.id.area_add_fab);
        add_fab.setOnClickListener(this);

        List<AreaModel> itemList = new ArrayList<>();
        itemList.add(new AreaModel(1,"PLI-1","திருச்சி-1","Y"));
        itemList.add(new AreaModel(2,"PLI-2","திருச்சி-2","Y"));
        itemList.add(new AreaModel(3,"PLI-3","திருச்சி-3","Y"));
        itemList.add(new AreaModel(4,"PLI-4","திருச்சி-4","Y"));

        ClickListener listener = new ClickListener() {
            @Override
            public void click(int index){
                Toast.makeText(context,"clicked item main class "+index, Toast.LENGTH_LONG).show();
                createDialogBox(context,itemList.get(index));
            }
        };

        AreaViewAdapter adapter = new AreaViewAdapter(context,itemList,listener);
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
            case R.id.area_back :
                finish();
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                break;
            case R.id.area_add_fab:
                createDialogBox(AreaListActivity.this,null);
                break;
        }
    }
    private void createDialogBox(Context context,AreaModel areaModel) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialog.setContentView(R.layout.dialog_area_create);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        TextInputEditText englishName = (TextInputEditText) dialog.findViewById(R.id.area_add_english);
        TextInputEditText tamilName = (TextInputEditText) dialog.findViewById(R.id.area_add_tamil);
        Spinner citySpinner = (Spinner) dialog.findViewById(R.id.area_add_spinner);
        Button submit = (Button) dialog.findViewById(R.id.area_add_submit);
        TextView close = (TextView) dialog.findViewById(R.id.area_add_close);
        if (areaModel != null) {
            englishName.setText(areaModel.getNameEnglish());
            tamilName.setText(areaModel.getNameTamil());
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
                if (areaModel != null) {
                    Toast.makeText(AreaListActivity.this, "Update city ID - " + areaModel.getId(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AreaListActivity.this, "New city - " + englishName.getText() + "-" + tamilName.getText(), Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}