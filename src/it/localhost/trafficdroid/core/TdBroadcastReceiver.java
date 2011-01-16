package it.localhost.trafficdroid.core;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdLock;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
			if (sharedPreferences.getBoolean(context.getResources().getString(R.string.notificationEnablerKey), true)) {
				int notificationTimeValue = Integer.parseInt(sharedPreferences.getString(context.getResources().getString(R.string.notificationTimeKey), Const.notificationTimeKeyDefault));
				alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + notificationTimeValue, notificationTimeValue, pendingIntent);
			}
		} else if (intent.getAction().equals(Const.doUpdate)) {
			TdLock.getLock(context).acquire();
			context.startService(new Intent(context, UpdateService.class));
		}
	}
}