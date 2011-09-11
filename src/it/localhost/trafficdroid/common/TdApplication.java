package it.localhost.trafficdroid.common;

import android.app.Application;
import android.content.Context;

public class TdApplication extends Application {
	private static Context instance;

	public TdApplication() {
		super();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}

	public static Context getContext() {
		return instance;
	}
}