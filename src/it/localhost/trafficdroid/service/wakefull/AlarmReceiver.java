package it.localhost.trafficdroid.service.wakefull;

import it.localhost.trafficdroid.service.TdListener;
import it.localhost.trafficdroid.service.wakefull.WakefulIntentService.AlarmListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AlarmReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context ctxt, Intent intent) {
		AlarmListener listener = new TdListener();
		if (intent.getAction() == null)
			listener.sendWakefulWork(ctxt);
		else
			WakefulIntentService.scheduleAlarms(listener, ctxt);
	}
}