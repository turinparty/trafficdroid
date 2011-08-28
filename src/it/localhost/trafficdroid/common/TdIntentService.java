package it.localhost.trafficdroid.common;

import java.util.Date;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.MainActivity;
import it.localhost.trafficdroid.dao.MainDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.parser.BadNewsParser;
import it.localhost.trafficdroid.parser.TrafficParser;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.commonsware.cwac.wakeful.WakefulIntentService;

public class TdIntentService extends WakefulIntentService {
	public TdIntentService() {
		super(Const.tdData);
	}

	@Override
	public void doWakefulWork(Intent arg0) {
		sendBroadcast(Const.beginUpdateIntent);
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = sharedPreferences.edit();
		try {
			MainDTO mainDto = MainDAO.create(getApplicationContext());
			TrafficParser.parse(mainDto, sharedPreferences.getString(getString(R.string.providerTrafficKey), getString(R.string.providerTrafficDefault)));
			BadNewsParser.parse(mainDto, sharedPreferences.getString(getString(R.string.providerBadNewsKey), getString(R.string.providerBadNewsDefault)));
			mainDto.setTrafficTime(new Date());
			MainDAO.store(mainDto, this);
			String congestedZones = mainDto.getCongestedZones();
			if (congestedZones != null && sharedPreferences.getBoolean(getString(R.string.chiaroveggenzaEnablerKey), Boolean.parseBoolean(getString(R.string.chiaroveggenzaEnablerDefault)))) {
				Notification notification = new Notification(R.drawable.notif_icon, getString(R.string.notificationTicker), System.currentTimeMillis());
				notification.flags |= Notification.FLAG_AUTO_CANCEL;
				notification.defaults |= Notification.DEFAULT_ALL;
				notification.setLatestEventInfo(this, getString(R.string.notificationTitle), congestedZones, PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));
				((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(Const.notificationId, notification);
			} else
				((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(Const.notificationId);
			editor.putBoolean(Const.exceptionCheck, false);
		} catch (TdException e) {
			editor.putBoolean(Const.exceptionCheck, true);
			editor.putString(Const.exceptionName, e.getName());
			editor.putString(Const.exceptionMsg, e.getMessage());
		} finally {
			editor.commit();
			sendBroadcast(Const.endUpdateIntent);
		}
	}
}