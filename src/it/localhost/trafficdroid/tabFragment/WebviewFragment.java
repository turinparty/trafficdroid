package it.localhost.trafficdroid.tabFragment;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.AdManager;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.google.android.gms.ads.AdView;

public class WebviewFragment extends Fragment implements TabListener {
	public static final String ALCOL_URL = "http://voti.kataweb.it/etilometro/index.php";
	private static final String URL_KEY = "URL_KEY";

	public static WebviewFragment newInstance(String url) {
		WebviewFragment instance = new WebviewFragment();
		Bundle args = new Bundle(1);
		args.putString(URL_KEY, url);
		instance.setArguments(args);
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		WebView webView = (WebView) View.inflate(getActivity(), R.layout.webview, null);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.loadUrl(getArguments().getString(URL_KEY));
		new AdManager().load(getActivity(), ((AdView) webView.findViewById(R.id.adView)), true);
		return webView;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.replace(android.R.id.content, this);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
}