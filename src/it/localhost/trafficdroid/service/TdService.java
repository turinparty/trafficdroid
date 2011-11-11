package it.localhost.trafficdroid.service;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.MainActivity;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdApp;
import it.localhost.trafficdroid.dao.MainDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import it.localhost.trafficdroid.exception.BadConfException;
import it.localhost.trafficdroid.exception.ConnectionException;
import it.localhost.trafficdroid.exception.GenericException;
import it.localhost.trafficdroid.parser.BadNewsParser;
import it.localhost.trafficdroid.parser.TrafficParser;
import it.localhost.trafficdroid.service.wakefull.WakefulIntentService;

import java.util.Date;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class TdService extends WakefulIntentService {
	public TdService() {
		super(Const.tdData);
	}

	@Override
	public void doWakefulWork(Intent arg0) {
		sendBroadcast(Const.beginUpdateIntent);
		NetworkInfo activeNetworkInfo = ((ConnectivityManager) TdApp.getContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
		try {
			if (activeNetworkInfo != null && !activeNetworkInfo.isConnected())
				throw new ConnectionException(Const.disconnectedMessage);
			MainDTO currDTO = MainDAO.create();
			TrafficParser.parse(currDTO, TdApp.getPrefString(R.string.providerTrafficKey, R.string.providerTrafficDefault));
			BadNewsParser.parse(currDTO, TdApp.getPrefString(R.string.providerBadNewsKey, R.string.providerBadNewsDefault));
			currDTO.setTrafficTime(new Date());
			List<StreetDTO> currStreets = currDTO.getStreets();
			try {
				MainDTO pastDTO = MainDAO.retrieve();
				List<StreetDTO> pastStreets = pastDTO.getStreets();
				if (pastStreets.size() == currStreets.size())
					for (int i = 0; i < currStreets.size(); i++) {
						List<ZoneDTO> pastZones = pastStreets.get(i).getZones();
						List<ZoneDTO> currZones = currStreets.get(i).getZones();
						if (pastZones.size() == currZones.size())
							for (int j = 0; j < currZones.size(); j++) {
								ZoneDTO pastZone = pastZones.get(j);
								ZoneDTO currZone = currZones.get(j);
								if (pastZone.getId().equalsIgnoreCase(currZone.getId())) {
									if (currZone.getSpeedLeft() != 0 && pastZone.getSpeedLeft() < currZone.getSpeedLeft())
										currZone.setTrendLeft(R.drawable.speed_up);
									else if (currZone.getSpeedLeft() != 0 && pastZone.getSpeedLeft() > currZone.getSpeedLeft())
										currZone.setTrendLeft(R.drawable.speed_down);
									else
										currZone.setTrendLeft(0);
									if (currZone.getSpeedRight() != 0 && pastZone.getSpeedRight() < currZone.getSpeedRight())
										currZone.setTrendRight(R.drawable.speed_up);
									else if (currZone.getSpeedRight() != 0 && pastZone.getSpeedRight() > currZone.getSpeedRight())
										currZone.setTrendRight(R.drawable.speed_down);
									else
										currZone.setTrendRight(0);
								}
							}
					}
			} catch (Exception e) {
				for (StreetDTO streetDTO : currStreets)
					for (ZoneDTO zoneDTO : streetDTO.getZones()) {
						zoneDTO.setTrendLeft(0);
						zoneDTO.setTrendRight(0);
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
			TdApp.getEditor().putBoolean(Const.exceptionCheck, false);
		} catch (GenericException e) {
			TdApp.getEditor().putBoolean(Const.exceptionCheck, true);
			TdApp.getEditor().putString(Const.exceptionMsg, e.getMessage());
		} catch (BadConfException e) {
			TdApp.getEditor().putBoolean(Const.exceptionCheck, true);
			TdApp.getEditor().putString(Const.exceptionMsg, Const.badConf + e.getMessage());
		} catch (ConnectionException e) {
			TdApp.getEditor().putBoolean(Const.exceptionCheck, true);
			TdApp.getEditor().putString(Const.exceptionMsg, e.getMessage());
		} finally {
			TdApp.getEditor().commit();
			sendBroadcast(Const.endUpdateIntent);
		}
	}
}