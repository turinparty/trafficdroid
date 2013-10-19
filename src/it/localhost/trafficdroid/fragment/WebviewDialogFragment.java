package it.localhost.trafficdroid.fragment;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.TdAdListener;
import it.localhost.trafficdroid.common.Utility;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.ads.AdRequest;
import com.google.ads.InterstitialAd;

public class WebviewDialogFragment extends DialogFragment {
	private static final String AUTOSTRADE_IT = "autostrade.it";
	public static final String TAG_URL = "url";
	public static final String TAG_DATA = "data";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Builder builder = new Builder(getActivity());
		WebView webView = (WebView) getActivity().getLayoutInflater().inflate(R.layout.webview, null);
		webView.setWebViewClient(new WebViewClient());
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);
		webView.getSettings().setBuiltInZoomControls(true);
		String url = getArguments().getString(TAG_URL);
		String data = getArguments().getString(TAG_DATA);
		if (url != null) {
			if (url.length() > 26 && url.substring(14, 27).equals(AUTOSTRADE_IT)) {
				Point size = new Point();
				getActivity().getWindowManager().getDefaultDisplay().getSize(size);
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
		builder.setView(webView);
		if (!Utility.isInterstitialFree(getActivity())) {
			InterstitialAd interstitial = new InterstitialAd(getActivity(), getString(R.string.adUnitId));
			interstitial.setAdListener(new TdAdListener());
			interstitial.loadAd(new AdRequest());
		}
		Dialog d = builder.create();
		d.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		return d;
	}

	public void show(FragmentManager fragmentManager, String url, String data) {
		Bundle arguments = new Bundle(2);
		arguments.putString(TAG_URL, url);
		arguments.putString(TAG_DATA, data);
		setArguments(arguments);
		show(fragmentManager, WebviewDialogFragment.class.getSimpleName());
	}
}