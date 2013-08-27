package it.localhost.trafficdroid.fragment;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.PreferencesActivity;
import it.localhost.trafficdroid.activity.WebViewActivity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;

public class SetupDialogFragment extends DialogFragment {
	private static final String APP_URL = "https://code.google.com/p/trafficdroid";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Builder builder = new Builder(getActivity());
		builder.setTitle(R.string.warning);
		builder.setMessage(R.string.badConf);
		builder.setPositiveButton(R.string.setProvider, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				startActivity(new Intent(getActivity(), PreferencesActivity.class));
			}
		});
		builder.setNegativeButton(R.string.betterInfo, new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Intent intent = new Intent(getActivity(), WebViewActivity.class);
				intent.putExtra(WebViewActivity.urlTag, APP_URL);
				startActivity(intent);
			}
		});
		setCancelable(false);
		builder.setCancelable(false);
		return builder.create();
	}
}