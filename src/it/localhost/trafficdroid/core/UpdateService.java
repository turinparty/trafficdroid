package it.localhost.trafficdroid.core;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.LocalBinder;
import it.localhost.trafficdroid.common.TdException;
import it.localhost.trafficdroid.dao.DlcTaskDAO;
import it.localhost.trafficdroid.dto.DLCTaskDTO;
import it.localhost.trafficdroid.gui.activity.MainActivity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

public class UpdateService extends Service {
	private DLCTaskDTO dlcTaskDto;
	private SharedPreferences sharedPreferences;
	private BroadcastReceiver receiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(Const.DO_UPDATE))
				updateDlcTask();
			else if (intent.getAction().equals(Const.PREF_EXIT)) {
				dlcTaskDto = DlcTaskDAO.get(sharedPreferences, getResources());
				scheduleService();
				updateDlcTask();
			}
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		return new LocalBinder(this);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		PreferenceManager.setDefaultValues(this, R.layout.preferences, false);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		dlcTaskDto = DlcTaskDAO.get(sharedPreferences, getResources());
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Const.DO_UPDATE);
		intentFilter.addAction(Const.PREF_EXIT);
		registerReceiver(receiver, intentFilter);
		scheduleService();
		updateDlcTask();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	public void updateDlcTask() {
		if (dlcTaskDto != null)
			new DlcAsyncTask().execute();
	}

	private void scheduleService() {
		PendingIntent mAlarmSender = PendingIntent.getBroadcast(getApplicationContext(), 0, Const.doUpdateIntent, 0);
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		int updateInterval = Integer.parseInt(sharedPreferences.getString(getResources().getString(R.string.updateIntervalKey), Const.updateInterval));
		am.cancel(mAlarmSender);
		if (updateInterval > 0)
			am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 1000 * updateInterval, mAlarmSender);
	}

	private void showNotification() {
		Notification notification = new Notification(R.drawable.notif_icon, Const.tickerText, System.currentTimeMillis());
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.setLatestEventInfo(getApplicationContext(), Const.notificationTitle, dlcTaskDto.getCongestedZones(), PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));
		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(Const.NOTIFICATION_ID, notification);
	}

	private void cancelNotification() {
		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(Const.NOTIFICATION_ID);
	}

	public DLCTaskDTO getData() {
		return dlcTaskDto;
	}

	private class DlcAsyncTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected void onPreExecute() {
			sendBroadcast(Const.beginUpdateIntent);
		}

		@Override
		protected Boolean doInBackground(Void... param) {
			try {
				Parser.parse(dlcTaskDto);
			} catch (TdException e) {
				//	e.printStackTrace();
			}
			return (!dlcTaskDto.getCongestedZones().equals(Const.emptyString));
		}

		@Override
		protected void onPostExecute(Boolean traffico) {
			sendBroadcast(Const.endUpdateIntent);
			if (traffico) {
				showNotification();
			} else {
				cancelNotification();
			}
		}
	}
}