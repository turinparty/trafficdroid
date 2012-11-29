package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AbstractActivity {
	private WebView webView;
	public static final String slash = "/";
	public static final String http = "http://";
	public static final String https = "https://";
	public static final String www = "www.";
	public static final String urlTag = "url";
	public static final String dataTag = "data";
	private String url;
	private String data;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.webview);
		webView = (WebView) findViewById(R.id.webview);
		webView.setWebViewClient(new WebViewClient());
		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				WebViewActivity.this.setProgress(progress * 100);
			}
		});
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);
		url = getIntent().getStringExtra(WebViewActivity.urlTag);
		data = getIntent().getStringExtra(WebViewActivity.dataTag);
		if (url != null)
			webView.loadUrl(url);
		else if (data != null)
			webView.loadData(data, "text/html", null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (url != null)
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