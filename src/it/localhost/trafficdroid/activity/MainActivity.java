package it.localhost.trafficdroid.activity;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdException;
import it.localhost.trafficdroid.dao.MainDAO;
import it.localhost.trafficdroid.dto.BadNewsDTO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
	private AdView adView;
	private TableLayout tableLayout;
	private LayoutInflater layoutInflater;
	private IntentFilter intentFilter;
	private OnClickListener webcamOnClickListener;
	private OnClickListener badNewsOnClickListener;
	private OnClickListener streetOnClickListener;
	private BroadcastReceiver receiver;
	private MainDTO mainDTO;
	private SharedPreferences sharedPreferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.main);
		intentFilter = new IntentFilter();
		intentFilter.addAction(Const.beginUpdate);
		intentFilter.addAction(Const.endUpdate);
		tableLayout = (TableLayout) findViewById(R.id.mainTable);
		layoutInflater = LayoutInflater.from(this);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		adView = new AdView(this, AdSize.BANNER, Const.adMobId);
		((LinearLayout) findViewById(R.id.mainLayout)).addView(adView);
		webcamOnClickListener = new OnClickListener() {
			public void onClick(View v) {
				String code = (String) v.getTag();
				String url = sharedPreferences.getString(getString(R.string.providerCamKey), getString(R.string.providerCamDefault));
				if (code.charAt(0) != Const.z)
					new AlertDialog.Builder(MainActivity.this).setTitle(getString(R.string.info)).setPositiveButton(getString(R.string.ok), null).setMessage(getString(R.string.help)).show();
				else if (url.equals(getString(R.string.providerCamDefault)))
					new AlertDialog.Builder(MainActivity.this).setTitle(getString(R.string.warning)).setPositiveButton(getString(R.string.ok), null).setMessage(getString(R.string.badWebcamConf)).show();
				else if (code.charAt(0) == Const.z && !url.equals(getString(R.string.providerCamDefault))) {
					Intent intent = new Intent(MainActivity.this, WebcamActivity.class);
					intent.putExtra(Const.camId, code.substring(1));
					startActivity(intent);
				}
			}
		};
		badNewsOnClickListener = new OnClickListener() {
			public void onClick(View v) {
				StreetDTO street = mainDTO.getStreets().get((Integer) v.getTag());
				Dialog d = new Dialog(MainActivity.this);
				ScrollView sv = new ScrollView(MainActivity.this);
				LinearLayout ll = new LinearLayout(MainActivity.this);
				d.setTitle(street.getName());
				ll.setOrientation(LinearLayout.VERTICAL);
				d.setContentView(sv);
				sv.addView(ll);
				for (BadNewsDTO event : street.getEvents()) {
					View eventRow = layoutInflater.inflate(R.layout.badnewsdialog, null);
					((TextView) eventRow.findViewById(R.id.BNDText)).setText(event.getTitle() + " " + event.getDescription());
					// TODO icona personalizzata in base all'evento
					ll.addView(eventRow);
				}
				d.show();
			}
		};
		streetOnClickListener = new OnClickListener() {
			public void onClick(View v) {
				for (int i = (Integer) v.getTag(R.id.streetStart); i < (Integer) v.getTag(R.id.streetEnd); i++) {
					if (tableLayout.getChildAt(i) == null)
						System.err.println(i);
					tableLayout.getChildAt(i).setVisibility(tableLayout.getChildAt(i).getVisibility() != View.VISIBLE ? View.VISIBLE : View.GONE);
				}
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
		adView.loadAd(new AdRequest());
		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(Const.notificationId);
		if (sharedPreferences.getString(getString(R.string.providerTrafficKey), getString(R.string.providerTrafficDefault)).equals(getString(R.string.providerTrafficDefault)))
			new AlertDialog.Builder(this).setTitle(getString(R.string.warning)).setPositiveButton(getString(R.string.ok), null).setMessage(getString(R.string.badConf)).show();
		else if (sharedPreferences.getString(getString(R.string.providerBadNewsKey), getString(R.string.providerBadNewsDefault)).equals(getString(R.string.providerBadNewsDefault)))
			new AlertDialog.Builder(this).setTitle(getString(R.string.warning)).setPositiveButton(getString(R.string.ok), null).setMessage(getString(R.string.badBadNewsConf)).show();
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
			mainDTO = MainDAO.retrieve(this);
			tableLayout.removeAllViews();
			setTitle(getString(R.string.app_name) + " " + DateFormat.getTimeFormat(this).format(mainDTO.getTrafficTime()));
			for (int i = 0; i < mainDTO.getStreets().size(); i++) {
				StreetDTO street = mainDTO.getStreets().get(i);
				TableRow streetRow = (TableRow) layoutInflater.inflate(R.layout.street, tableLayout, false);
				((TextView) streetRow.findViewById(R.id.streetName)).setText(street.getName());
				((TextView) streetRow.findViewById(R.id.streetDirLeft)).setText(street.getDirectionLeft());
				((TextView) streetRow.findViewById(R.id.streetDirRight)).setText(street.getDirectionRight());
				tableLayout.addView(streetRow);
				streetRow.setTag(R.id.streetStart, tableLayout.getChildCount());
				if (street.getEvents() != null && street.getEvents().size() != 0) {
					TableRow eventRow = (TableRow) layoutInflater.inflate(R.layout.badnewstable, tableLayout, false);
					((TextView) eventRow.findViewById(R.id.BNTText)).setText(Integer.toString(street.getEvents().size()));
					eventRow.setTag(i);
					eventRow.setOnClickListener(badNewsOnClickListener);
					tableLayout.addView(eventRow);
				}
				streetRow.setTag(R.id.streetEnd, tableLayout.getChildCount() + street.getZones().size() * 2);
				streetRow.setOnClickListener(streetOnClickListener);
				for (ZoneDTO zoneDTO : street.getZones()) {
					TableRow zoneNameRow = (TableRow) layoutInflater.inflate(R.layout.zonefirst, tableLayout, false);
					TableRow zoneSpeedRow = (TableRow) layoutInflater.inflate(R.layout.zonesecond, tableLayout, false);
					TextView zoneNameText = (TextView) zoneNameRow.findViewById(R.id.zoneNameaaa);
					TextView leftZoneSpeedText = (TextView) zoneSpeedRow.findViewById(R.id.zoneSpeedLeft);
					TextView rightZoneSpeedText = (TextView) zoneSpeedRow.findViewById(R.id.zoneSpeedRight);
					zoneNameText.setText(zoneDTO.getName());
					leftZoneSpeedText.setText(zoneDTO.getSpeedLeft());
					rightZoneSpeedText.setText(zoneDTO.getSpeedRight());
					leftZoneSpeedText.setTextColor(Const.colorCat[zoneDTO.getCatLeft()]);
					rightZoneSpeedText.setTextColor(Const.colorCat[zoneDTO.getCatRight()]);
					leftZoneSpeedText.setTypeface(zoneDTO.getCatLeft() == 1 ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
					rightZoneSpeedText.setTypeface(zoneDTO.getCatRight() == 1 ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
					zoneSpeedRow.setTag(zoneDTO.getId());
					zoneSpeedRow.setOnClickListener(webcamOnClickListener);
					ImageView cam = (ImageView) zoneSpeedRow.findViewById(R.id.zoneCam);
					if (zoneDTO.getId().charAt(0) == Const.z)
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