package it.localhost.trafficdroid.core;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.LocalBinder;
import it.localhost.trafficdroid.common.TdException;
import it.localhost.trafficdroid.dao.StreetDAO;
import it.localhost.trafficdroid.dto.DLCTaskDTO;
import it.localhost.trafficdroid.gui.activity.MainActivity;
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
import android.preference.PreferenceManager;
import android.util.Log;

public class UpdateService extends Service {
	private Intent dataReadyIntent = new Intent(Const.DATA_READY);
	private static final int NOTIFICATION_ID = 1;
	private DLCTaskDTO dlctask;
	private SharedPreferences sharedPreferences;
	private BroadcastReceiver receiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			Log.d("BroadcastReceiver", intent.getAction());
			if (intent.getAction().equals(Const.DO_UPDATE))
				updateDlcTask();
			else if (intent.getAction().equals(Const.INIT_DLCTASK))
				initDlcTask();
		}
	};

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.w("SRV", "onStartCommand");
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		registerReceiver(receiver, new IntentFilter(Const.DO_UPDATE));
		registerReceiver(receiver, new IntentFilter(Const.INIT_DLCTASK));
		initDlcTask();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.w("SRV", "onBind");
		return new LocalBinder(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.w("SRV", "onDestroy");
		unregisterReceiver(receiver);
	}

	public void initDlcTask() {
		Log.w("UpdateService", "initDlcTask");
		dlctask = new DLCTaskDTO(StreetDAO.getAllEnabled(sharedPreferences, getResources()), sharedPreferences.getString(getResources().getString(R.string.urlKey), Const.emptyString));
		dlctask.setCongestionThreshold(sharedPreferences.getInt(getResources().getString(R.string.trafficKey), 1));
		updateDlcTask();
	}

	public void updateDlcTask() {
		Log.w("UpdateService", "updateDlcTask");
		new DLCTask().execute();
	}

	public DLCTaskDTO getData() {
		return dlctask;
	}

	private void showNotification(String tickerText, String contentTitle, String contentText) {
		Notification notification = new Notification(R.drawable.icon, tickerText, System.currentTimeMillis());
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.setLatestEventInfo(getApplicationContext(), contentTitle, contentText, PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));
		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(NOTIFICATION_ID, notification);
	}

	private void cancelNotification() {
		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(NOTIFICATION_ID);
	}

	private class DLCTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... param) {
			try {
				Parser.parse(dlctask);
			} catch (TdException e) {
				e.printStackTrace();
			}
			return (!dlctask.getCongestedZones().equals(""));
		}

		@Override
		protected void onPostExecute(Boolean traffico) {
			sendBroadcast(dataReadyIntent);
			if (traffico) {
				showNotification("Traffico individuato!", "Ingorgo in tratte selezionate.", dlctask.getCongestedZones());
			} else {
				cancelNotification();
			}
			//					renderData();
			//			if (param != null)
			//				new AlertDialog.Builder(MainActivity.this).setTitle(getResources().getString(R.string.error)).setMessage(param).setPositiveButton(getResources().getString(R.string.ok), null).show();
		}
	}
}
