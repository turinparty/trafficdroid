package it.localhost.trafficdroid.core;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class UpdateService extends Service {
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	private Runnable mTask = new Runnable() {
		public void run() {
			UpdateService.this.stopSelf();
		}
	};

	@Override
	public void onCreate() {
		new Thread(null, mTask, "UpdateService TrafficDroid").start();
	}
}
