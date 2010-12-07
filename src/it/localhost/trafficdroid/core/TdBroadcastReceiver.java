package it.localhost.trafficdroid.core;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
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
		System.err.println(intent.getAction());
		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED") || intent.getAction().equals(Const.scheduleService)) {
			
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, Const.doUpdateIntent, 0);
			AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
			SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
			alarmManager.cancel(pendingIntent);
			if (sharedPreferences.getBoolean(context.getResources().getString(R.string.notificationEnablerKey), true)) {
				int notificationTimeValue = Integer.parseInt(sharedPreferences.getString(context.getResources().getString(R.string.notificationTimeKey), Const.notificationTimeKeyDefault));
				alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 1000 * notificationTimeValue, pendingIntent);
			}
		} else if (intent.getAction().equals(Const.doUpdate)) {
			Intent doUpdateIntent = new Intent(context, UpdateService.class);
		//	doUpdateIntent.setAction(intent.getAction());
			context.startService(doUpdateIntent);
		}
	}
}