package it.localhost.trafficdroid.gui;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

public class TDPreferenceActivity extends PreferenceActivity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PreferenceScreen preferenceScreen = getPreferenceManager().createPreferenceScreen(this);
		EditTextPreference urlRequestPref = new EditTextPreference(this);
		urlRequestPref.setKey(Const.KEY_URL);
		urlRequestPref.setTitle(R.string.urlRequest);
		urlRequestPref.setSummary(R.string.urlInfo);
		urlRequestPref.setDialogTitle(R.string.urlRequest);
		preferenceScreen.addPreference(urlRequestPref);
		setPreferenceScreen(preferenceScreen);
	}
}