package it.localhost.trafficdroid.common;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.MainActivity;
import it.localhost.trafficdroid.dao.MainDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import it.localhost.trafficdroid.parser.BadNewsParser;
import it.localhost.trafficdroid.parser.TrafficParser;

import java.util.Date;
import java.util.List;

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
		SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
		MainDTO pastDTO = MainDAO.retrieve();
		MainDTO currDTO = MainDAO.create();
		try {
			TrafficParser.parse(currDTO, TdApp.getPrefString(R.string.providerTrafficKey, R.string.providerTrafficDefault));
			BadNewsParser.parse(currDTO, TdApp.getPrefString(R.string.providerBadNewsKey, R.string.providerBadNewsDefault));
			currDTO.setTrafficTime(new Date());
			List<StreetDTO> pastStreets = pastDTO.getStreets();
			List<StreetDTO> currStreets = currDTO.getStreets();
			if (pastDTO != null && pastStreets.size() == currStreets.size())
				for (int i = 0; i < currStreets.size(); i++) {
					List<ZoneDTO> pastZones = pastStreets.get(i).getZones();
					List<ZoneDTO> currZones = currStreets.get(i).getZones();
					if (pastZones.size() == currZones.size())
						for (int j = 0; j < currZones.size(); j++) {
							ZoneDTO pastZone = pastZones.get(j);
							ZoneDTO currZone = currZones.get(j);
							if (pastZone.getId().equalsIgnoreCase(currZone.getId())) {
								if (currZone.getSpeedLeft() != 0 && pastZone.getSpeedLeft() < currZone.getSpeedLeft())
									currZone.setTrendLeft(R.drawable.up);
								else if (currZone.getSpeedLeft() != 0 && pastZone.getSpeedLeft() > currZone.getSpeedLeft())
									currZone.setTrendLeft(R.drawable.down);
								else
									currZone.setTrendLeft(0);
								if (currZone.getSpeedRight() != 0 && pastZone.getSpeedRight() < currZone.getSpeedRight())
									currZone.setTrendRight(R.drawable.up);
								else if (currZone.getSpeedRight() != 0 && pastZone.getSpeedRight() > currZone.getSpeedRight())
									currZone.setTrendRight(R.drawable.down);
								else
									currZone.setTrendRight(0);
							}
						}
				}
			String congestedZones = currDTO.getCongestedZones();
			if (congestedZones != null && TdApp.getPrefBoolean(R.string.chiaroveggenzaEnablerKey, R.string.chiaroveggenzaEnablerDefault)) {
				Notification notification = new Notification(R.drawable.icon, getString(R.string.notificationTicker), System.currentTimeMillis());
				notification.flags |= Notification.FLAG_AUTO_CANCEL;
				notification.defaults |= Notification.DEFAULT_ALL;
				notification.setLatestEventInfo(this, getString(R.string.notificationTitle), congestedZones, PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));
				((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(Const.notificationId, notification);
			} else
				((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(Const.notificationId);
			MainDAO.store(currDTO);
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