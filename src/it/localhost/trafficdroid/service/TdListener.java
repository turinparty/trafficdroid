package it.localhost.trafficdroid.service;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Utility;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.SystemClock;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.commonsware.cwac.wakeful.WakefulIntentService.AlarmListener;

public class TdListener implements AlarmListener {
	@Override
	public void scheduleAlarms(AlarmManager mgr, PendingIntent pi, Context ctxt) {
		mgr.cancel(pi);
		if (Utility.isChiaroveggenzaEnabler(ctxt) && !Utility.getProviderTraffic(ctxt).equals(ctxt.getString(R.string.providerTrafficDefault))) {
			int notificationTimeValue = Integer.parseInt(Utility.getChiaroveggenzaTime(ctxt));
			mgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + notificationTimeValue, notificationTimeValue, pi);
		}
	}

	@Override
	public void sendWakefulWork(Context ctxt) {
		WakefulIntentService.sendWakefulWork(ctxt, TdService.class);
	}

	@Override
	public long getMaxAge() {
		return 3600000;
	}
}