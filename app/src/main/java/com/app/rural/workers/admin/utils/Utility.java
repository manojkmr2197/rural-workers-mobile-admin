package com.app.rural.workers.admin.utils;

import android.content.Context;

import com.app.rural.workers.admin.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class Utility {

    public static void bannerADLoading(AdView adView){
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId(context.getResources().getString(R.string.banner_ad_unit_id));
        adView.loadAd(new AdRequest.Builder().build());
    }
}
