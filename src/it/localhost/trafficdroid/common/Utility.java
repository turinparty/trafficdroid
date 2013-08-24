package it.localhost.trafficdroid.common;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.preference.PreferenceManager;

public class Utility {
	public static Editor getEditor(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).edit();
	}

	public static String getPrefString(Context context, String key, String def) {
		return PreferenceManager.getDefaultSharedPreferences(context).getString(key, def);
	}

	public static String getPrefString(Context context, int key, int def) {
		return getPrefString(context, context.getString(key), context.getString(def));
	}

	public static boolean getPrefBoolean(Context context, String key, Boolean def) {
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, def);
	}

	public static boolean getPrefBoolean(Context context, int key, int def) {
		return getPrefBoolean(context, context.getString(key), Boolean.parseBoolean(context.getString(def)));
	}

	public static int getPrefInt(Context context, String key, int def) {
		return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, def);
	}

	public static int getVersionCode(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			return 0;
		}
	}
}