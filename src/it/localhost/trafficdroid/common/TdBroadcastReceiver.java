package it.localhost.trafficdroid.common;

import it.localhost.trafficdroid.R;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.commonsware.cwac.wakeful.WakefulIntentService;

public class TdBroadcastReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED) || intent.getAction().equals(Const.scheduleService) || intent.getAction().equals(Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE)) {
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, Const.doUpdateIntent, 0);
			AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
			alarmManager.cancel(pendingIntent);
			if (TdApp.getPrefBoolean(R.string.chiaroveggenzaEnablerKey, R.string.chiaroveggenzaEnablerDefault)) {
				int notificationTimeValue = Integer.parseInt(TdApp.getPrefString(R.string.chiaroveggenzaTimeKey, R.string.chiaroveggenzaTimeDefault));
				alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + notificationTimeValue, notificationTimeValue, pendingIntent);
			}
		} else if (intent.getAction().equals(Const.doUpdate)) {
			WakefulIntentService.sendWakefulWork(context, TdIntentService.class);
		}
	}
}