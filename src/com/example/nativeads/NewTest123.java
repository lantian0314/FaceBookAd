package com.example.nativeads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdListener;
import com.facebook.ads.NativeAd.Image;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NewTest123 extends Activity {

	private static final int R_ICON_ID = 1;
	private static final int R_ICONANDBODY_ID = 2;
	private static final int R_COVER_ID = 3;
	private static final int R_SOCIAL_ID = 4;
	private static final int R_MAIN_ID = 5;
	private ImageView coverImageView;
	private RelativeLayout MainLayout;
	private ImageView iconImage;
	private TextView bodyText;
	private TextView socialContext;
	private Button adButton;
	private Handler handler;
	private RelativeLayout TopLayout;
	private Object nativeAdObject = null;
	private Class<?> nativeAdClass = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainLayout = new RelativeLayout(getApplicationContext());
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		MainLayout.setLayoutParams(params);

		TopLayout = new RelativeLayout(getApplicationContext());
		TopLayout.setId(R_MAIN_ID);
		TopLayout.setBackgroundColor(Color.GREEN);
		RelativeLayout.LayoutParams topParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		TopLayout.setLayoutParams(topParams);
		MainLayout.addView(TopLayout);
		getIconAndBody();

		getCoverImage();

		getSocialContext();

		getAdButton();

		getNextButton();

		setContentView(MainLayout);

		showNativeAd();

	}

	private void getIconAndBody() {
		RelativeLayout iconLayout = new RelativeLayout(getApplicationContext());
		RelativeLayout.LayoutParams totalParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		iconLayout.setId(R_ICONANDBODY_ID);
		iconLayout.setLayoutParams(totalParams);

		iconImage = new ImageView(getApplicationContext());
		RelativeLayout.LayoutParams iconParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		iconParams.addRule(RelativeLayout.CENTER_VERTICAL);
		iconImage.setId(R_ICON_ID);
		iconImage.setLayoutParams(iconParams);

		bodyText = new TextView(getApplicationContext());
		bodyText.setTextColor(Color.BLACK);
		RelativeLayout.LayoutParams bodyParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		bodyParams.addRule(RelativeLayout.RIGHT_OF, R_ICON_ID);
		bodyParams.addRule(RelativeLayout.ALIGN_TOP, R_ICON_ID);
		bodyParams.addRule(RelativeLayout.CENTER_VERTICAL);
		bodyText.setLayoutParams(bodyParams);

		iconLayout.addView(iconImage);
		iconLayout.addView(bodyText);
		TopLayout.addView(iconLayout);

	}

	private void getCoverImage() {
		coverImageView = new ImageView(getApplicationContext());
		coverImageView.setId(R_COVER_ID);
		RelativeLayout.LayoutParams coverParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		coverParams.addRule(RelativeLayout.BELOW, R_ICONANDBODY_ID);
		coverParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		coverImageView.setLayoutParams(coverParams);
		TopLayout.addView(coverImageView);
	}

	private void getSocialContext() {
		int[] size = getscreenDisplay();
		socialContext = new TextView(getApplicationContext());
		socialContext.setId(R_SOCIAL_ID);
		socialContext.setTextColor(Color.BLACK);
		RelativeLayout.LayoutParams socialParams = new RelativeLayout.LayoutParams(
				(int) (size[0] * 0.5), RelativeLayout.LayoutParams.WRAP_CONTENT);
		socialParams.addRule(RelativeLayout.BELOW, R_COVER_ID);
		socialParams.addRule(RelativeLayout.ALIGN_LEFT);
		socialContext.setLayoutParams(socialParams);
		TopLayout.addView(socialContext);
	}

	private void getAdButton() {
		adButton = new Button(getApplicationContext());
		adButton.setVisibility(View.INVISIBLE);
		RelativeLayout.LayoutParams socialParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		socialParams.addRule(RelativeLayout.BELOW, R_COVER_ID);
		socialParams.addRule(RelativeLayout.RIGHT_OF, R_SOCIAL_ID);
		adButton.setLayoutParams(socialParams);
		TopLayout.addView(adButton);
	}

	private void getNextButton() {
		RelativeLayout nextLayout = new RelativeLayout(getApplicationContext());
		RelativeLayout.LayoutParams nextButtonParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		nextButtonParams.addRule(RelativeLayout.BELOW, R_MAIN_ID);
		nextLayout.setLayoutParams(nextButtonParams);

		Button nextButton = new Button(this);
		nextButton.setText("Hscroll");
		RelativeLayout.LayoutParams nextParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		nextParams.addRule(RelativeLayout.BELOW, R_MAIN_ID);
		nextButton.setLayoutParams(nextParams);
		nextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(),
						Hscroll.class);
				startActivity(intent);

			}
		});
		nextLayout.addView(nextButton);
		MainLayout.addView(nextLayout);
	}

	private void showNativeAd() {
		AdSettings.addTestDevice("ddd");
		try {
			nativeAdClass = Class.forName("com.facebook.ads.NativeAd");

			Class<?>[] classes = new Class<?>[] { Context.class, String.class };
			Object[] params = new Object[] { getApplicationContext(),
					"1525523984400914_1651354238484554" };
			nativeAdObject = MyReflection.getInstance(
					"com.facebook.ads.NativeAd", classes, params);

			Object adListenerObject = MyReflection.getInterfaceProxy(
					"com.facebook.ads.AdListener",
					new MyReflection.ProxyListener() {
						@SuppressLint("NewApi")
						@Override
						public void onRun(String methodName, Object[] args,
								Class<?>[] argsCls) {

							if (methodName.equals("onAdLoaded")) {
								try {
									Image coverImage = (Image) getAdObject(
											nativeAdClass, "getAdCoverImage",
											nativeAdObject);
									Image iconForAd = (Image) getAdObject(
											nativeAdClass, "getAdIcon",
											nativeAdObject);
									String titleForAd = (String) getAdObject(
											nativeAdClass, "getAdTitle",
											nativeAdObject);
									String textForAdBody = (String) getAdObject(
											nativeAdClass, "getAdBody",
											nativeAdObject);
									String socialContextForAd = (String) getAdObject(
											nativeAdClass, "getAdSocialContext",
											nativeAdObject);
									String titleForAdButton = (String) getAdObject(
											nativeAdClass, "getAdCallToAction",
											nativeAdObject);

									bodyText.setText(textForAdBody);
									socialContext.setText(socialContextForAd);
									adButton.setText(titleForAdButton);
									adButton.setVisibility(View.VISIBLE);
									
									Object[] iconObjects = { iconForAd, iconImage};
									MyReflection.invokeStaticMethod(
											nativeAdObject,
											"downloadAndDisplayImage", iconObjects);
									
									Object[] coverObjects = { coverImage, coverImageView };
									MyReflection.invokeStaticMethod(
											nativeAdObject,
											"downloadAndDisplayImage", coverObjects);

									nativeAdClass.getMethod(
											"registerViewForInteraction",
											View.class).invoke(nativeAdObject,
											MainLayout);

								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					});
					
				Class<?> adlClass = Class
						.forName("com.facebook.ads.AdListener");
				
				MyReflection.invokeMethod(nativeAdObject, "setAdListener",
						new Class<?>[] { adlClass },
						new Object[] { adListenerObject });
				
				nativeAdClass.getMethod("loadAd").invoke(nativeAdObject);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Object getAdObject(Class<?> cls, String methodName, Object adObject) {
		Method method;
		try {
			method = cls.getMethod(methodName);
			return method.invoke(adObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private int[] getscreenDisplay() {
		DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
		int display[] = { displaymetrics.widthPixels,
				displaymetrics.heightPixels };
		return display;
	}
}
