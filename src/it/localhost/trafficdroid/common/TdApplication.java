package it.localhost.trafficdroid.common;

import android.app.Application;
import android.content.Context;

public class TdApplication extends Application {
	private static Context instance;

	public TdApplication() {
		super();
		instance = this;
	}

	public static Context getContext() {
		return instance;
	}
}