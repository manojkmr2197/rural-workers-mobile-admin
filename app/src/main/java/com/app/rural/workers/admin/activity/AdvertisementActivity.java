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
import android.widget.TextView;
import android.widget.Toast;

import com.app.rural.workers.admin.R;
import com.app.rural.workers.admin.adapter.AdViewAdapater;
import com.app.rural.workers.admin.listener.ClickListener;
import com.app.rural.workers.admin.model.AdModel;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        Context context = AdvertisementActivity.this;

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.app_color));
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ad_recyclerView);
        TextView back = (TextView) findViewById(R.id.ad_back);
        back.setOnClickListener(this);

        List<AdModel> itemList = new ArrayList<>();
        itemList.add(new AdModel(1,"https://media.geeksforgeeks.org/wp-content/uploads/20210101144014/gfglogo.png"));
        itemList.add(new AdModel(2,"https://media.geeksforgeeks.org/wp-content/uploads/20210101144014/gfglogo.png"));
        itemList.add(new AdModel(3,"https://media.geeksforgeeks.org/wp-content/uploads/20210101144014/gfglogo.png"));
        itemList.add(new AdModel(4,"https://media.geeksforgeeks.org/wp-content/uploads/20210101144014/gfglogo.png"));
        itemList.add(new AdModel(5,"https://media.geeksforgeeks.org/wp-content/uploads/20210101144014/gfglogo.png"));
        itemList.add(new AdModel(6,"https://media.geeksforgeeks.org/wp-content/uploads/20210101144014/gfglogo.png"));

        ClickListener listener = new ClickListener() {
            @Override
            public void click(int index){
                Toast.makeText(context,"clicked item main class "+index, Toast.LENGTH_LONG).show();
            }
        };

        AdViewAdapater adapter = new AdViewAdapater(context,itemList,listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ad_back :
                finish();
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                break;
        }
    }
}