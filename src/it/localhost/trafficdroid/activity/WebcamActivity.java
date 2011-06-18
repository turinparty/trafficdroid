package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.webkit.WebView;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class WebcamActivity extends Activity {
	private WebView mWebView;
	private GoogleAnalyticsTracker tracker;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tracker = GoogleAnalyticsTracker.getInstance();
		tracker.start(Const.anlyticsId, this);
		tracker.trackPageView(this.getClass().getName());
		mWebView = new WebView(this);
		setContentView(mWebView);
		mWebView.getSettings().setJavaScriptEnabled(true);
		String url = PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.providerCamKey), getString(R.string.providerCamDefault));
		//CookieDAO.setCookie(this, url);
		int code = Integer.parseInt(getIntent().getStringExtra(Const.camId)) + 6280 * (new GregorianCalendar().get(GregorianCalendar.DATE));
		mWebView.loadUrl(Const.http + url + Const.popupTelecamera + code);
	}

	@Override
	public void onPause() {
		super.onPause();
		tracker.dispatch();
	}

	@Override
	protected void onStop() {
		super.onStop();
		tracker.stop();
	}
}