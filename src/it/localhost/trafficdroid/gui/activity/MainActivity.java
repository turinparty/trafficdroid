package it.localhost.trafficdroid.gui.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.core.Parser;
import it.localhost.trafficdroid.dao.StreetDAO;
import it.localhost.trafficdroid.dao.TrafficDAO;
import it.localhost.trafficdroid.dto.DLCTaskDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.exception.CoreException;
import it.localhost.trafficdroid.exception.DaoException;
import it.localhost.trafficdroid.gui.adapter.ZoneListAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ListView zoneView;
	private TextView leftTextView;
	private TextView rightTextView;
	private TextView centerTextView;
	private SharedPreferences sharedPreferences;
	private Spinner spinner;
	private ArrayAdapter<StreetDTO> arrayAdapter;
	private ZoneListAdapter zoneListAdapter;
	private DLCTaskDTO dlctask;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		leftTextView = (TextView) findViewById(R.id.left);
		rightTextView = (TextView) findViewById(R.id.right);
		centerTextView = (TextView) findViewById(R.id.center);
		zoneView = (ListView) findViewById(R.id.zonelist);
		spinner = (Spinner) findViewById(R.id.spinner);
		zoneListAdapter = new ZoneListAdapter(MainActivity.this);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				setView();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		dlctask = new DLCTaskDTO(StreetDAO.getAllEnabled(sharedPreferences, getResources()), sharedPreferences.getString(getResources().getString(R.string.urlKey), Const.emptyString));
		new DLCTask().execute(dlctask);
		try {
			dlctask = TrafficDAO.retrieveData(getApplicationContext());
		} catch (DaoException e) {
			System.err.println(e.getKey() + ": " + e.getMessage());
			e.printStackTrace();
		}
		arrayAdapter = new ArrayAdapter<StreetDTO>(this, android.R.layout.simple_spinner_item, dlctask.getStreets());
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(arrayAdapter);
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
				onResume();
				return true;
			}
		});
		return true;
	}

	private void setView() {
		if (dlctask.getUrl().equalsIgnoreCase(getResources().getString(R.string.urlDefaultValue)) || dlctask.getUrl().equalsIgnoreCase(Const.emptyString)) {
			new AlertDialog.Builder(MainActivity.this).setTitle(getResources().getString(R.string.warning)).setPositiveButton(getResources().getString(R.string.ok), null).setMessage(getResources().getString(R.string.noProvider)).show();
		} else if (arrayAdapter.getCount() == 0) {
			new AlertDialog.Builder(MainActivity.this).setTitle(getResources().getString(R.string.warning)).setPositiveButton(getResources().getString(R.string.ok), null).setMessage(getResources().getString(R.string.noStreets)).show();
		} else {
			zoneListAdapter.setListItems(dlctask.getStreets().get(spinner.getSelectedItemPosition()).getZones());
			zoneView.setAdapter(zoneListAdapter);
			leftTextView.setText(dlctask.getStreets().get(spinner.getSelectedItemPosition()).getDirectionLeft());
			rightTextView.setText(dlctask.getStreets().get(spinner.getSelectedItemPosition()).getDirectionRight());
			if (dlctask.getTrafficTime() != null)
				centerTextView.setText(DateFormat.getTimeFormat(getApplicationContext()).format(dlctask.getTrafficTime()));
			else
				centerTextView.setText(Const.emptyString);
		}
	}

	private class DLCTask extends AsyncTask<DLCTaskDTO, Void, DLCTaskDTO> {
		private String error;

		@Override
		protected DLCTaskDTO doInBackground(DLCTaskDTO... param) {
			try {
				Parser.parse(param[0]);
				TrafficDAO.storeData(param[0], getApplicationContext());
			} catch (CoreException e) {
				param[0].setTrafficTime(null);
				error = e.getKey() + ": " + e.getMessage();
				e.printStackTrace();
			} catch (DaoException e) {
				param[0].setTrafficTime(null);
				error = e.getKey() + ": " + e.getMessage();
				e.printStackTrace();
			}
			return param[0];
		}

		@Override
		protected void onPostExecute(DLCTaskDTO param) {
			if (param == null)
				new AlertDialog.Builder(MainActivity.this).setTitle(getResources().getString(R.string.error)).setMessage(error).setPositiveButton(getResources().getString(R.string.ok), null).show();
			else {
				setView();
			}
		}
	}
}