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
		LinearLayout layout=(LinearLayout) findViewById(R.id.MainContainer);
		layout.addView(adView);
		adView.loadAd();
	}
}
