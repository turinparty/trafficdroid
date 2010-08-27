package it.localhost.trafficdroid.gui.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dao.StreetDAO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.gui.adapter.ZoneListAdapter;
import it.localhost.trafficdroid.gui.task.DLCTask;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
	private ListView listView;
	private TextView leftTextView;
	private TextView rightTextView;
	private SharedPreferences sharedPreferences;
	private Spinner spinner;
	private ArrayAdapter<StreetDTO> arrayAdapter;
	private List<StreetDTO> allEnabledStreets;
	private ZoneListAdapter trattaListAdapter;
	private String url;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		leftTextView = (TextView) findViewById(R.id.left);
		rightTextView = (TextView) findViewById(R.id.right);
		listView = (ListView) findViewById(R.id.trattelist);
		spinner = (Spinner) findViewById(R.id.spinner);
		trattaListAdapter = new ZoneListAdapter(MainActivity.this);
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
		allEnabledStreets = StreetDAO.getAllEnabled(sharedPreferences);
		new DLCTask().execute(allEnabledStreets);
		arrayAdapter = new ArrayAdapter<StreetDTO>(this, android.R.layout.simple_spinner_item, allEnabledStreets);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(arrayAdapter);
		url = sharedPreferences.getString(getResources().getText(R.string.urlKey).toString(), Const.emptyString);
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
		if (url.equalsIgnoreCase(getResources().getText(R.string.urlDefaultValue).toString()) || url.equalsIgnoreCase(Const.emptyString)) {
			new AlertDialog.Builder(MainActivity.this).setTitle(getResources().getText(R.string.warning)).setPositiveButton(getResources().getText(R.string.ok), null).setMessage(getResources().getText(R.string.noProvider)).show();
		} else if (arrayAdapter.getCount() == 0) {
			new AlertDialog.Builder(MainActivity.this).setTitle(getResources().getText(R.string.warning)).setPositiveButton(getResources().getText(R.string.ok), null).setMessage(getResources().getText(R.string.noStreets)).show();
		} else {
			trattaListAdapter.setListItems(allEnabledStreets.get(spinner.getSelectedItemPosition()).getZones());
			listView.setAdapter(trattaListAdapter);
			leftTextView.setText(allEnabledStreets.get(spinner.getSelectedItemPosition()).getDirections()[0]);
			rightTextView.setText(allEnabledStreets.get(spinner.getSelectedItemPosition()).getDirections()[1]);
		}
	}

	
}