package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdException;
import it.localhost.trafficdroid.dao.MainDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
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
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TableLayout tableLayout;
	private LayoutInflater layoutInflater;
	private IntentFilter intentFilter;
	private OnClickListener webcamOnClickListener;
	private BroadcastReceiver receiver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.main);
		intentFilter = new IntentFilter();
		intentFilter.addAction(Const.beginUpdate);
		intentFilter.addAction(Const.endUpdate);
		tableLayout = (TableLayout) findViewById(R.id.zonelist);
		layoutInflater = LayoutInflater.from(this);
		webcamOnClickListener = new OnClickListener() {
			public void onClick(View v) {
				String code = (String) v.getTag();
				if (code.charAt(0) == 'z') {
					Intent intent = new Intent(MainActivity.this, WebcamActivity.class);
					intent.putExtra(Const.camId, code.substring(1));
					startActivity(intent);
				} else
					new AlertDialog.Builder(MainActivity.this).setTitle(getResources().getString(R.string.info)).setPositiveButton(getResources().getString(R.string.ok), null).setMessage(getResources().getString(R.string.help)).show();
			}
		};
		receiver = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (intent.getAction().equals(Const.beginUpdate)) {
					setProgressBarIndeterminateVisibility(true);
				} else if (intent.getAction().equals(Const.endUpdate)) {
					setProgressBarIndeterminateVisibility(false);
					refresh();
				}
			}
		};
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
			refresh();
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

	private void refresh() {
		try {
			MainDTO mainDTO = MainDAO.retrieve(this);
			tableLayout.removeAllViews();
			setTitle(getString(R.string.app_name) + " " + DateFormat.getTimeFormat(this).format(mainDTO.getTrafficTime()));
			for (StreetDTO street : mainDTO.getStreets()) {
				TableRow streetRow = (TableRow) layoutInflater.inflate(R.layout.street, tableLayout, false);
				((TextView) streetRow.findViewById(R.id.streetName)).setText(street.getName());
				((TextView) streetRow.findViewById(R.id.left)).setText(street.getDirectionLeft());
				((TextView) streetRow.findViewById(R.id.right)).setText(street.getDirectionRight());
				tableLayout.addView(streetRow);
				for (ZoneDTO zoneDTO : street.getZones()) {
					TableRow zoneNameRow = (TableRow) layoutInflater.inflate(R.layout.zonename, tableLayout, false);
					TableRow zoneSpeedRow = (TableRow) layoutInflater.inflate(R.layout.zone, tableLayout, false);
					TextView zoneNameText = (TextView) zoneNameRow.findViewById(R.id.zoneName);
					TextView leftZoneSpeedText = (TextView) zoneSpeedRow.findViewById(R.id.speedLeft);
					TextView rightZoneSpeedText = (TextView) zoneSpeedRow.findViewById(R.id.speedRight);
					zoneNameText.setText(zoneDTO.getName());
					leftZoneSpeedText.setText(zoneDTO.getSpeedLeft());
					rightZoneSpeedText.setText(zoneDTO.getSpeedRight());
					leftZoneSpeedText.setTextColor(Const.colorCat[zoneDTO.getCatLeft()]);
					rightZoneSpeedText.setTextColor(Const.colorCat[zoneDTO.getCatRight()]);
					leftZoneSpeedText.setTypeface((zoneDTO.getCatLeft() == 1) ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
					rightZoneSpeedText.setTypeface((zoneDTO.getCatRight() == 1) ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
					zoneSpeedRow.setTag(zoneDTO.getId());
					zoneSpeedRow.setOnClickListener(webcamOnClickListener);
					ImageView cam = (ImageView) zoneSpeedRow.findViewById(R.id.cam);
					if (zoneDTO.getId().charAt(0) == 'z')
						cam.setImageResource(android.R.drawable.ic_menu_camera);
					else
						cam.setImageResource(android.R.drawable.ic_menu_add);
					tableLayout.addView(zoneNameRow);
					tableLayout.addView(zoneSpeedRow);
				}
			}
		} catch (TdException e) {
			if (e.getKey() != TdException.FileNotFoundException)
				e.printStackTrace();
		}
	}
}