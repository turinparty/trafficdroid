package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdAnalytics;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AbstractActivity {
	private WebView webView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);
		String url = getIntent().getStringExtra(Const.url);
		String data = getIntent().getStringExtra(Const.data);
		if (url != null)
			webView.loadUrl(url);
		else if (data != null)
			webView.loadData(data, Const.html, Const.utf8);
	}
}