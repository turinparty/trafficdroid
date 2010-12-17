package it.localhost.trafficdroid.gui.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdException;
import it.localhost.trafficdroid.dao.TrafficDAO;
import it.localhost.trafficdroid.dto.DLCTaskDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.gui.ZoneListAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	private DLCTaskDTO dlctask;
	private ListView zoneView;
	private TextView leftTextView;
	private TextView rightTextView;
	private TextView centerTextView;
	private Spinner spinner;
	private int spinnerPosition;
	private ArrayAdapter<StreetDTO> arrayAdapter;
	private IntentFilter intentFilter;
	private BroadcastReceiver receiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(Const.beginUpdate)) {
				setProgressBarIndeterminateVisibility(true);
			} else if (intent.getAction().equals(Const.endUpdate)) {
				setProgressBarIndeterminateVisibility(false);
				refreshgui();
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.main);
		intentFilter = new IntentFilter();
		intentFilter.addAction(Const.beginUpdate);
		intentFilter.addAction(Const.endUpdate);
		leftTextView = (TextView) findViewById(R.id.left);
		rightTextView = (TextView) findViewById(R.id.right);
		centerTextView = (TextView) findViewById(R.id.center);
		zoneView = (ListView) findViewById(R.id.zonelist);
		spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				viewStreet();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		zoneView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String zoneId = dlctask.getStreets().get(spinner.getSelectedItemPosition()).getZones().get(position).getId();
				if (zoneId.charAt(0) == 'z') {
					Intent intent = new Intent(MainActivity.this, WebcamActivity.class);
					intent.putExtra(Const.camId, zoneId.substring(1));
					startActivity(intent);
				}
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(Const.notificationId);
		String url = PreferenceManager.getDefaultSharedPreferences(this).getString(getResources().getString(R.string.providerTrafficKey), Const.emptyString);
		if (url.equals(Const.emptyString) || url.equals(getResources().getString(R.string.providerTrafficDefaultValue)))
			new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.warning)).setPositiveButton(getResources().getString(R.string.ok), null).setMessage(getResources().getString(R.string.badConf)).show();
		else
			refreshgui();
		registerReceiver(receiver, intentFilter);
	}

	@Override
	public void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
	}

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
				sendBroadcast(Const.doUpdateIntent);
				return true;
			}
		});
		return true;
	}

	private void refreshgui() {
		try {
			dlctask = TrafficDAO.retrieveData(this);
			arrayAdapter = new ArrayAdapter<StreetDTO>(MainActivity.this, android.R.layout.simple_spinner_item, dlctask.getStreets());
			arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(arrayAdapter);
			if (spinnerPosition < dlctask.getStreets().size())
				spinner.setSelection(spinnerPosition);
			viewStreet();
		} catch (TdException e) {
			if (e.getKey() != TdException.FileNotFoundException)
				e.printStackTrace();
		}
	}

	public void viewStreet() {
		if (dlctask.getStreets().size() > 0) {
			spinnerPosition = spinner.getSelectedItemPosition();
			zoneView.setAdapter(new ZoneListAdapter(this, dlctask.getStreets().get(spinner.getSelectedItemPosition()).getZones()));
			leftTextView.setText(dlctask.getStreets().get(spinner.getSelectedItemPosition()).getDirectionLeft());
			rightTextView.setText(dlctask.getStreets().get(spinner.getSelectedItemPosition()).getDirectionRight());
			if (dlctask.getTrafficTime() != null)
				centerTextView.setText(DateFormat.getTimeFormat(this).format(dlctask.getTrafficTime()));
			//centerTextView.setText(new java.text.SimpleDateFormat("H:mm:ss").format(dlctask.getTrafficTime()));
		} else {
			zoneView.setAdapter(null);
			leftTextView.setText(null);
			rightTextView.setText(null);
			centerTextView.setText(null);
		}
	}
}