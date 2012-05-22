package it.localhost.trafficdroid.service;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.MainActivity;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdApp;
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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;

import com.commonsware.cwac.wakeful.WakefulIntentService;

public class TdService extends WakefulIntentService { // NO_UCD
	public static final String beginUpdate = "it.localhost.trafficdroid.BEGIN_UPDATE";
	public static final String endUpdate = "it.localhost.trafficdroid.END_UPDATE";
	private static final Intent beginUpdateIntent = new Intent(beginUpdate);
	private static final Intent endUpdateIntent = new Intent(endUpdate);
	private static final String disconnectedMessage = "Connessione di rete inesistente";

	public TdService() {
		super(MainDAO.tdData);
	}

	@Override
	public void doWakefulWork(Intent arg0) {
		sendBroadcast(beginUpdateIntent);
		MainDTO currDTO = MainDAO.create();
		MainDTO pastDTO;
		try {
			pastDTO = MainDAO.retrieve();
		} catch (Exception e) {
			pastDTO = null;
		}
		NetworkInfo activeNetworkInfo = ((ConnectivityManager) TdApp.getContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
		if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
			try {
				TrafficParser.parse(currDTO, TdApp.getPrefString(R.string.providerTrafficKey, R.string.providerTrafficDefault));
				for (StreetDTO currStreet : currDTO.getStreets()) {
					List<ZoneDTO> currZones = currStreet.getZones();
					for (ZoneDTO currZone : currZones) {
						if (currZone.getSpeedLeft() < 1)
							currZone.setCatLeft((byte) 0);
						else if (currZone.getSpeedLeft() < 11)
							currZone.setCatLeft((byte) 1);
						else if (currZone.getSpeedLeft() < 31)
							currZone.setCatLeft((byte) 2);
						else if (currZone.getSpeedLeft() < 51)
							currZone.setCatLeft((byte) 3);
						else if (currZone.getSpeedLeft() < 71)
							currZone.setCatLeft((byte) 4);
						else if (currZone.getSpeedLeft() < 91)
							currZone.setCatLeft((byte) 5);
						else
							currZone.setCatLeft((byte) 6);
						if (currZone.getSpeedRight() < 1)
							currZone.setCatRight((byte) 0);
						else if (currZone.getSpeedRight() < 11)
							currZone.setCatRight((byte) 1);
						else if (currZone.getSpeedRight() < 31)
							currZone.setCatRight((byte) 2);
						else if (currZone.getSpeedRight() < 51)
							currZone.setCatRight((byte) 3);
						else if (currZone.getSpeedRight() < 71)
							currZone.setCatRight((byte) 4);
						else if (currZone.getSpeedRight() < 91)
							currZone.setCatRight((byte) 5);
						else
							currZone.setCatRight((byte) 6);
						boolean congestionLeft = currZone.getCatLeft() > 0 && currZone.getCatLeft() <= currDTO.getCongestionThreshold();
						boolean congestionRight = currZone.getCatRight() > 0 && currZone.getCatRight() <= currDTO.getCongestionThreshold();
						if (congestionLeft && congestionRight)
							currDTO.addCongestedZone(currZone.getName());
						else if (congestionLeft)
							currDTO.addCongestedZone(currZone.getName());
						else if (congestionRight)
							currDTO.addCongestedZone(currZone.getName());
						StreetDTO pastStreet = null;
						ZoneDTO pastZone = null;
						if (pastDTO != null) {
							pastStreet = pastDTO.getStreet(currStreet.getId());
							if (pastStreet != null) {
								pastZone = pastStreet.getZone(currZone.getName());
								if (pastZone != null) {
									int trendSpeed = Integer.parseInt(TdApp.getPrefString(R.string.trendSpeedKey, R.string.trendSpeedDefault));
									if (currZone.getCatLeft() == 0 || pastZone.getCatLeft() == 0)
										currZone.setTrendLeft(0);
									else if (currZone.getSpeedLeft() - pastZone.getSpeedLeft() >= trendSpeed)
										currZone.setTrendLeft(R.drawable.speed_up);
									else if (pastZone.getSpeedLeft() - currZone.getSpeedLeft() >= trendSpeed)
										currZone.setTrendLeft(R.drawable.speed_down);
									else
										currZone.setTrendLeft(0);
									if (currZone.getCatRight() == 0 || pastZone.getCatRight() == 0)
										currZone.setTrendRight(0);
									else if (currZone.getSpeedRight() - pastZone.getSpeedRight() >= trendSpeed)
										currZone.setTrendRight(R.drawable.speed_up);
									else if (pastZone.getSpeedRight() - currZone.getSpeedRight() >= trendSpeed)
										currZone.setTrendRight(R.drawable.speed_down);
									else
										currZone.setTrendRight(0);
								}
							}
						}
						if (pastZone == null) {
							currZone.setTrendLeft(0);
							currZone.setTrendRight(0);
						}
					}
				}
				String congestedZones = currDTO.getCongestedZones();
				if (congestedZones != null && TdApp.getPrefBoolean(R.string.chiaroveggenzaEnablerKey, R.string.chiaroveggenzaEnablerDefault)) {
					NotificationCompat.Builder bui = new NotificationCompat.Builder(this);
					bui.setDefaults(Notification.DEFAULT_ALL);
					bui.setSmallIcon(R.drawable.icon);
					bui.setTicker(congestedZones);
					bui.setContentTitle(getString(R.string.notificationTitle));
					bui.setContentText(congestedZones);
					Intent intent = new Intent(this, MainActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					bui.setContentIntent(PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
					((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(Const.notificationId, bui.getNotification());
				} else
					((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(Const.notificationId);
			} catch (Exception e) {
				TdApp.getEditor().putBoolean(Const.exceptionCheck, true);
				TdApp.getEditor().putString(Const.exceptionMsg, e.getMessage());
			}
			try {
				if (TdApp.getPrefBoolean(R.string.badnewsEnablerKey, R.string.badnewsEnablerDefault))
					new BadNewsParser(currDTO, TdApp.getPrefString(R.string.providerBadNewsKey, R.string.providerBadNewsDefault)).parse();
			} catch (Exception e) {
				TdApp.getEditor().putBoolean(Const.exceptionCheck, true);
				TdApp.getEditor().putString(Const.exceptionMsg, e.getMessage());
			}
			try {
				currDTO.setTrafficTime(new Date());
				MainDAO.store(currDTO);
			} catch (Exception e) {
				TdApp.getEditor().putBoolean(Const.exceptionCheck, true);
				TdApp.getEditor().putString(Const.exceptionMsg, e.getMessage());
			}
		} else {
			TdApp.getEditor().putBoolean(Const.exceptionCheck, true);
			TdApp.getEditor().putString(Const.exceptionMsg, disconnectedMessage);
		}
		TdApp.getEditor().commit();
		sendBroadcast(endUpdateIntent);
	}
}