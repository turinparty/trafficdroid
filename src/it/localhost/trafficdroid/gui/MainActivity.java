package it.localhost.trafficdroid.gui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends Activity
{
	public static final String PREFS_NAME = "TrafficDroid";
	public static final String KEY_URL = "url";
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		if (settings.getString(KEY_URL, null) == null)
			startActivity(new Intent(MainActivity.this, UrlRequestActivity.class));
		else
			startActivity(new Intent(MainActivity.this, ParserActivity.class));
		
		finish();
	}
}
