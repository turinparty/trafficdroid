package it.localhost.trafficdroid.activity;

import java.util.GregorianCalendar;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.webkit.WebView;

public class WebcamActivity extends Activity {
	private WebView mWebView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mWebView = new WebView(this);
		setContentView(mWebView);
		mWebView.getSettings().setJavaScriptEnabled(true);
		String url = PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.providerCamKey), getString(R.string.providerCamDefault));
		//CookieDAO.setCookie(this, url);
		int code = Integer.parseInt(getIntent().getStringExtra(Const.camId)) + 6280 * (new GregorianCalendar().get(GregorianCalendar.DATE));
		mWebView.loadUrl(Const.http + url + Const.popupTelecamera + code);
	}
}