package it.localhost.trafficdroid.common;

import it.localhost.trafficdroid.activity.WebcamActivity;
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
		Intent intent = new Intent(context, WebcamActivity.class);
		intent.putExtra(Const.camId, ((String) v.getTag()).substring(1));
		context.startActivity(intent);
	}
}
