package it.localhost.trafficdroid.common;

import com.commonsware.cwac.wakeful.WakefulIntentService;

import it.localhost.trafficdroid.R;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.SystemClock;
import android.preference.PreferenceManager;

public class TdBroadcastReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED) || intent.getAction().equals(Const.scheduleService) || intent.getAction().equals(Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE)) {
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, Const.doUpdateIntent, 0);
			AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
			SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
			alarmManager.cancel(pendingIntent);
			Resources resources = context.getResources();
			if (sharedPreferences.getBoolean(resources.getString(R.string.chiaroveggenzaEnablerKey), Boolean.parseBoolean(resources.getString(R.string.chiaroveggenzaEnablerDefault)))) {
				int notificationTimeValue = Integer.parseInt(sharedPreferences.getString(resources.getString(R.string.chiaroveggenzaTimeKey), resources.getString(R.string.chiaroveggenzaTimeDefault)));
				alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + notificationTimeValue, notificationTimeValue, pendingIntent);
			}
		} else if (intent.getAction().equals(Const.doUpdate)) {
			WakefulIntentService.sendWakefulWork(context, TdIntentService.class);
		}
	}
}