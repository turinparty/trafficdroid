package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import android.os.Bundle;
import android.webkit.WebView;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class WebViewActivity extends AbstractActivity {
	private WebView webView;
	private GoogleAnalyticsTracker tracker;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tracker = GoogleAnalyticsTracker.getInstance();
		tracker.startNewSession(Const.anlyticsId, this);
		setContentView(R.layout.linearlayout_webview);
		webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(getIntent().getStringExtra(Const.url));
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