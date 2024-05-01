package com.app.rural.workers.admin.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.rural.workers.admin.R;
import com.app.rural.workers.admin.adapter.CityViewAdapter;
import com.app.rural.workers.admin.listener.API;
import com.app.rural.workers.admin.listener.ClickListener;
import com.app.rural.workers.admin.model.CityModel;
import com.app.rural.workers.admin.utils.RetrofitClient;
import com.app.rural.workers.admin.utils.SingleTon;
import com.app.rural.workers.admin.utils.Utility;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityListActivity extends AppCompatActivity implements View.OnClickListener {

    FrameLayout data_fl,no_data_fl;
    Context context;
    Activity activity;

    API api;

    List<CityModel> itemList = new ArrayList<>();

    CityViewAdapter adapter;
    RecyclerView recyclerView;
    ClickListener listener;

    AdView no_data_adView,progress_adView;

    BottomSheetDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        context = CityListActivity.this;
        activity = CityListActivity.this;

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.app_color));
        }

        no_data_fl = (FrameLayout) findViewById(R.id.city_no_data_ll);
        data_fl = (FrameLayout) findViewById(R.id.city_data_ll);

        no_data_adView = (AdView) findViewById(R.id.city_empty_google_adView);

        recyclerView = (RecyclerView) findViewById(R.id.city_recyclerView);
        TextView back = (TextView) findViewById(R.id.city_back);
        back.setOnClickListener(this);

        FloatingActionButton add_fab = (FloatingActionButton) findViewById(R.id.city_add_fab);
        add_fab.setOnClickListener(this);

        listener = new ClickListener() {
            @Override
            public void click(int index) {
                Toast.makeText(context, "clicked item main class " + index, Toast.LENGTH_LONG).show();
                createDialogBox(context, itemList.get(index));
            }
        };
        createProgressDialog();
        checkInternet();


    }

    private void checkInternet() {
        if(SingleTon.isNetworkConnected(activity)){
            no_data_fl.setVisibility(View.GONE);
            data_fl.setVisibility(View.VISIBLE);
            callApiData();
        }else{
            no_data_fl.setVisibility(View.VISIBLE);
            data_fl.setVisibility(View.GONE);
            createNoInternetDialog();
        }

    }

    private void callApiData() {
        api = new RetrofitClient().getClient().create(API.class);

        Call<List<CityModel>> call = api.getCityAllList();

        progressDialog.show();
        Utility.bannerADLoading(progress_adView);

        call.enqueue(new Callback<List<CityModel>>() {
            @Override
            public void onResponse(Call<List<CityModel>> call, Response<List<CityModel>> response) {
                progressDialog.dismiss();
                try {
                    itemList.clear();
                    itemList = response.body();
                    if (itemList.isEmpty()) {
                        data_fl.setVisibility(View.GONE);
                        no_data_fl.setVisibility(View.VISIBLE);
                        Utility.bannerADLoading(no_data_adView);
                    } else {
                        data_fl.setVisibility(View.VISIBLE);
                        no_data_fl.setVisibility(View.GONE);
                        adapter = new CityViewAdapter(context, itemList, listener);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<CityModel>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CityListActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                data_fl.setVisibility(View.GONE);
                no_data_fl.setVisibility(View.VISIBLE);
            }
        });
    }

    private void createProgressDialog() {
        progressDialog = new BottomSheetDialog(context);
        progressDialog.setContentView(R.layout.dialog_progress_view);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        progress_adView = (AdView) progressDialog.findViewById(R.id.progress_google_adView);

    }

    private void createNoInternetDialog() {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialog.setContentView(R.layout.dialog_no_internet_view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        TextView retry = (TextView) dialog.findViewById(R.id.no_internet_retry);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInternet();
                dialog.dismiss();
            }
        });
        dialog.show();
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
            englishName.setText(cityModel.getCityNameEnglish());
            tamilName.setText(cityModel.getCityNameTamil());
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
                    Toast.makeText(CityListActivity.this, "Update city ID - " + cityModel.getCityId(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CityListActivity.this, "New city - " + englishName.getText() + "-" + tamilName.getText(), Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}