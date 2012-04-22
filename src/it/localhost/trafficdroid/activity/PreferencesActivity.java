package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdAnalytics;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

public class PreferencesActivity extends PreferenceActivity { // NO_UCD
	private static final String autovelox = "Autovelox";
	public static final String autoveloxNone = "0";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TdAnalytics.startNewSession();
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
				PreferenceScreen streetScreen = addStreetScreen(streetsCategory, streetId[i], streetName[i]);
				PreferenceCategory subStreetCategory = null;
				for (int j = 0; j < streetFather.length; j++)
					if (streetFather[j] == streetId[i]) {
						if (subStreetCategory == null) {
							subStreetCategory = new PreferenceCategory(this);
							streetScreen.addPreference(subStreetCategory);
							subStreetCategory.setTitle(R.string.diramation);
						}
						setZonesCategory(addStreetScreen(subStreetCategory, streetId[j], streetName[j]), streetId[j]);
					}
				setZonesCategory(streetScreen, streetId[i]);
			}
	}

	private PreferenceScreen addStreetScreen(PreferenceCategory streetsCategory, int streetId, String streetName) {
		PreferenceScreen streetScreen = getPreferenceManager().createPreferenceScreen(this);
		streetsCategory.addPreference(streetScreen);
		streetScreen.setTitle(streetName);
		CheckBoxPreference streetCheck = new CheckBoxPreference(this);
		streetCheck.setKey(Integer.toString(streetId));
		streetCheck.setTitle(streetName);
		streetCheck.setSummary(R.string.selectStreet);
		streetScreen.addPreference(streetCheck);
		return streetScreen;
	}

	private void setZonesCategory(PreferenceScreen streetScreen, int streetId) {
		PreferenceCategory zonesCategory = new PreferenceCategory(this);
		streetScreen.addPreference(zonesCategory);
		zonesCategory.setTitle(R.string.sniper);
		String[] zonesId = getResources().getStringArray(Const.zonesResId.get(streetId));
		String[] zonesName = getResources().getStringArray(Const.zonesResName.get(streetId));
		String[] zonesAutovelox = getResources().getStringArray(Const.zonesResAutovelox.get(streetId));
		for (int k = 0; k < zonesId.length; k++) {
			CheckBoxPreference singlezone = new CheckBoxPreference(this);
			singlezone.setKey(zonesId[k]);
			singlezone.setTitle(zonesName[k]);
			if (!zonesAutovelox[k].equalsIgnoreCase(autoveloxNone))
				singlezone.setSummary(autovelox);
			zonesCategory.addPreference(singlezone);
		}
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
}