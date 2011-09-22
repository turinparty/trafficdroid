package it.localhost.trafficdroid.common;

import it.localhost.trafficdroid.R;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class TdApp extends Application {
	private static Context instance;
	private static SharedPreferences sharedPreferences;
	private static Editor editor;

	@Override
	public void onCreate() {
		super.onCreate();
		PreferenceManager.setDefaultValues(this, R.layout.preferencescreen, false);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		editor = sharedPreferences.edit();
		instance = this;
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
}