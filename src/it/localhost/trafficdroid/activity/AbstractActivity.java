package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdAnalytics;
import android.app.Activity;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

public abstract class AbstractActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TdAnalytics.startNewSession(Const.anlyticsId);
	}

	@Override
	public void onResume() {
		super.onResume();
		TdAnalytics.trackPageView(this.getClass().getName());
	}

	@Override
	public void onPause() {
		super.onPause();
		TdAnalytics.dispatch();
	}

	@Override
	public void onStop() {
		super.onStop();
		TdAnalytics.stopSession();
	}

	public String versionName() {
		try {
			return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			return Const.notFound;
		}
	}

	public int versionCode() {
		try {
			return getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			return 0;
		}
	}
}