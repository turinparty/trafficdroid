package it.localhost.trafficdroid.common;

import it.localhost.trafficdroid.premium.R;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.preference.PreferenceManager;

public class TdApp extends Application {
	private static final String notFound = "NotFound";
	private static Context instance;
	private static SharedPreferences sharedPreferences;
	private static Editor editor;
	private static String versionName;
	private static int versionCode;

	@Override
	public void onCreate() {
		//StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
		//StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
		super.onCreate();
		PreferenceManager.setDefaultValues(this, R.layout.preferencescreen, false);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		editor = sharedPreferences.edit();
		instance = this;
		try {
			PackageInfo pi = getPackageManager().getPackageInfo(getPackageName(), 0);
			versionName = pi.versionName;
			versionCode = pi.versionCode;
		} catch (NameNotFoundException e) {
			versionName = notFound;
			versionCode = 0;
		}
	}

	public static Editor getEditor() {
		return editor;
	}

	public static Context getContext() {
		return instance;
	}

	public static String getPrefString(String key, String def) {
		return sharedPreferences.getString(key, def);
	}

	public static String getPrefString(int key, int def) {
		return getPrefString(getContext().getString(key), getContext().getString(def));
	}

	public static boolean getPrefBoolean(String key, Boolean def) {
		return sharedPreferences.getBoolean(key, def);
	}

	public static boolean getPrefBoolean(int key, int def) {
		return getPrefBoolean(getContext().getString(key), Boolean.parseBoolean(getContext().getString(def)));
	}

	public static int getPrefInt(String key, int def) {
		return sharedPreferences.getInt(key, def);
	}

	public static String getVersionName() {
		return versionName;
	}

	public static int getVersionCode() {
		return versionCode;
	}
}