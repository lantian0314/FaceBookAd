package com.example.nativeads;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import com.facebook.ads.*;
import com.facebook.ads.NativeAd.Image;
import com.facebook.ads.NativeAd.Rating;

public class MainActivity extends Activity {

	private NativeAd nativeAd;
	private ImageView nativecoverImage;
	private ImageView nativeIconImage;
	private TextView nativeAdTitle;
	private TextView nativeAdBody;
	private TextView nativeAdSocialContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		nativecoverImage = (ImageView) findViewById(R.id.coverImage);
		// nativeIconImage = (ImageView) findViewById(R.id.nativeAdIcon);
		// nativeAdTitle = (TextView) findViewById(R.id.nativeAdTitle);
		// nativeAdBody = (TextView) findViewById(R.id.nativeAdBody);
		// nativeAdSocialContext = (TextView)
		// findViewById(R.id.nativeAdSocialContext);
		showNativeAd();
	}

	private void showNativeAd() {
		AdSettings.addTestDevice("ddd");
		nativeAd = new NativeAd(this, "1525523984400914_1651354238484554");
		nativeAd.setAdListener(new AdListener() {

			@Override
			public void onError(Ad arg0, AdError arg1) {
				// TODO Auto-generated method stub
				Log.e("facebook", "loadError");
			}

			@Override
			public void onAdLoaded(Ad ad) {
				if (ad != nativeAd) {
					return;
				}
				String titleForAd = nativeAd.getAdTitle();
				Image coverImage = nativeAd.getAdCoverImage();
				Image iconForAd = nativeAd.getAdIcon();
				String socialContextForAd = nativeAd.getAdSocialContext();
				String titleForAdButton = nativeAd.getAdCallToAction();
				String textForAdBody = nativeAd.getAdBody();
				Rating appRatingForAd = nativeAd.getAdStarRating();

//				LinearLayout nativeAdContainer = new LinearLayout(
//						getApplicationContext());
				LinearLayout mainContainer = (LinearLayout) findViewById(R.id.MainContainer);
				TextView titleLabel = new TextView(getApplicationContext());
				titleLabel.setText(titleForAd);
				// nativeAdTitle.setText(titleForAd);
				// nativeAdBody.setText(textForAdBody);
				// nativeAdSocialContext.setText(socialContextForAd);

				TextView AdButtonLabel = new TextView(getApplicationContext());
				AdButtonLabel.setText(titleForAdButton);
				mainContainer.addView(AdButtonLabel);
				mainContainer.addView(titleLabel);
				
//				LinearLayout imagelLayout = new LinearLayout(
//						getApplicationContext());
//				ImageView imageView = new ImageView(getApplicationContext());
//				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//						LinearLayout.LayoutParams.WRAP_CONTENT,
//						LinearLayout.LayoutParams.WRAP_CONTENT);
//				imagelLayout.setLayoutParams(params);
				NativeAd.downloadAndDisplayImage(coverImage, nativecoverImage);
				
				// Load the cover image into an ImageView using an helper
				// function
				
				// NativeAd.downloadAndDisplayImage(iconForAd, nativeIconImage);
				
				//mainContainer.addView(nativeAdContainer);
				nativeAd.registerViewForInteraction(mainContainer);

			}

			@Override
			public void onAdClicked(Ad arg0) {
				// TODO Auto-generated method stub
				Log.e("facebook", "adClicked");
				Toast.makeText(getApplicationContext(), "Ad Click", 1).show();
			}
		});
		// nativeAd.loadAd();
		nativeAd.loadAd(NativeAd.MediaCacheFlag.ALL);

		nativeAd.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}
}
