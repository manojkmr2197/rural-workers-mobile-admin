package com.app.rural.workers.admin.listener;

import com.app.rural.workers.admin.model.CityModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface API {

    @Multipart
    @POST("file/job/image/upload")
    Call<String> updateJobImage(@Part MultipartBody.Part img);

    @GET("admin/city/list")
    Call<List<CityModel>> getCityAllList();
}
