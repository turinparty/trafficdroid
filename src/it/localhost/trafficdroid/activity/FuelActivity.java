package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.webkit.WebView;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class FuelActivity extends Activity {
	private WebView mWebView;
	private GoogleAnalyticsTracker tracker;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tracker = GoogleAnalyticsTracker.getInstance();
		tracker.startNewSession(Const.anlyticsId, this);
		mWebView = new WebView(this);
		setContentView(mWebView);
		String url = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getString(R.string.providerFuelKey), getString(R.string.providerFuelDefault));
		mWebView.loadUrl(Const.http + url + Const.fuel);
	}

	@Override
	public void onResume() {
		super.onResume();
		tracker.trackPageView(this.getClass().getName());
	}

	@Override
	public void onPause() {
		super.onPause();
		tracker.dispatch();
	}

	@Override
	public void onStop() {
		super.onStop();
		tracker.stopSession();
	}
}