package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class WebViewActivity extends AbstractActivity {
	private WebView webView;
	public static final String slash = "/";
	public static final String http = "http://";
	public static final String www = "www.";
	public static final String URL = "url";
	private String url;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);
		url = getIntent().getStringExtra(WebViewActivity.URL);
		webView.loadUrl(url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.webview_option, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuRefresh:
			webView.loadUrl(url);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}