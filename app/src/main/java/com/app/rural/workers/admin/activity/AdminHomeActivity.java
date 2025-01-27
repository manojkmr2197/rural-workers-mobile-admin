package com.app.rural.workers.admin.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.app.rural.workers.admin.R;
import com.app.rural.workers.admin.utils.Utility;

import com.google.android.material.navigation.NavigationView;

public class AdminHomeActivity extends AppCompatActivity {

    Context rootContext;
    Activity rootActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        rootContext = AdminHomeActivity.this;
        rootActivity = AdminHomeActivity.this;

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.app_color));
        }



        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.home_drawer_layout);
        TextView textView = (TextView) findViewById(R.id.home_nav_text_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i =null;
                mDrawerLayout.closeDrawer(GravityCompat.START);
                switch (item.getItemId()){
                    case R.id.nav_workers:
                        i = new Intent(AdminHomeActivity.this, WorkerListActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                        break;
                    case R.id.nav_users:
                        i = new Intent(AdminHomeActivity.this, UserListActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                        break;
                    case R.id.nav_ad:
                        i = new Intent(AdminHomeActivity.this, AdvertisementActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                        break;
                    case R.id.nav_city:
                        i = new Intent(AdminHomeActivity.this, CityListActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                        break;
                    case R.id.nav_area:
                        i = new Intent(AdminHomeActivity.this, AreaListActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                        break;
                    case R.id.nav_job:
                        i = new Intent(AdminHomeActivity.this, JobListActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                        break;
                    case R.id.nav_proof_type:
                        i = new Intent(AdminHomeActivity.this, ProofTypeActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                        break;
                }
                return true;
            }
        });
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.home_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}