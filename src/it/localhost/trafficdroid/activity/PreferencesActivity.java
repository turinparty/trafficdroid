package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import android.os.Bundle;

public class PreferencesActivity extends AbstractActivity { // NO_UCD
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preference);
		setTitle(R.string.settings);
	}
}