package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class PreferencesActivity extends PreferenceActivity {
	private GoogleAnalyticsTracker tracker;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tracker = GoogleAnalyticsTracker.getInstance();
		tracker.startNewSession(Const.anlyticsId, this);
		addPreferencesFromResource(R.layout.preferencescreen);
		int[] streetId = getResources().getIntArray(R.array.streetsId);
		String[] streetName = getResources().getStringArray(R.array.streetsName);
		int[] streetFather = getResources().getIntArray(R.array.streetsFather);
		PreferenceScreen root = getPreferenceScreen();
		PreferenceCategory streetsCategory = new PreferenceCategory(this);
		root.addPreference(streetsCategory);
		streetsCategory.setTitle(R.string.mappedSreet);
		for (int i = 0; i < streetId.length; i++)
			if (streetFather[i] == 0) {
				PreferenceScreen streetScreen = getStreetScreen(streetId[i], streetName[i]);
				streetsCategory.addPreference(streetScreen);
				PreferenceCategory subStreetCategory = new PreferenceCategory(this);
				streetScreen.addPreference(subStreetCategory);
				subStreetCategory.setTitle(R.string.diramation);
				boolean alone = true;
				for (int j = 0; j < streetFather.length; j++)
					if (streetFather[j] == streetId[i]) {
						PreferenceScreen subStreetScreen = getStreetScreen(streetId[j], streetName[j]);
						subStreetCategory.addPreference(subStreetScreen);
						PreferenceCategory subZonesCategory = new PreferenceCategory(this);
						subStreetScreen.addPreference(subZonesCategory);
						setZonesCategory(subZonesCategory, streetId[j]);
						alone = false;
					}
				if (alone)
					streetScreen.removePreference(subStreetCategory);
				PreferenceCategory zonesCategory = new PreferenceCategory(this);
				streetScreen.addPreference(zonesCategory);
				setZonesCategory(zonesCategory, streetId[i]);
			}
	}

	private PreferenceScreen getStreetScreen(int streetId, String streetName) {
		PreferenceScreen streetScreen = getPreferenceManager().createPreferenceScreen(this);
		streetScreen.setTitle(streetName);
		CheckBoxPreference streetCheck = new CheckBoxPreference(this);
		streetCheck.setKey(Integer.toString(streetId));
		streetCheck.setTitle(streetName);
		streetCheck.setSummary(R.string.selectStreet);
		streetScreen.addPreference(streetCheck);
		return streetScreen;
	}

	private void setZonesCategory(PreferenceCategory zonesCategory, int streetId) {
		zonesCategory.setTitle(R.string.sniper);
		String[] zonesId = getResources().getStringArray(Const.zonesRes.get(streetId));
		String[] zonesName = getResources().getStringArray(Const.zonesRes.get(0 - streetId));
		for (int k = 0; k < zonesId.length; k++) {
			CheckBoxPreference singlezone = new CheckBoxPreference(this);
			singlezone.setKey(zonesId[k]);
			singlezone.setTitle(zonesName[k]);
			zonesCategory.addPreference(singlezone);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		tracker.trackPageView(this.getClass().getName());
	}

	@Override
	public void onPause() {
		super.onPause();
		tracker.dispatch();
	}

	@Override
	public void onStop() {
		super.onStop();
		tracker.stopSession();
		sendBroadcast(Const.scheduleServiceIntent);
	}
}