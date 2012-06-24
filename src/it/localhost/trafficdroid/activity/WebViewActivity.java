package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AbstractActivity {
	private static final String html = "text/html";
	private static final String utf8 = "UTF-8";
	private WebView webView;
	public static final String data = "data";
	public static final String slash = "/";
	public static final String http = "http://";
	public static final String url = "url";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);
		String url = getIntent().getStringExtra(WebViewActivity.url);
		String data = getIntent().getStringExtra(WebViewActivity.data);
		if (url != null)
			webView.loadUrl(url);
		else if (data != null)
			webView.loadData(data, html, utf8);
	}
}