package com.app.rural.workers.admin.activity;

import android.content.Context;
import android.content.Intent;
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
import com.app.rural.workers.admin.adapter.CityViewAdapter;
import com.app.rural.workers.admin.listener.ClickListener;
import com.app.rural.workers.admin.model.CityModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class CityListActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        Context context = CityListActivity.this;

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.app_color));
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.city_recyclerView);
        TextView back = (TextView) findViewById(R.id.city_back);
        back.setOnClickListener(this);

        FloatingActionButton add_fab = (FloatingActionButton) findViewById(R.id.city_add_fab);
        add_fab.setOnClickListener(this);

        List<CityModel> itemList = new ArrayList<>();
        itemList.add(new CityModel(1, "Trichy1", "திருச்சி", "Y"));
        itemList.add(new CityModel(2, "Trichy2", "திருச்சி", "Y"));
        itemList.add(new CityModel(3, "Trichy3", "திருச்சி", "N"));
        itemList.add(new CityModel(4, "Trichy4", "திருச்சி", "Y"));

        ClickListener listener = new ClickListener() {
            @Override
            public void click(int index) {
                Toast.makeText(context, "clicked item main class " + index, Toast.LENGTH_LONG).show();
                createDialogBox(context, itemList.get(index));
            }
        };

        CityViewAdapter adapter = new CityViewAdapter(context, itemList, listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.city_back:
                finish();
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                break;
            case R.id.city_add_fab:
                createDialogBox(CityListActivity.this, null);
                break;
        }
    }

    private void createDialogBox(Context context, CityModel cityModel) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialog.setContentView(R.layout.dialog_city_create);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        TextInputEditText englishName = (TextInputEditText) dialog.findViewById(R.id.city_add_english);
        TextInputEditText tamilName = (TextInputEditText) dialog.findViewById(R.id.city_add_tamil);
        Button submit = (Button) dialog.findViewById(R.id.city_add_submit);
        TextView close = (TextView) dialog.findViewById(R.id.city_add_close);
        if (cityModel != null) {
            englishName.setText(cityModel.getNameEnglish());
            tamilName.setText(cityModel.getNameTamil());
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
                if (cityModel != null) {
                    Toast.makeText(CityListActivity.this, "Update city ID - " + cityModel.getId(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CityListActivity.this, "New city - " + englishName.getText() + "-" + tamilName.getText(), Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}