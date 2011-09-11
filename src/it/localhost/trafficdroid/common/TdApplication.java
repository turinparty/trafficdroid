package it.localhost.trafficdroid.common;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

public class TdApplication extends Application {
	private static Context instance;

	public TdApplication() {
		super();
		instance = this;
		GoogleAnalyticsTracker.getInstance().trackEvent(Const.eventCatApp, Const.eventActionVersion, versionName(), 0);
	}

	public static Context getContext() {
		return instance;
	}
	
	public String versionName() {
		try {
			return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			return "NotFound";
		}
	}
}