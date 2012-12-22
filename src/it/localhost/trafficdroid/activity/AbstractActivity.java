package it.localhost.trafficdroid.activity;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.google.analytics.tracking.android.EasyTracker;

public abstract class AbstractActivity extends Activity {
	public static final String KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgg7ckVCx3779Q4Dq99wMFYlwS4+jmbrTtBjLzG2cL4xoz6pZhLct4vIL2sfhA588Vfp4vRDHaIN3lpiCOGIxWRxI3krOoF+n1G/F9kUdiGaK4hYMPYa41MPbG6wc9tWJgcGe0PdYExCmeIvFiQrc4HU63J9zN+C1HRqw1t91YC2vzyZFxNLoIp3kcoz6rBCopm4GA01ZPZrP5RTR2hiJLrDVJl5mzuDrl7yoMq6OQ1SasVaWkgN7yTDyh9Df9hv5FsE8haVFddSJfTEh4BFZcFSW+17xgeImNtCgDtQ/GuTG3FIOiOotugIa1OjKC4z5zbZFl8Zz+cz8fFOxzfLkTQIDAQAB";
	public static final String SKU_AD_FREE = "ad_free";
	public static final List<String> additionalSkuList = Arrays.asList(new String[] { SKU_AD_FREE });
	public static final String eventCatWebcam = "Webcam";
	public static final String eventCatBadNews = "BadNews";
	public static final String eventCatIab = "InAppBilling";
	public static final String eventActionRequest = "Request";
	public static final String eventActionOpen = "Open";
	public static final String eventActionNone = "None";
	public static final String eventActionLaunchPurchaseFlow = "LaunchPurchaseFlow";
	public static final String eventActionOnIabPurchaseFinished = "OnIabPurchaseFinished";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance().activityStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance().activityStop(this);
	}
}