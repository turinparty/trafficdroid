package it.localhost.trafficdroid.activity;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class MainActivity extends Activity {
	private TableLayout tableLayout;
	private LayoutInflater layoutInflater;
	private IntentFilter intentFilter;
	private OnClickListener webcamOnClickListener;
	private OnClickListener badNewsOnClickListener;
	private OnClickListener streetOnClickListener;
	private BroadcastReceiver receiver;
	private MainDTO mainDTO;
	private SharedPreferences sharedPreferences;
	private GoogleAnalyticsTracker tracker;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tracker = GoogleAnalyticsTracker.getInstance();
		tracker.startNewSession(Const.anlyticsId, this);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.linearlayout_main);
		intentFilter = new IntentFilter();
		intentFilter.addAction(Const.beginUpdate);
		intentFilter.addAction(Const.endUpdate);
		tableLayout = (TableLayout) findViewById(R.id.mainTable);
		layoutInflater = LayoutInflater.from(this);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		webcamOnClickListener = new OnClickListener() {
			public void onClick(View v) {
				String code = (String) v.getTag();
				if (code.charAt(0) == Const.webcamNone) {
					tracker.trackEvent(Const.eventCatWebcam, Const.eventActionNone, code, 0);
					new AlertDialog.Builder(MainActivity.this).setTitle(R.string.info).setPositiveButton(R.string.ok, null).setMessage(R.string.webcamNone).show();
				} else if (code.charAt(0) == Const.webcamTrue) {
					tracker.trackEvent(Const.eventCatWebcam, Const.eventActionOpen, code, 0);
					Intent intent = new Intent(MainActivity.this, WebcamActivity.class);
					intent.putExtra(Const.camId, code.substring(1));
					startActivity(intent);
				} else {
					tracker.trackEvent(Const.eventCatWebcam, Const.eventActionRequest, code, 0);
					new AlertDialog.Builder(MainActivity.this).setTitle(R.string.info).setPositiveButton(R.string.ok, null).setMessage(R.string.webcamAdd).show();
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
				for (BadNewsDTO event : street.getBadNews()) {
					View eventRow = layoutInflater.inflate(R.layout.linearlayout_badnews, null);
					((TextView) eventRow.findViewById(R.id.BNDText)).setText(event.getTitle() + " " + event.getDescription());
					// TODO icona personalizzata in base all'evento
					ll.addView(eventRow);
				}
				d.show();
			}
		};
		streetOnClickListener = new OnClickListener() {
			public void onClick(View v) {
				String streetVkey = (Integer) v.getTag(R.id.streetId) + "V";
				boolean streetVisible = !sharedPreferences.getBoolean(streetVkey, true);
				sharedPreferences.edit().putBoolean(streetVkey, streetVisible).commit();
				for (int i = (Integer) v.getTag(R.id.streetStart); i < (Integer) v.getTag(R.id.streetEnd); i++) {
					tableLayout.getChildAt(i).setVisibility(streetVisible ? View.VISIBLE : View.GONE);
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
		tracker.trackPageView(this.getClass().getName());
		registerReceiver(receiver, intentFilter);
		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(Const.notificationId);
		if (sharedPreferences.getString(getString(R.string.providerTrafficKey), getString(R.string.providerTrafficDefault)).equals(getString(R.string.providerTrafficDefault)))
			new AlertDialog.Builder(this).setTitle(R.string.warning).setPositiveButton(R.string.ok, null).setMessage(R.string.badConf).show();
		else if (sharedPreferences.getBoolean(getString(R.string.berserkKey), Boolean.parseBoolean(getString(R.string.berserkDefault))))
			sendBroadcast(Const.doUpdateIntent);
		refresh();
	}

	@Override
	public void onPause() {
		super.onPause();
		tracker.dispatch();
		unregisterReceiver(receiver);
	}

	@Override
	public void onStop() {
		super.onStop();
		tracker.stopSession();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menusettings:
			startActivity(new Intent(MainActivity.this, PreferencesActivity.class));
			return true;
		case R.id.menurefresh:
			sendBroadcast(Const.doUpdateIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void refresh() {
		try {
			tableLayout.removeAllViews();
			mainDTO = MainDAO.retrieve(this);
			if (mainDTO.getTrafficTime() != null)
				setTitle(getString(R.string.app_name) + " " + DateFormat.getTimeFormat(this).format(mainDTO.getTrafficTime()));
			else
				setTitle(R.string.app_name);
			for (int i = 0; i < mainDTO.getStreets().size(); i++) {
				StreetDTO street = mainDTO.getStreets().get(i);
				boolean streetVisible = sharedPreferences.getBoolean(street.getId() + "V", true);
				TableRow streetRow = (TableRow) layoutInflater.inflate(R.layout.tablerow_street, tableLayout, false);
				((TextView) streetRow.findViewById(R.id.streetName)).setText(street.getName());
				((TextView) streetRow.findViewById(R.id.streetDirLeft)).setText(street.getDirectionLeft());
				((TextView) streetRow.findViewById(R.id.streetDirRight)).setText(street.getDirectionRight());
				tableLayout.addView(streetRow);
				streetRow.setTag(R.id.streetId, street.getId());
				streetRow.setTag(R.id.streetStart, tableLayout.getChildCount());
				if (street.getBadNews() != null && street.getBadNews().size() != 0) {
					TableRow badNewsRow = (TableRow) layoutInflater.inflate(R.layout.tablerow_badnews, tableLayout, false);
					((TextView) badNewsRow.findViewById(R.id.BNTText)).setText(Integer.toString(street.getBadNews().size()));
					badNewsRow.setTag(i);
					badNewsRow.setOnClickListener(badNewsOnClickListener);
					badNewsRow.setVisibility(streetVisible ? View.VISIBLE : View.GONE);
					tableLayout.addView(badNewsRow);
				}
				for (ZoneDTO zoneDTO : street.getZones()) {
					TableRow zoneNameRow = (TableRow) layoutInflater.inflate(R.layout.tablerow_zonefirst, tableLayout, false);
					TableRow zoneSpeedRow = (TableRow) layoutInflater.inflate(R.layout.tablerow_zonesecond, tableLayout, false);
					TextView zoneNameText = (TextView) zoneNameRow.findViewById(R.id.zoneName);
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
					if (zoneDTO.getId().charAt(0) == Const.webcamTrue)
						cam.setImageResource(android.R.drawable.ic_menu_camera);
					else if (zoneDTO.getId().charAt(0) == Const.webcamNone)
						cam.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
					else
						cam.setImageResource(android.R.drawable.ic_menu_add);
					zoneNameRow.setVisibility(streetVisible ? View.VISIBLE : View.GONE);
					zoneSpeedRow.setVisibility(streetVisible ? View.VISIBLE : View.GONE);
					tableLayout.addView(zoneNameRow);
					tableLayout.addView(zoneSpeedRow);
				}
				streetRow.setTag(R.id.streetEnd, tableLayout.getChildCount());
				streetRow.setOnClickListener(streetOnClickListener);
			}
			TableRow badNewsRow = (TableRow) layoutInflater.inflate(R.layout.tablerow_badnews, tableLayout, false);
			((TextView) badNewsRow.findViewById(R.id.BNTText)).setText(Integer.toString(mainDTO.getOtherBadNews().size()));
			tableLayout.addView(badNewsRow);
		} catch (TdException e) {
			if (e.getKey() == TdException.FileNotFoundException)
				sendBroadcast(Const.doUpdateIntent);
			else
				e.printStackTrace();
		}
	}
}