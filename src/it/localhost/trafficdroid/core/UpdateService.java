package it.localhost.trafficdroid.core;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
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
import android.preference.PreferenceManager;

public class UpdateService extends IntentService {
	public UpdateService() {
		super(Const.tdData);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		sendBroadcast(Const.beginUpdateIntent);
		try {
			DLCTaskDTO dlcTaskDto = DlcTaskDAO.get(this);
			Parser.parse(dlcTaskDto);
			TrafficDAO.storeData(dlcTaskDto, this);
			if (!dlcTaskDto.getCongestedZones().equals(Const.emptyString) && PreferenceManager.getDefaultSharedPreferences(this).getBoolean(getResources().getString(R.string.notificationEnablerKey), true)) {
				Notification notification = new Notification(R.drawable.notif_icon, getResources().getString(R.string.tickerText), System.currentTimeMillis());
				notification.flags |= Notification.FLAG_AUTO_CANCEL;
				notification.defaults |= Notification.DEFAULT_ALL;
				notification.setLatestEventInfo(getApplicationContext(), getResources().getString(R.string.notificationTitle), dlcTaskDto.getCongestedZones(), PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));
				((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(Const.notificationId, notification);
			} else
				((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(Const.notificationId);
		} catch (TdException e) {
			//TODO come le gestiamo le eccezioni?
		}
		sendBroadcast(Const.endUpdateIntent);
	}
}