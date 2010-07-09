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
		EditTextPreference editTextPref = new EditTextPreference(this);
		editTextPref.setKey(Const.KEY_URL);
		editTextPref.setTitle(R.string.urlRequest);
		editTextPref.setSummary(R.string.urlInfo);
		editTextPref.setDialogTitle(R.string.urlRequest);
		preferenceScreen.addPreference(editTextPref);
		setPreferenceScreen(preferenceScreen);
	}
}