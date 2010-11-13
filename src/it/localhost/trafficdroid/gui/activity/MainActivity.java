package it.localhost.trafficdroid.gui.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.LocalBinder;
import it.localhost.trafficdroid.common.TdException;
import it.localhost.trafficdroid.core.Parser;
import it.localhost.trafficdroid.core.UpdateService;
import it.localhost.trafficdroid.dao.StreetDAO;
import it.localhost.trafficdroid.dao.TrafficDAO;
import it.localhost.trafficdroid.dto.DLCTaskDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.gui.ZoneListAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private static final int REFRESH_ID = 2;
	private static final int CLOSE_ID = 3;
	private static final int START_AUTOUPDATE_ID = 4;
	private static final int STOP_AUTOUPDATE_ID = 5;
	
	private static final int UPDATE_INTERVAL_SECONDS = 5;
	
	private UpdateService appService = null;
	
	private Intent intentService;
	
	private PendingIntent mAlarmSender;
	
	private Intent doUpdateIntent = new Intent(UpdateService.DO_UPDATE);
	
	private DLCTaskDTO dlctask;
	
	private ListView zoneView;
	private TextView leftTextView;
	private TextView rightTextView;
	private TextView centerTextView;
	
	private Spinner spinner;
	private ArrayAdapter<StreetDTO> arrayAdapter;
	private ZoneListAdapter zoneListAdapter;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		leftTextView = (TextView) findViewById(R.id.left);
		rightTextView = (TextView) findViewById(R.id.right);
		centerTextView = (TextView) findViewById(R.id.center);
		zoneView = (ListView) findViewById(R.id.zonelist);
		spinner = (Spinner) findViewById(R.id.spinner);
		zoneListAdapter = new ZoneListAdapter(this);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				renderData();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
		
		intentService = new Intent(this, UpdateService.class);
		startService(intentService);
		
		mAlarmSender = PendingIntent.getBroadcast(getApplicationContext(), 0, doUpdateIntent, 0);
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
		bindService(intentService, onService, BIND_AUTO_CREATE);
	}

	@Override
	public void onResume() {
		super.onResume();
		
//		if (appService != null) appService.update();
		
		
		//TODO gestire url nulla e no strade selezionate
		
		registerReceiver(onDataReady, new IntentFilter(UpdateService.DATA_READY));
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		unregisterReceiver(onDataReady);
	}
	
	@Override
	protected void onStop()
	{
		super.onStop();
		unbindService(onService);
	}
	
	private void refreshGUI()
	{
		Log.d("ACT", "refreshGUI");
		dlctask = appService.getData();
		
		arrayAdapter = new ArrayAdapter<StreetDTO>(this, android.R.layout.simple_spinner_item, dlctask.getStreets());
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(arrayAdapter);
		
		renderData();
	}
	
	private BroadcastReceiver onDataReady = new BroadcastReceiver()
	{
		public void onReceive(Context context, Intent intent)
		{
			refreshGUI();
		}
	};
	
	private ServiceConnection onService = new ServiceConnection()
	{
		public void onServiceConnected(ComponentName className, IBinder rawBinder)
		{
			appService = ((LocalBinder) rawBinder).getService();
			Log.d("ACT", "service connected");
		}
		
		public void onServiceDisconnected(ComponentName className)
		{
			appService = null;
			Log.d("ACT", "service disconnected");
		}
	};
	
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		menu.add(Menu.NONE, REFRESH_ID, Menu.NONE, "Refresh")
//				.setIcon(android.R.drawable.ic_menu_rotate);
//		
//		menu.add(Menu.NONE, CLOSE_ID, Menu.NONE, "Exit")
//			.setIcon(android.R.drawable.ic_menu_close_clear_cancel);
//		
//		menu.add(Menu.NONE, START_AUTOUPDATE_ID, Menu.NONE, "Start autoupdate")
//			.setIcon(android.R.drawable.ic_menu_close_clear_cancel);
//		
//		menu.add(Menu.NONE, STOP_AUTOUPDATE_ID, Menu.NONE, "Stop autoupdate")
//		.setIcon(android.R.drawable.ic_menu_close_clear_cancel);
//
//		return(super.onCreateOptionsMenu(menu));
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		if (item.getItemId() == REFRESH_ID) {
//			appService.update();
//		}
//		else if (item.getItemId() == CLOSE_ID) {
//			finish();
//		}
//		else if (item.getItemId() == START_AUTOUPDATE_ID) {
//			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
//            am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), UPDATE_INTERVAL_SECONDS * 1000, mAlarmSender);
//            Toast.makeText(this, "Autoupdate started", Toast.LENGTH_LONG).show();
//		}
//		else if (item.getItemId() == STOP_AUTOUPDATE_ID) {
//			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
//            am.cancel(mAlarmSender);
//            Toast.makeText(this, "Autoupdate stopped", Toast.LENGTH_LONG).show();
//		}
//		return true;
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem m1 = menu.add(0, Const.menuSettings, Menu.NONE, R.string.settings);
		MenuItem m2 = menu.add(0, Const.menuRefresh, Menu.NONE, R.string.refresh);
		m1.setIcon(android.R.drawable.ic_menu_preferences);
		m2.setIcon(android.R.drawable.ic_menu_rotate);
		m1.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem _menuItem) {
				startActivity(new Intent(getApplicationContext(), PreferencesActivity.class));
				return true;
			}
		});
		m2.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem _menuItem) {
				appService.update();
				return true;
			}
		});
		return true;
	}

//	private void executeTask() {
//		if (dlctask.getUrl().equalsIgnoreCase(getResources().getString(R.string.urlDefaultValue)) || dlctask.getUrl().equalsIgnoreCase(Const.emptyString)) {
//			new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.warning)).setPositiveButton(getResources().getString(R.string.ok), null).setMessage(getResources().getString(R.string.noProvider)).show();
//		} else if (arrayAdapter.getCount() == 0) {
//			new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.warning)).setPositiveButton(getResources().getString(R.string.ok), null).setMessage(getResources().getString(R.string.noStreets)).show();
//		} else {
//			new DLCTask().execute(dlctask);
//		}
//	}

	private void renderData() {
		zoneListAdapter.setListItems(dlctask.getStreets().get(spinner.getSelectedItemPosition()).getZones());
		zoneView.setAdapter(zoneListAdapter);
		leftTextView.setText(dlctask.getStreets().get(spinner.getSelectedItemPosition()).getDirectionLeft());
		rightTextView.setText(dlctask.getStreets().get(spinner.getSelectedItemPosition()).getDirectionRight());
		if (dlctask.getTrafficTime() != null)
			centerTextView.setText(DateFormat.getTimeFormat(this).format(dlctask.getTrafficTime()));
	}

	
}