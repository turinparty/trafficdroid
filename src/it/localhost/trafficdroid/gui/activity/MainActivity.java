package it.localhost.trafficdroid.gui.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.LocalBinder;
import it.localhost.trafficdroid.core.UpdateService;
import it.localhost.trafficdroid.dto.DLCTaskDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.gui.ZoneListAdapter;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	private UpdateService appService = null;
	private Intent intentService;
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
		Log.w("ACT", "onCreate");
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.main);
		leftTextView = (TextView) findViewById(R.id.left);
		rightTextView = (TextView) findViewById(R.id.right);
		centerTextView = (TextView) findViewById(R.id.center);
		zoneView = (ListView) findViewById(R.id.zonelist);
		spinner = (Spinner) findViewById(R.id.spinner);
		zoneListAdapter = new ZoneListAdapter(this);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				viewStreet();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		intentService = new Intent(this, UpdateService.class);
		startService(intentService);
	}

	@Override
	protected void onStart() {
		Log.w("ACT", "onStart");
		super.onStart();
		bindService(intentService, onService, BIND_AUTO_CREATE);
	}

	@Override
	public void onResume() {
		Log.w("ACT", "onResume");
		super.onResume();
		setProgressBarIndeterminateVisibility(true);
		if (appService != null)
			refreshgui();
		//TODO gestire url nulla e no strade selezionate
		registerReceiver(onDataReady, new IntentFilter(Const.DATA_READY));
	}

	@Override
	public void onPause() {
		Log.w("ACT", "onPause");
		super.onPause();
		unregisterReceiver(onDataReady);
	}

	@Override
	protected void onStop() {
		Log.w("ACT", "onStop");
		super.onStop();
		unbindService(onService);
	}

	private void refreshgui() {
		Log.w("ACT", "refreshGUI");
		dlctask = appService.getData();
		arrayAdapter = new ArrayAdapter<StreetDTO>(MainActivity.this, android.R.layout.simple_spinner_item, dlctask.getStreets());
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(arrayAdapter);
		viewStreet();
		setProgressBarIndeterminateVisibility(false);
	}

	public void viewStreet() {
		zoneListAdapter.setListItems(dlctask.getStreets().get(spinner.getSelectedItemPosition()).getZones());
		zoneView.setAdapter(zoneListAdapter);
		leftTextView.setText(dlctask.getStreets().get(spinner.getSelectedItemPosition()).getDirectionLeft());
		rightTextView.setText(dlctask.getStreets().get(spinner.getSelectedItemPosition()).getDirectionRight());
		if (dlctask.getTrafficTime() != null) {
			//XXX per test
			//centerTextView.setText(DateFormat.getTimeFormat(this).format(dlctask.getTrafficTime()));
	        SimpleDateFormat sdf = new SimpleDateFormat("H:mm:ss");
	        centerTextView.setText(sdf.format(dlctask.getTrafficTime()));
		}
	}

	private BroadcastReceiver onDataReady = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			refreshgui();
		}
	};
	private ServiceConnection onService = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder rawBinder) {
			appService = ((LocalBinder) rawBinder).getService();
			refreshgui();
			Log.w("ACT", "service connected");
		}

		public void onServiceDisconnected(ComponentName className) {
			appService = null;
			Log.w("ACT", "service disconnected");
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem m1 = menu.add(0, Const.menuSettings, Menu.NONE, R.string.settings);
		MenuItem m2 = menu.add(0, Const.menuRefresh, Menu.NONE, R.string.refresh);
		m1.setIcon(android.R.drawable.ic_menu_preferences);
		m2.setIcon(android.R.drawable.ic_menu_rotate);
		m1.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem _menuItem) {
				startActivity(new Intent(MainActivity.this, PreferencesActivity.class));
				return true;
			}
		});
		m2.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem _menuItem) {
				setProgressBarIndeterminateVisibility(true);
				appService.updateDlcTask();
				return true;
			}
		});
		return true;
	}
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
