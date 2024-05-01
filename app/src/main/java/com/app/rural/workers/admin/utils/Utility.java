package com.app.rural.workers.admin.utils;

import android.content.Context;

import com.app.rural.workers.admin.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class Utility {

    public static void bannerADLoading(AdView adView){
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId(context.getResources().getString(R.string.banner_ad_unit_id));
        adView.loadAd(new AdRequest.Builder().build());
    }

    public static InterstitialAd interstitialAdLoading(Context context) {
        InterstitialAd mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(context.getResources().getString(R.string.interstitial_ad_unit_id));
        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
        return mInterstitialAd;
    }
}
