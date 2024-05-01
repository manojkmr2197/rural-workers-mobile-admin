package com.app.rural.workers.admin.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.rural.workers.admin.R;
import com.app.rural.workers.admin.adapter.JobViewAdapter;
import com.app.rural.workers.admin.listener.API;
import com.app.rural.workers.admin.listener.ClickListener;
import com.app.rural.workers.admin.model.JobModel;
import com.app.rural.workers.admin.utils.RetrofitClient;
import com.app.rural.workers.admin.utils.SingleTon;
import com.google.android.gms.ads.AdRequest;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobListActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imagePreview;
    String picturePath;

    API api;

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
        requestPermission();
        checkNetwork();

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
        dialog.setContentView(R.layout.dialog_job_create);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        TextInputEditText englishName = (TextInputEditText) dialog.findViewById(R.id.job_add_english);
        TextInputEditText tamilName = (TextInputEditText) dialog.findViewById(R.id.job_add_tamil);
        MaterialCheckBox enableBox = (MaterialCheckBox) dialog.findViewById(R.id.job_add_checkbox);
        imagePreview = (ImageView) dialog.findViewById(R.id.job_add_imageview);
        Button imageChoose = (Button) dialog.findViewById(R.id.job_add_imagebt);

        Button submit = (Button) dialog.findViewById(R.id.job_add_submit);
        TextView close = (TextView) dialog.findViewById(R.id.job_add_close);
        if(jobModel != null) {
            englishName.setText(jobModel.getNameEnglish());
            tamilName.setText(jobModel.getNameTamil());
        }

        imageChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

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
                    //Toast.makeText(JobListActivity.this, "New city - " + englishName.getText() + "-" + tamilName.getText(), Toast.LENGTH_LONG).show();
                    imageUploadAPI("");
                }
                //dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void selectImage() {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, 100);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

            Log.v("choose file path ", picturePath);

            //File file = new File(picturePath);

            //picNameTv.setText(file.getName());

            imagePreview.setImageBitmap(BitmapFactory.decodeFile(picturePath));

//            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
//            pictureFile = outputStream.toByteArray();

        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(JobListActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            //Toast.makeText(MainHome.this," Phone Call Permission.",Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(JobListActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                break;
        }
    }

    public void checkNetwork() {
        if (!SingleTon.isNetworkConnected(JobListActivity.this)) {
            Toast.makeText(JobListActivity.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
        } else {
            api = new RetrofitClient().getClient().create(API.class);
//            callCategoryAPI();
//            handler.postDelayed(myRunnable = new Runnable() {
//                @Override
//                public void run() {
//                    if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
//                        mInterstitialAd.loadAd(new AdRequest.Builder().build());
//                    }
//                    mRewardedVideoAd.loadAd(getString(R.string.rewarded_video_ad), new AdRequest.Builder().build());
//
//                }
//
//            }, 1000);
        }
    }

    private void imageUploadAPI(String id) {
//        if (progress.getVisibility() == View.GONE) {
//            progress.setVisibility(View.VISIBLE);
//        }
        //pass it like this
        File file = new File(picturePath);

        if (!file.isFile()) {
            Log.v("Image File", "File not Found");
            return;
        }

// add another part within the multipart request
        RequestBody jobId =
                RequestBody.create(MediaType.parse("text/plain"), id);


        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

        Call<String> call = api.updateJobImage(filePart);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //progress.setVisibility(View.GONE);
                System.out.println(response.code());
                if (response != null && response.body() != null) {
                    Toast.makeText(JobListActivity.this, "Success .!", Toast.LENGTH_SHORT).show();

//                    if (response.body().getStatus()) {
//                        showRewardedVideo();
//
//                    } else {
//                        Toast.makeText(rootContext, "Internal Server Error .!", Toast.LENGTH_SHORT).show();
//                    }
                } else {
                    Toast.makeText(JobListActivity.this, "Internal Server Error .!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //progress.setVisibility(View.GONE);
                t.printStackTrace();
                Toast.makeText(JobListActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}