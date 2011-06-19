package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class PreferencesActivity extends PreferenceActivity {
	private GoogleAnalyticsTracker tracker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tracker = GoogleAnalyticsTracker.getInstance();
		tracker.start(Const.anlyticsId, this);
		PreferenceManager.setDefaultValues(this, R.layout.preferences, false);
		addPreferencesFromResource(R.layout.preferences);
		PreferenceScreen root = getPreferenceScreen();
		PreferenceCategory streetsCategory = new PreferenceCategory(this);
		streetsCategory.setTitle(R.string.mappedSreet);
		root.addPreference(streetsCategory);
		int[] streetId = getResources().getIntArray(Const.streetsRes[0]);
		String[] streetName = getResources().getStringArray(Const.streetsRes[1]);
		for (int i = 0; i < streetId.length; i++) {
			PreferenceScreen streetScreen = getPreferenceManager().createPreferenceScreen(this);
			streetScreen.setTitle(streetName[i]);
			streetsCategory.addPreference(streetScreen);
			CheckBoxPreference streetCheck = new CheckBoxPreference(this);
			streetCheck.setKey(Integer.toString(streetId[i]));
			streetCheck.setTitle(streetName[i]);
			streetCheck.setSummary(R.string.selectStreet);
			streetScreen.addPreference(streetCheck);
			PreferenceCategory zonesCategory = new PreferenceCategory(this);
			zonesCategory.setTitle(R.string.sniper);
			streetScreen.addPreference(zonesCategory);
			String[][] zones = new String[2][];
			zones[0] = getResources().getStringArray(Const.zonesRes()[0][i]);
			zones[1] = getResources().getStringArray(Const.zonesRes()[1][i]);
			for (int j = 0; j < zones[0].length; j++) {
				CheckBoxPreference singlezone = new CheckBoxPreference(this);
				singlezone.setKey(zones[0][j]);
				singlezone.setTitle(zones[1][j]);
				zonesCategory.addPreference(singlezone);
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		tracker.trackPageView(this.getClass().getName());
	}

	@Override
	public void onPause() {
		super.onPause();
		tracker.dispatch();
	}

	@Override
	protected void onStop() {
		super.onStop();
		tracker.stop();
		sendBroadcast(Const.scheduleServiceIntent);
	}
}