package it.localhost.trafficdroid.service;

import it.localhost.trafficdroid.premium.R;
import it.localhost.trafficdroid.common.TdApp;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.SystemClock;

import com.commonsware.cwac.wakeful.WakefulIntentService;

public class TdListener implements WakefulIntentService.AlarmListener {
	public void scheduleAlarms(AlarmManager mgr, PendingIntent pi, Context ctxt) {
		mgr.cancel(pi);
		if (TdApp.getPrefBoolean(R.string.chiaroveggenzaEnablerKey, R.string.chiaroveggenzaEnablerDefault)) {
			int notificationTimeValue = Integer.parseInt(TdApp.getPrefString(R.string.chiaroveggenzaTimeKey, R.string.chiaroveggenzaTimeDefault));
			mgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + notificationTimeValue, notificationTimeValue, pi);
		}
	}

	public void sendWakefulWork(Context ctxt) {
		WakefulIntentService.sendWakefulWork(ctxt, TdService.class);
	}

	public long getMaxAge() {
		return Integer.parseInt(TdApp.getPrefString(R.string.chiaroveggenzaTimeKey, R.string.chiaroveggenzaTimeDefault)) * 2;
	}
}