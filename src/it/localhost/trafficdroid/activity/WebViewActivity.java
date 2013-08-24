package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AbstractActivity {
	private static final String AUTOSTRADE_IT = "autostrade.it";
	public static final String urlTag = "url";
	public static final String dataTag = "data";
	private WebView webView;
	private String url;
	private String data;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.webview);
		webView = (WebView) findViewById(R.id.webview);
		webView.setWebViewClient(new WebViewClient());
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setUseWideViewPort(true);
		url = getIntent().getStringExtra(WebViewActivity.urlTag);
		data = getIntent().getStringExtra(WebViewActivity.dataTag);
		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				WebViewActivity.this.setProgress(progress * 100);
			}
		});
		if (url != null) {
			if (url.length() > 26 && url.substring(14, 27).equals(AUTOSTRADE_IT)) {
				Point size = new Point();
				getWindowManager().getDefaultDisplay().getSize(size);
				if (size.x > 768)
					url = url + "&ua=Mozilla/5.0%20(iPad;%20U;%20CPU%20iPhone%20OS%203_2%20like%20Mac%20OS%20X;%20en-us)%20AppleWebKit/531.21.10";
				else if (size.x > 320)
					url = url + "&ua=Android%201.1";
				else
					url = url + "&ua=NokiaE51";
			}
			webView.loadUrl(url);
		} else if (data != null)
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