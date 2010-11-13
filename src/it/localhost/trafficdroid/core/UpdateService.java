
package it.localhost.trafficdroid.core;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.LocalBinder;
import it.localhost.trafficdroid.common.TdException;
import it.localhost.trafficdroid.dao.StreetDAO;
import it.localhost.trafficdroid.dto.DLCTaskDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.gui.activity.MainActivity;

import java.util.List;

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

public class UpdateService extends Service
{
	public static final String DATA_READY = "com.commonsware.android.service.DATA_READY";
	public static final String DO_UPDATE = "com.commonsware.android.service.DO_UPDATE";
	private Intent dataReadyIntent = new Intent(DATA_READY);
	
	private static final int NOTIFICATION_ID = 1;
	
	private DLCTaskDTO dlctask;
	private SharedPreferences sharedPreferences;
	
	private BroadcastReceiver receiver = new BroadcastReceiver()
	{
		public void onReceive(Context context, Intent intent)
		{
			update();
		}
	};
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		Log.d("SRV", "onCreate");
		
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		
		
		update();
		
		registerReceiver(receiver, new IntentFilter(DO_UPDATE));
	}
	
	@Override
	public IBinder onBind(Intent intent)
	{
		Log.d("SRV", "onBind");
		return new LocalBinder(this);
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		unregisterReceiver(receiver);
		Log.d("SRV", "onDestroy");
	}
	
	public void update()
	{
		Log.d("SRV", "update");
		new DLCTask().execute();
	}
	
	public DLCTaskDTO getData()
	{
		Log.d("SRV", "srv getData");
		return dlctask;
	}
	
	private void showNotification(String tickerText, String contentTitle, String contentText)
	{
		Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		Notification notification = new Notification(R.drawable.icon, tickerText, System.currentTimeMillis());
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.setLatestEventInfo(getApplicationContext(), contentTitle, contentText, contentIntent);

		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(NOTIFICATION_ID, notification);
	}
	
	private void cancelNotification()
	{
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.cancel(NOTIFICATION_ID);
	}
	
	
	private class DLCTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... param) {
			try {
				dlctask = new DLCTaskDTO(StreetDAO.getAllEnabled(sharedPreferences, getResources()), sharedPreferences.getString(getResources().getString(R.string.urlKey), Const.emptyString));
				Parser.parse(dlctask);
			} catch (TdException e) {
				e.printStackTrace();
			}
			
			List<StreetDTO> streets = dlctask.getStreets();
			for (int strtCount = 0; strtCount < streets.size(); strtCount++) {
				for (int znCount = 0; znCount < streets.get(strtCount).getZones().size(); znCount++) {
					if (
							(streets.get(strtCount).getZones().get(znCount).getCatLeft() > 0 && streets.get(strtCount).getZones().get(znCount).getCatLeft() < 3) || 
							(streets.get(strtCount).getZones().get(znCount).getCatRight() > 0 && streets.get(strtCount).getZones().get(znCount).getCatRight() < 3)
						)
						return true;
				}
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean traffico) {
			sendBroadcast(dataReadyIntent);
			
			if (traffico)
			{
				showNotification("Traffico individuato!", "Ingorgo in tratte selezionate.", "Apri l'app");
			}
			else
			{
				cancelNotification();
			}
			
//					renderData();
			
//			if (param != null)
//				new AlertDialog.Builder(MainActivity.this).setTitle(getResources().getString(R.string.error)).setMessage(param).setPositiveButton(getResources().getString(R.string.ok), null).show();
		}
	}
}
