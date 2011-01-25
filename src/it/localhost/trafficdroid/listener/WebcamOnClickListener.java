package it.localhost.trafficdroid.listener;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.WebcamActivity;
import it.localhost.trafficdroid.common.Const;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class WebcamOnClickListener implements OnClickListener {
	private Context context;

	public WebcamOnClickListener(Context context) {
		super();
		this.context = context;
	}

	public void onClick(View v) {
		String code = (String) v.getTag();
		if (code.charAt(0) == 'z') {
			Intent intent = new Intent(context, WebcamActivity.class);
			intent.putExtra(Const.camId, code.substring(1));
			context.startActivity(intent);
		} else
			new AlertDialog.Builder(context).setTitle(context.getResources().getString(R.string.info)).setPositiveButton(context.getResources().getString(R.string.ok), null).setMessage(context.getResources().getString(R.string.help)).show();
	}
}
