package com.app.rural.workers.admin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.rural.workers.admin.R;

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
    }

    @Override
    public void onBackPressed()
    {
        finish();
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
    }
}