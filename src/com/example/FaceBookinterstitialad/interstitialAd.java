package com.example.FaceBookinterstitialad;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.nativeads.R;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

public class interstitialAd extends Activity implements InterstitialAdListener{

	private InterstitialAd interstitialAd;
	private int try_time=0;
	private int max_time=6;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		loadInterstitialAd();
	}
	
	private void loadInterstitialAd(){
	    interstitialAd = new InterstitialAd(this, "1525523984400914_1651354238484554");
	    //interstitialAd.setAdListener(interstitialAd.this);
	    if (interstitialAd.isAdLoaded()) {
	    	interstitialAd.show();
		}else {
			tryShowInterstitialAd();
		}
	    interstitialAd.loadAd();
	    
	}

	private void tryShowInterstitialAd() {
		try_time++;
		if (try_time<=max_time) {
			mHandler.sendEmptyMessageDelayed(MSG_SHOWASD, 10*1000);
		}	
	}

	@Override
	public void onAdClicked(Ad arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdLoaded(Ad arg0) {
		interstitialAd.show();
	}

	@Override
	public void onError(Ad arg0, AdError arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInterstitialDismissed(Ad arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInterstitialDisplayed(Ad arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onDestroy() {
		if (interstitialAd!=null) {
			interstitialAd.destroy();
		}
		super.onDestroy();
	}
	
	private final int MSG_SHOWASD = 20;
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_SHOWASD:
				 if (interstitialAd.isAdLoaded()) {
				    	interstitialAd.show();
					}else {
						tryShowInterstitialAd();
					}
				break;
			}
		}
	};
}
