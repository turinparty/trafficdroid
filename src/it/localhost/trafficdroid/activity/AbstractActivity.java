package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.billing.IabHelper;
import it.localhost.trafficdroid.common.billing.IabHelper.OnIabPurchaseFinishedListener;
import it.localhost.trafficdroid.common.billing.IabHelper.OnIabSetupFinishedListener;
import it.localhost.trafficdroid.common.billing.IabHelper.QueryInventoryFinishedListener;
import it.localhost.trafficdroid.common.billing.IabResult;
import it.localhost.trafficdroid.common.billing.Inventory;
import it.localhost.trafficdroid.common.billing.Purchase;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.google.analytics.tracking.android.EasyTracker;

public abstract class AbstractActivity extends Activity implements QueryInventoryFinishedListener, OnIabPurchaseFinishedListener, OnIabSetupFinishedListener {
	public static final String KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgg7ckVCx3779Q4Dq99wMFYlwS4+jmbrTtBjLzG2cL4xoz6pZhLct4vIL2sfhA588Vfp4vRDHaIN3lpiCOGIxWRxI3krOoF+n1G/F9kUdiGaK4hYMPYa41MPbG6wc9tWJgcGe0PdYExCmeIvFiQrc4HU63J9zN+C1HRqw1t91YC2vzyZFxNLoIp3kcoz6rBCopm4GA01ZPZrP5RTR2hiJLrDVJl5mzuDrl7yoMq6OQ1SasVaWkgN7yTDyh9Df9hv5FsE8haVFddSJfTEh4BFZcFSW+17xgeImNtCgDtQ/GuTG3FIOiOotugIa1OjKC4z5zbZFl8Zz+cz8fFOxzfLkTQIDAQAB";
	public static final String SKU_AD_FREE = "ad_free";
	public static final String SKU_QUIZ_FREE = "quiz_free";
	public static final List<String> additionalSkuList = Arrays.asList(new String[] { SKU_AD_FREE, SKU_QUIZ_FREE });
	public static final String eventCatWebcam = "Webcam";
	public static final String eventCatBadNews = "BadNews";
	public static final String eventCatIab = "InAppBilling";
	public static final String eventCatGraph = "Graph";
	public static final String eventActionRequest = "Request";
	public static final String eventActionOpen = "Open";
	public static final String eventActionNone = "None";
	public static final String eventActionLaunchPurchaseFlow = "LaunchPurchaseFlow";
	public static final String eventActionOnIabPurchaseFinished = "OnIabPurchaseFinished";
	private IabHelper mHelper;
	private boolean adFree;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHelper = new IabHelper(this, KEY);
		mHelper.startSetup(this);
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

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mHelper != null)
			mHelper.dispose();
		mHelper = null;
	}

	@Override
	public void onIabSetupFinished(IabResult result) {
		if (result.isSuccess())
			mHelper.queryInventoryAsync(true, additionalSkuList, this);
	}

	@Override
	public void onQueryInventoryFinished(IabResult result, Inventory inv) {
		if (result.isSuccess() && inv.hasPurchase(SKU_AD_FREE)) {
			adFree = true;
			findViewById(R.id.ad).setVisibility(View.GONE);
			invalidateOptionsMenu();
		}
	}

	@Override
	public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
		EasyTracker.getTracker().sendEvent(eventCatIab, eventActionOnIabPurchaseFinished, result.getMessage(), (long) result.getResponse());
		if (result.isSuccess() && purchase.getSku().equals(SKU_AD_FREE)) {
			adFree = true;
			findViewById(R.id.ad).setVisibility(View.GONE);
			invalidateOptionsMenu();
		}
	}

	public void launchPurchaseFlow(String sku) {
		EasyTracker.getTracker().sendEvent(eventCatIab, eventActionLaunchPurchaseFlow, sku, (long) 0);
		mHelper.launchPurchaseFlow(this, sku, 101010, this);
	}

	public boolean isAdFree() {
		return adFree;
	}
}