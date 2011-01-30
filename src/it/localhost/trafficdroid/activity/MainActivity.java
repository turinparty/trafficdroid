package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdException;
import it.localhost.trafficdroid.dao.MainDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import it.localhost.trafficdroid.listener.WebcamOnClickListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
	private MainDTO dlctask;
	private TableLayout tableLayout;
	private LayoutInflater layoutInflater;
	private TextView leftTextView;
	private TextView rightTextView;
	private TextView centerTextView;
	private Spinner spinner;
	private int spinnerPosition;
	private ArrayAdapter<StreetDTO> arrayAdapter;
	private IntentFilter intentFilter;
	private WebcamOnClickListener webcamOnClickListener;
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
		tableLayout = (TableLayout) findViewById(R.id.zonelist);
		spinner = (Spinner) findViewById(R.id.spinner);
		layoutInflater = LayoutInflater.from(this);
		webcamOnClickListener = new WebcamOnClickListener(this);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				viewStreet();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		registerReceiver(receiver, intentFilter);
		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(Const.notificationId);
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		if (sharedPreferences.getString(getString(R.string.providerTrafficKey), getString(R.string.providerTrafficDefault)).equals(getString(R.string.providerTrafficDefault)))
			new AlertDialog.Builder(this).setTitle(getString(R.string.warning)).setPositiveButton(getString(R.string.ok), null).setMessage(getString(R.string.badConf)).show();
		else {
			if (sharedPreferences.getBoolean(getString(R.string.berserkKey), Boolean.parseBoolean(getString(R.string.berserkDefault))))
				sendBroadcast(Const.doUpdateIntent);
			refreshgui();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem menuSettings = menu.add(0, Const.menuSettings, Menu.NONE, R.string.settings);
		MenuItem menuRefresh = menu.add(0, Const.menuRefresh, Menu.NONE, R.string.refresh);
		menuSettings.setIcon(android.R.drawable.ic_menu_preferences);
		menuRefresh.setIcon(android.R.drawable.ic_menu_rotate);
		menuSettings.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem _menuItem) {
				startActivity(new Intent(MainActivity.this, PreferencesActivity.class));
				return true;
			}
		});
		menuRefresh.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem _menuItem) {
				sendBroadcast(Const.doUpdateIntent);
				return true;
			}
		});
		return true;
	}

	private void refreshgui() {
		try {
			dlctask = MainDAO.retrieve(this);
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
			tableLayout.removeAllViews();
			for (ZoneDTO zoneDTO : dlctask.getStreets().get(spinner.getSelectedItemPosition()).getZones()) {
				TableRow nameRow = (TableRow) layoutInflater.inflate(R.layout.zonename, tableLayout, false);
				TableRow speedRow = (TableRow) layoutInflater.inflate(R.layout.zone, tableLayout, false);
				TextView centerTextView = (TextView) nameRow.findViewById(R.id.zoneName);
				TextView leftTextView = (TextView) speedRow.findViewById(R.id.speedLeft);
				TextView rightTextView = (TextView) speedRow.findViewById(R.id.speedRight);
				centerTextView.setText(zoneDTO.getName());
				leftTextView.setText(zoneDTO.getSpeedLeft());
				rightTextView.setText(zoneDTO.getSpeedRight());
				leftTextView.setTextColor(Const.colorCat[zoneDTO.getCatLeft()]);
				rightTextView.setTextColor(Const.colorCat[zoneDTO.getCatRight()]);
				leftTextView.setTypeface((zoneDTO.getCatLeft() == 1) ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
				rightTextView.setTypeface((zoneDTO.getCatRight() == 1) ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
				speedRow.setTag(zoneDTO.getId());
				speedRow.setOnClickListener(webcamOnClickListener);
				ImageView cam = (ImageView) speedRow.findViewById(R.id.cam);
				if (zoneDTO.getId().charAt(0) == 'z')
					cam.setImageResource(android.R.drawable.ic_menu_camera);
				else
					cam.setImageResource(android.R.drawable.ic_menu_add);
				tableLayout.addView(nameRow);
				tableLayout.addView(speedRow);
			}
			leftTextView.setText(dlctask.getStreets().get(spinner.getSelectedItemPosition()).getDirectionLeft());
			rightTextView.setText(dlctask.getStreets().get(spinner.getSelectedItemPosition()).getDirectionRight());
			if (dlctask.getTrafficTime() != null)
				centerTextView.setText(DateFormat.getTimeFormat(this).format(dlctask.getTrafficTime()));
			//centerTextView.setText(new java.text.SimpleDateFormat("H:mm:ss").format(dlctask.getTrafficTime()));
		} else {
			leftTextView.setText(null);
			rightTextView.setText(null);
			centerTextView.setText(null);
		}
	}
}