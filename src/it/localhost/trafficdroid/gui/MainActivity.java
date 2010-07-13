package it.localhost.trafficdroid.gui;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.core.Parser;
import it.localhost.trafficdroid.dao.StreetDAO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
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
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends Activity {
	private ListView tratteListView;
	private SharedPreferences settings;
	private Spinner spinner;
	private ArrayAdapter<StreetDTO> adapter;
	private Button okBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		settings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		tratteListView = (ListView) findViewById(R.id.trattelist);
		spinner = (Spinner) findViewById(R.id.spinner);
		okBtn = (Button) findViewById(R.id.okbtn);
	}

	@Override
	public void onResume() {
		super.onResume();
		adapter = new ArrayAdapter<StreetDTO>(this, android.R.layout.simple_spinner_item, StreetDAO.getAllEnabled(settings));
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		okBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new TratteDownloader().execute(adapter.getItem(spinner.getSelectedItemPosition()).getCode());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem m1 = menu.add(0, Const.menuSettings, Menu.NONE, R.string.settings);
		m1.setIcon(android.R.drawable.ic_menu_preferences);
		m1.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem _menuItem) {
				startActivity(new Intent(MainActivity.this, PreferencesActivity.class));
				return true;
			}
		});
		return true;
	}

	private class TratteDownloader extends AsyncTask<Integer, Void, List<ZoneDTO>> {
		private ProgressDialog dialog;
		private String error;

		@Override
		protected void onPreExecute() {
			error = null;
			dialog = ProgressDialog.show(MainActivity.this, getResources().getText(R.string.pleaswait), getResources().getText(R.string.downloading), true);
		}

		@Override
		protected List<ZoneDTO> doInBackground(Integer... params) {
			String url = settings.getString(getResources().getText(R.string.urlKey).toString(), Const.emptyString);
			List<ZoneDTO> output = null;
			try {
				output = Parser.parse(params[0], url);
			} catch (CoreException e) {
				error = e.getKey() + ": " + e.getMessage();
			}
			return output;
		}

		@Override
		protected void onPostExecute(List<ZoneDTO> tratte) {
			dialog.dismiss();
			if (error != null)
				new AlertDialog.Builder(MainActivity.this).setTitle(getResources().getText(R.string.errore)).setMessage(error).setPositiveButton(getResources().getText(R.string.ok), null).show();
			else {
				TrattaListAdapter tla = new TrattaListAdapter(MainActivity.this);
				tla.setListItems(tratte);
				tratteListView.setAdapter(tla);
			}
		}
	}
}