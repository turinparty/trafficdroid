package it.localhost.trafficdroid.gui.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.core.UpdateService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServiceActivity extends Activity {
	private Intent intentService;
	private Button startService;
	private Button stopService;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.serviceactivity);
		startService = (Button) findViewById(R.id.btnstart);
		stopService = (Button) findViewById(R.id.btnstop);
		
		intentService = new Intent(getApplicationContext(), UpdateService.class);
		
		startService.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				startService(intentService);
			}
		});
		
		stopService.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				stopService(intentService);
			}
		});
	}
}