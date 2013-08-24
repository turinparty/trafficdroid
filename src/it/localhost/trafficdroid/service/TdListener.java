package it.localhost.trafficdroid.service;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Utility;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.SystemClock;

import com.commonsware.cwac.wakeful.WakefulIntentService;

public class TdListener implements WakefulIntentService.AlarmListener {
	public void scheduleAlarms(AlarmManager mgr, PendingIntent pi, Context ctxt) {
		mgr.cancel(pi);
		if (Utility.getPrefBoolean(ctxt, R.string.chiaroveggenzaEnablerKey, R.string.chiaroveggenzaEnablerDefault) && !Utility.getPrefString(ctxt, R.string.providerTrafficKey, R.string.providerTrafficDefault).equals(ctxt.getString(R.string.providerTrafficDefault))) {
			int notificationTimeValue = Integer.parseInt(Utility.getPrefString(ctxt, R.string.chiaroveggenzaTimeKey, R.string.chiaroveggenzaTimeDefault));
			mgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + notificationTimeValue, notificationTimeValue, pi);
		}
	}

	public void sendWakefulWork(Context ctxt) {
		WakefulIntentService.sendWakefulWork(ctxt, TdService.class);
	}

	public long getMaxAge() {
		return 3600000;
	}
}