package it.localhost.trafficdroid.core;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.StartupBroadcastReceiver;
import it.localhost.trafficdroid.common.TdException;
import it.localhost.trafficdroid.dao.DlcTaskDAO;
import it.localhost.trafficdroid.dao.TrafficDAO;
import it.localhost.trafficdroid.dto.DLCTaskDTO;
import it.localhost.trafficdroid.gui.activity.MainActivity;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class UpdateService extends IntentService {
	private DLCTaskDTO dlcTaskDto = null;
	private SharedPreferences sharedPreferences;

	public UpdateService() {
		super("UpdateService");
	}

	@Override
	public void onCreate() {
		Log.e("SERV", "onCreate");
		super.onCreate();
		PreferenceManager.setDefaultValues(this, R.layout.preferences, false);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.e("SERV", "onHandleIntent, action = " + intent.getAction());
		if (intent.getAction().equals(Const.DO_UPDATE))
			updateDlcTask();
		else if (intent.getAction().equals(Const.PREF_EXIT)) {
			StartupBroadcastReceiver.scheduleService(getApplicationContext());
			dlcTaskDto = null;
			updateDlcTask();
		}
	}

	public void updateDlcTask() {
		sendBroadcast(Const.beginUpdateIntent);
		if (dlcTaskDto == null)
			dlcTaskDto = DlcTaskDAO.get(sharedPreferences, getResources());
		try {
			Parser.parse(dlcTaskDto);
			TrafficDAO.storeData(dlcTaskDto, UpdateService.this);
		} catch (TdException e) {
			// e.printStackTrace();
		}
		if (!dlcTaskDto.getCongestedZones().equals(Const.emptyString))
			showNotification();
		else
			cancelNotification();
		sendBroadcast(Const.endUpdateIntent);
	}

	private void showNotification() {
		Notification notification = new Notification(R.drawable.notif_icon, getResources().getString(R.string.tickerText), System.currentTimeMillis());
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.setLatestEventInfo(getApplicationContext(), getResources().getString(R.string.notificationTitle), dlcTaskDto.getCongestedZones(),
				PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));
		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(Const.NOTIFICATION_ID, notification);
	}

	private void cancelNotification() {
		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(Const.NOTIFICATION_ID);
	}
}