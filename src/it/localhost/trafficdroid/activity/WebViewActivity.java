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
	public static final String URL = "url";
	public static final String bollo = "http://www.agenziaentrate.gov.it/wps/content/Nsilib/Nsi/Home/Servizi+online/Servizi+senza+registrazione/Calcolo+del+bollo+e+controllo+dei+pagamenti+effettuati+in+base+ai+KW+o+ai+CV/";
	private String url;

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