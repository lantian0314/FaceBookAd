package com.example.FaceBookBannerAd;

import com.example.nativeads.R;
import com.facebook.ads.*;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class BannerAd extends Activity {

	private AdView adView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		adView = new AdView(this, "1525523984400914_1651354238484554",
				AdSize.BANNER_HEIGHT_50);
		// ‹÷⁄Õ¯¬Á320*250
		/*adView = new AdView(this, "1525523984400914_1651354238484554",
				AdSize.RECTANGLE_HEIGHT_250);*/
		/*adView = new AdView(this, "1525523984400914_1651354238484554",
				AdSize.BANNER_320_50);*/
		LinearLayout layout=(LinearLayout) findViewById(R.id.MainContainer);
		layout.addView(adView);
		adView.setAdListener(new AdListener() {
			
			@Override
			public void onError(Ad arg0, AdError arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAdLoaded(Ad arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAdClicked(Ad arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		adView.loadAd();
	}
	@Override
	protected void onDestroy() {
		adView.destroy();
		super.onDestroy();
	}
}
