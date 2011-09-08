package it.localhost.trafficdroid.common;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.MainActivity;
import it.localhost.trafficdroid.dao.MainDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import it.localhost.trafficdroid.parser.BadNewsParser;
import it.localhost.trafficdroid.parser.TrafficParser;

import java.util.Date;

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
		MainDTO pastDTO = MainDAO.retrieve(getApplicationContext());
		MainDTO currDTO = MainDAO.create(getApplicationContext());
		try {
			TrafficParser.parse(currDTO, sharedPreferences.getString(getString(R.string.providerTrafficKey), getString(R.string.providerTrafficDefault)));
			BadNewsParser.parse(currDTO, sharedPreferences.getString(getString(R.string.providerBadNewsKey), getString(R.string.providerBadNewsDefault)));
			currDTO.setTrafficTime(new Date());
			if (pastDTO != null && currDTO.getPrefCount() == pastDTO.getPrefCount() && pastDTO.getStreets().size() != 0)
				for (int i = 0; i < currDTO.getStreets().size(); i++)
					for (int j = 0; j < currDTO.getStreets().get(i).getZones().size(); j++) {
						ZoneDTO pastZone = pastDTO.getStreets().get(i).getZones().get(j);
						ZoneDTO currZone = currDTO.getStreets().get(i).getZones().get(j);
						if (pastZone.getSpeedLeft() < currZone.getSpeedLeft())
							currZone.setTrendLeft(R.drawable.up);
						else if (pastZone.getSpeedLeft() > currZone.getSpeedLeft())
							currZone.setTrendLeft(R.drawable.down);
						else if (pastZone.getSpeedLeft() == currZone.getSpeedLeft())
							currZone.setTrendLeft(0);
						if (pastZone.getSpeedRight() < currZone.getSpeedRight())
							currZone.setTrendRight(R.drawable.up);
						else if (pastZone.getSpeedRight() > currZone.getSpeedRight())
							currZone.setTrendRight(R.drawable.down);
						else if (pastZone.getSpeedRight() == currZone.getSpeedRight())
							currZone.setTrendRight(0);
					}
			String congestedZones = currDTO.getCongestedZones();
			if (congestedZones != null && sharedPreferences.getBoolean(getString(R.string.chiaroveggenzaEnablerKey), Boolean.parseBoolean(getString(R.string.chiaroveggenzaEnablerDefault)))) {
				Notification notification = new Notification(R.drawable.icon, getString(R.string.notificationTicker), System.currentTimeMillis());
				notification.flags |= Notification.FLAG_AUTO_CANCEL;
				notification.defaults |= Notification.DEFAULT_ALL;
				notification.setLatestEventInfo(this, getString(R.string.notificationTitle), congestedZones, PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));
				((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(Const.notificationId, notification);
			} else
				((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(Const.notificationId);
			MainDAO.store(currDTO, getApplicationContext());
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