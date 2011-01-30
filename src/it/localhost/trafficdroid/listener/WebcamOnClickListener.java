package it.localhost.trafficdroid.listener;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.WebcamActivity;
import it.localhost.trafficdroid.common.Const;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
		} else {
			Resources resources = context.getResources();
			new AlertDialog.Builder(context).setTitle(resources.getString(R.string.info)).setPositiveButton(resources.getString(R.string.ok), null).setMessage(resources.getString(R.string.help)).show();
		}
	}
}
