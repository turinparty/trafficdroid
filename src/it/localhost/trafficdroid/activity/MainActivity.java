package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.adapter.AbstractItem;
import it.localhost.trafficdroid.adapter.ListViewAdapter;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dao.MainDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends AbstractActivity {
	private ListView listView;
	private IntentFilter intentFilter;
	private BroadcastReceiver receiver;
	private MainDTO mainDTO;
	private SharedPreferences sharedPreferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		setTheme(Const.themes[Integer.parseInt(sharedPreferences.getString(getString(R.string.themeKey), getString(R.string.themeDefault)))]);
		super.onCreate(savedInstanceState);
		trackEvent(Const.eventCatApp, Const.eventActionVersion, versionName());
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.main);
		intentFilter = new IntentFilter();
		intentFilter.addAction(Const.beginUpdate);
		intentFilter.addAction(Const.endUpdate);
		listView = (ListView) findViewById(R.id.mainTable);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				((AbstractItem) view.getTag()).onClick();
			}
		});
		//		streetOnClickListener = new OnClickListener() {
		//			public void onClick(View v) {
		//				String streetVkey = (Integer) v.getTag(R.id.streetId) + "V";
		//				boolean streetVisible = !sharedPreferences.getBoolean(streetVkey, true);
		//				sharedPreferences.edit().putBoolean(streetVkey, streetVisible).commit();
		//				for (int i = (Integer) v.getTag(R.id.streetStart); i < (Integer) v.getTag(R.id.streetEnd); i++) {
		//					tableLayout.getChildAt(i).setVisibility(streetVisible ? View.VISIBLE : View.GONE);
		//				}
		//			}
		//		};
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
		if (sharedPreferences.getString(getString(R.string.providerTrafficKey), getString(R.string.providerTrafficDefault)).equals(
				getString(R.string.providerTrafficDefault)))
			new AlertDialog.Builder(this).setTitle(R.string.warning).setPositiveButton(R.string.ok, null).setMessage(R.string.badConf).show();
		else if (sharedPreferences.getBoolean(getString(R.string.berserkKey), Boolean.parseBoolean(getString(R.string.berserkDefault))))
			sendBroadcast(Const.doUpdateIntent);
		refresh();
	}

	@Override
	public void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
	}

	@Override
	public void onStop() {
		super.onStop();
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
		case R.id.menufuel:
			Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
			String provider = sharedPreferences.getString(getString(R.string.providerFuelKey), getString(R.string.providerFuelDefault));
			intent.putExtra(Const.url, Const.http + provider + Const.fuel);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void refresh() {
		mainDTO = MainDAO.retrieve(getApplicationContext());
		if (sharedPreferences.getBoolean(Const.exceptionCheck, false)) {
			String msg = sharedPreferences.getString(Const.exceptionName, null) + ": " + sharedPreferences.getString(Const.exceptionMsg, null);
			new AlertDialog.Builder(this).setTitle(R.string.error).setPositiveButton(R.string.ok, null).setMessage(msg).show();
			setTitle(msg);
		} else {
			if (mainDTO != null && mainDTO.getTrafficTime() != null) {
				setTitle(getString(R.string.app_name) + ": " + DateFormat.getTimeFormat(this).format(mainDTO.getTrafficTime()));
				ListViewAdapter adapter = new ListViewAdapter(this, mainDTO);
				listView.setAdapter(adapter);
			}
		}
	}
}