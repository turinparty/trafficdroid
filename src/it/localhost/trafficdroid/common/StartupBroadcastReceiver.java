package it.localhost.trafficdroid.common;

import it.localhost.trafficdroid.core.UpdateService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartupBroadcastReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.w("StartupBroadcastReceiver", "onReceive");
		context.startService(new Intent(context, UpdateService.class));
	}
}