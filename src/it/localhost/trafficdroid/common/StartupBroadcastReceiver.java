package it.localhost.trafficdroid.common;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.core.UpdateService;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceManager;

public class StartupBroadcastReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
			scheduleService(context);
		else if (intent.getAction().equals(Const.DO_UPDATE) || intent.getAction().equals(Const.PREF_EXIT)) {
			Intent doUpdateIntent = new Intent(context, UpdateService.class);
			doUpdateIntent.setAction(intent.getAction());
			context.startService(doUpdateIntent);
		}
	}

	public static void scheduleService(Context context) {
		PendingIntent mAlarmSender = PendingIntent.getBroadcast(context, 0, Const.doUpdateIntent, 0);
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		int updateInterval = Integer.parseInt(sharedPreferences.getString(context.getResources().getString(R.string.updateIntervalKey), Const.defaultUpdateInterval));
		am.cancel(mAlarmSender);
		if (updateInterval > 0)
			am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 1000 * updateInterval, mAlarmSender);
	}
}