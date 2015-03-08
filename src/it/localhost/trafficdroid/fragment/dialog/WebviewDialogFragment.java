package it.localhost.trafficdroid.fragment.dialog;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.AdManager;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebView;

import com.google.android.gms.ads.AdView;

public class WebviewDialogFragment extends DialogFragment {
	public static final String TAG_URL = "url";
	public static final String TAG_DATA = "data";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Builder builder = new Builder(getActivity());
		WebView webView = (WebView) View.inflate(getActivity(), R.layout.webview, null);
		webView.getSettings().setJavaScriptEnabled(true);
		String url = getArguments().getString(TAG_URL);
		String data = getArguments().getString(TAG_DATA);
		if (url != null) {
			webView.loadUrl(url);
		} else if (data != null)
			webView.loadData(data, "text/html", null);
		builder.setView(webView);
		Dialog d = builder.create();
		d.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		new AdManager().load(getActivity(), ((AdView) webView.findViewById(R.id.adView)), true);
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