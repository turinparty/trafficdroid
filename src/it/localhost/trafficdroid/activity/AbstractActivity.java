package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.TdAdListener;
import it.localhost.trafficdroid.common.Utility;
import it.localhost.trafficdroid.fragment.QuizDialogFragment;

import java.security.KeyFactory;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Base64;
import android.view.View;

import com.android.vending.billing.IInAppBillingService;
import com.google.ads.AdRequest;
import com.google.ads.InterstitialAd;
import com.google.analytics.tracking.android.EasyTracker;

public abstract class AbstractActivity extends Activity { // NO_UCD
	private static final String IN_APP_BILLING_SERVICE = "com.android.vending.billing.InAppBillingService.BIND";
	public static final String EVENT_CAT_WEBCAM = "Webcam";
	public static final String EVENT_CAT_BADNEWS = "BadNews";
	public static final String EVENT_CAT_IAB = "InAppBilling";
	public static final String EVENT_CAT_GRAPH = "Graph";
	public static final String EVENT_ACTION_REQUEST = "Request";
	public static final String EVENT_ACTION_OPEN = "Open";
	public static final String EVENT_ACTION_NONE = "None";
	public static final String EVENT_ACTION_LAUNCHPURCHASEFLOW = "LaunchPurchaseFlow";
	private static final String ITEM_TYPE_INAPP = "inapp";
	public static final String SKU_AD_FREE = "ad_free";
	public static final String SKU_QUIZ_FREE = "quiz_free";
	public static final String SKU_INTERSTITIAL_FREE = "interstitial_free";
	private static final String RESPONSE_CODE = "RESPONSE_CODE";
	private static final String RESPONSE_BUY_INTENT = "BUY_INTENT";
	private static final String RESPONSE_INAPP_PURCHASE_ITEM_LIST = "INAPP_PURCHASE_ITEM_LIST";
	private static final String RESPONSE_INAPP_PURCHASE_DATA_LIST = "INAPP_PURCHASE_DATA_LIST";
	private static final String RESPONSE_INAPP_DATA_SIGNATURE_LIST = "INAPP_DATA_SIGNATURE_LIST";
	private static final String RESPONSE_INAPP_CONTINUATION_TOKEN = "INAPP_CONTINUATION_TOKEN";
	private static final String KEY_FACTORY_ALGORITHM = "RSA";
	private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
	private static final String KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgg7ckVCx3779Q4Dq99wMFYlwS4+jmbrTtBjLzG2cL4xoz6pZhLct4vIL2sfhA588Vfp4vRDHaIN3lpiCOGIxWRxI3krOoF+n1G/F9kUdiGaK4hYMPYa41MPbG6wc9tWJgcGe0PdYExCmeIvFiQrc4HU63J9zN+C1HRqw1t91YC2vzyZFxNLoIp3kcoz6rBCopm4GA01ZPZrP5RTR2hiJLrDVJl5mzuDrl7yoMq6OQ1SasVaWkgN7yTDyh9Df9hv5FsE8haVFddSJfTEh4BFZcFSW+17xgeImNtCgDtQ/GuTG3FIOiOotugIa1OjKC4z5zbZFl8Zz+cz8fFOxzfLkTQIDAQAB";
	private boolean adFree, interstitialFree;
	private IInAppBillingService inAppBillingService;
	private ServiceConnection serviceConnection;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		serviceConnection = new TdServiceConnection();
		bindService(new Intent(IN_APP_BILLING_SERVICE), serviceConnection, Context.BIND_AUTO_CREATE);
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
		unbindService(serviceConnection);
	}

	public void launchPurchaseFlow(String sku) {
		EasyTracker.getTracker().sendEvent(EVENT_CAT_IAB, EVENT_ACTION_LAUNCHPURCHASEFLOW, sku, (long) 0);
		try {
			Bundle buyIntentBundle = inAppBillingService.getBuyIntent(3, getPackageName(), sku, ITEM_TYPE_INAPP, "");
			if (buyIntentBundle.getInt(RESPONSE_CODE) == 0)
				startIntentSenderForResult(((PendingIntent) buyIntentBundle.getParcelable(RESPONSE_BUY_INTENT)).getIntentSender(), 101010, new Intent(), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isAdFree() {
		return adFree;
	}

	public boolean isInterstitialFree() {
		return interstitialFree;
	}

	private class TdServiceConnection implements ServiceConnection {
		@Override
		public void onServiceDisconnected(ComponentName name) {
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			inAppBillingService = IInAppBillingService.Stub.asInterface(service);
			try {
				int response = inAppBillingService.isBillingSupported(3, getPackageName(), ITEM_TYPE_INAPP);
				if (response == 0)
					new RetrievePurchasesService().execute();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public class RetrievePurchasesService extends AsyncTask<Void, Void, ArrayList<String>> {
		@Override
		protected ArrayList<String> doInBackground(Void... params) {
			ArrayList<String> out = new ArrayList<String>();
			String continueToken = null;
			try {
				Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
				sig.initVerify(KeyFactory.getInstance(KEY_FACTORY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decode(KEY, Base64.DEFAULT))));
				do {
					Bundle ownedItems = inAppBillingService.getPurchases(3, getPackageName(), ITEM_TYPE_INAPP, continueToken);
					if (ownedItems.getInt(RESPONSE_CODE) == 0) {
						ArrayList<String> skuList = ownedItems.getStringArrayList(RESPONSE_INAPP_PURCHASE_ITEM_LIST);
						ArrayList<String> purchaseDataList = ownedItems.getStringArrayList(RESPONSE_INAPP_PURCHASE_DATA_LIST);
						ArrayList<String> signatureList = ownedItems.getStringArrayList(RESPONSE_INAPP_DATA_SIGNATURE_LIST);
						for (int i = 0; i < skuList.size(); ++i) {
							sig.update(purchaseDataList.get(i).getBytes());
							if (sig.verify(Base64.decode(signatureList.get(i), Base64.DEFAULT)))
								out.add(skuList.get(i));
						}
					}
					continueToken = ownedItems.getString(RESPONSE_INAPP_CONTINUATION_TOKEN);
				} while (continueToken != null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return out;
		}

		@Override
		protected void onPostExecute(ArrayList<String> result) {
			super.onPostExecute(result);
			if (result.contains(SKU_AD_FREE)) {
				adFree = true;
				findViewById(R.id.ad).setVisibility(View.GONE);
				invalidateOptionsMenu();
			}
			if (result.contains(SKU_INTERSTITIAL_FREE))
				interstitialFree = true;
			else if (!(AbstractActivity.this instanceof MainActivity)) {
				InterstitialAd interstitial = new InterstitialAd(AbstractActivity.this, getString(R.string.adUnitId));
				interstitial.setAdListener(new TdAdListener());
				AdRequest adRequest = new AdRequest();
				adRequest.addTestDevice(getString(R.string.testDevices));
				interstitial.loadAd(adRequest);
			}
			if (!result.contains(SKU_QUIZ_FREE) && AbstractActivity.this instanceof MainActivity && !Utility.getPrefString(AbstractActivity.this, R.string.providerTrafficKey, R.string.providerTrafficDefault).equals(getString(R.string.providerTrafficDefault)))
				new QuizDialogFragment().show(getFragmentManager(), getString(R.string.patenteQuiz));
		}
	}
}