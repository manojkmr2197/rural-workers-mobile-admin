package com.app.rural.workers.admin.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

public class SingleTon {

    private static SingleTon singleTon;

    private SingleTon() {

    }

    public static final SingleTon getInstance() {

        if (singleTon == null) {
            singleTon = new SingleTon();
        }
        return singleTon;
    }

    public static String FCM_TOKEN = "fcm_token";
    public static String SHARED_PREF = "shared_pref";

    public static final boolean isNetworkConnected(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
