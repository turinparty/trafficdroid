package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class PreferencesActivity extends AbstractActivity { // NO_UCD
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preference);
		setTitle(R.string.settings);
		PreferenceManager.setDefaultValues(this, R.layout.preferencescreen, false);
	}
}