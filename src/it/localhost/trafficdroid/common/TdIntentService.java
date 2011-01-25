package it.localhost.trafficdroid.common;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.MainActivity;
import it.localhost.trafficdroid.dao.MainDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;

public class TdIntentService extends IntentService {
	public TdIntentService() {
		super(Const.tdData);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (!TdLock.getLock(this).isHeld())
			TdLock.getLock(this).acquire();
		super.onStartCommand(intent, flags, startId);
		return START_REDELIVER_INTENT;
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		try {
			sendBroadcast(Const.beginUpdateIntent);
			MainDTO mainDto = MainDAO.create(this);
			Parser.parse(mainDto);
			MainDAO.store(mainDto, this);
			if (!mainDto.getCongestedZones().equals(Const.emptyString) && PreferenceManager.getDefaultSharedPreferences(this).getBoolean(getResources().getString(R.string.notificationEnablerKey), true)) {
				Notification notification = new Notification(R.drawable.notif_icon, getResources().getString(R.string.tickerText), System.currentTimeMillis());
				notification.flags |= Notification.FLAG_AUTO_CANCEL;
				notification.defaults |= Notification.DEFAULT_ALL;
				notification.setLatestEventInfo(getApplicationContext(), getResources().getString(R.string.notificationTitle), mainDto.getCongestedZones(), PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));
				((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(Const.notificationId, notification);
			} else
				((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(Const.notificationId);
			sendBroadcast(Const.endUpdateIntent);
		} catch (TdException e) {
			// come gestiamo la cosa?
		} finally {
			TdLock.getLock(this).release();
		}
	}
}