package it.localhost.trafficdroid.gui.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

public class PreferencesActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PreferenceManager.setDefaultValues(this, R.layout.preferences, false);
		addPreferencesFromResource(R.layout.preferences);
		PreferenceScreen root = getPreferenceScreen();
		PreferenceCategory streetsCategory = new PreferenceCategory(this);
		streetsCategory.setTitle(R.string.mappedSreet);
		root.addPreference(streetsCategory);
		int[] streetId = getResources().getIntArray(Const.streetsResId[0]);
		String[] streetName = getResources().getStringArray(Const.streetsResId[1]);
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
			zones[0] = getResources().getStringArray(Const.zonesResId()[0][i]);
			zones[1] = getResources().getStringArray(Const.zonesResId()[1][i]);
			for (int j = 0; j < zones[0].length; j++) {
				CheckBoxPreference singlezone = new CheckBoxPreference(this);
				singlezone.setKey(zones[0][j]);
				singlezone.setTitle(zones[1][j]);
				zonesCategory.addPreference(singlezone);
			}
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		sendBroadcast(Const.scheduleServiceIntent);
	}
}