package com.example.nativeads;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.ads.AdError;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdScrollView;
import com.facebook.ads.NativeAdView;
import com.facebook.ads.NativeAdsManager;
import com.facebook.ads.NativeAdsManager.Listener;

public class Hscroll extends Activity implements Listener {

	private LinearLayout mainLinearLayout;
	private NativeAdsManager manager;
	private NativeAdScrollView scrollView;
	
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainLinearLayout= new LinearLayout(
				getApplicationContext());
		LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		mainLinearLayout.setLayoutDirection(LinearLayout.VERTICAL);
		mainLinearLayout.setLayoutParams(mainParams);
		setContentView(mainLinearLayout);
		
		manager=new NativeAdsManager(getApplicationContext(), "1525523984400914_1651354238484554", 5);
		manager.setListener(this);
		manager.loadAds(NativeAd.MediaCacheFlag.ALL);
	}
	
	@Override
	public void onAdError(AdError error) {
		Toast.makeText(this, "Ad error: " + error.getErrorMessage(), 1).show();
	}
	@Override
	public void onAdsLoaded() {
		Toast.makeText(this, "Ads loaded", Toast.LENGTH_SHORT).show();
		if (scrollView!=null) {
			mainLinearLayout.removeView(scrollView);
		}
		
		scrollView=new NativeAdScrollView(Hscroll.this, manager, NativeAdView.Type.HEIGHT_400);
		mainLinearLayout.addView(scrollView);
	}
}
