package it.localhost.trafficdroid.gui;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.core.Parser;
import it.localhost.trafficdroid.dao.StreetDAO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.exception.CoreException;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
	private TrattaListAdapter trattaListAdapter;
	private OnItemSelectedListener onItemSelectedListener;
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
		trattaListAdapter = new TrattaListAdapter(MainActivity.this);
		onItemSelectedListener = new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				setView();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		};
	}

	@Override
	public void onResume() {
		super.onResume();
		allEnabledStreets = StreetDAO.getAllEnabled(sharedPreferences);
		arrayAdapter = new ArrayAdapter<StreetDTO>(this, android.R.layout.simple_spinner_item, allEnabledStreets);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(arrayAdapter);
		url = sharedPreferences.getString(getResources().getText(R.string.urlKey).toString(), Const.emptyString);
		if (url.equalsIgnoreCase(getResources().getText(R.string.urlDefaultValue).toString()) || url.equalsIgnoreCase(Const.emptyString)) {
			spinner.setOnItemSelectedListener(null);
			new AlertDialog.Builder(MainActivity.this).setTitle(getResources().getText(R.string.warning)).setPositiveButton(getResources().getText(R.string.ok), null).setMessage(getResources().getText(R.string.noProvider)).show();
		} else if (arrayAdapter.getCount() == 0) {
			spinner.setOnItemSelectedListener(null);
			new AlertDialog.Builder(MainActivity.this).setTitle(getResources().getText(R.string.warning)).setPositiveButton(getResources().getText(R.string.ok), null).setMessage(getResources().getText(R.string.noStreets)).show();
		} else
			spinner.setOnItemSelectedListener(onItemSelectedListener);
		new TratteDownloader().execute(allEnabledStreets);
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
		trattaListAdapter.setListItems(allEnabledStreets.get(spinner.getSelectedItemPosition()).getZones());
		listView.setAdapter(trattaListAdapter);
		leftTextView.setText(allEnabledStreets.get(spinner.getSelectedItemPosition()).getDirections()[0]);
		rightTextView.setText(allEnabledStreets.get(spinner.getSelectedItemPosition()).getDirections()[1]);
	}

	private class TratteDownloader extends AsyncTask<List<StreetDTO>, Void, List<StreetDTO>> {
		private ProgressDialog dialog;
		private String error;

		@Override
		protected void onPreExecute() {
			error = null;
			dialog = ProgressDialog.show(MainActivity.this, getResources().getText(R.string.pleaswait), getResources().getText(R.string.downloading), true);
		}

		@Override
		protected List<StreetDTO> doInBackground(List<StreetDTO>... params) {
			error = null;
			try {
				for (StreetDTO elem : params[0])
					elem = Parser.parse(elem, url);
				return params[0];
			} catch (CoreException e) {
				error = e.getKey() + ": " + e.getMessage();
				return null;
			}
		}

		@Override
		protected void onPostExecute(List<StreetDTO> tratte) {
			dialog.dismiss();
			if (error != null)
				new AlertDialog.Builder(MainActivity.this).setTitle(getResources().getText(R.string.error)).setMessage(error).setPositiveButton(getResources().getText(R.string.ok), null).show();
			else {
				allEnabledStreets = tratte;
				setView();
			}
		}
	}
}