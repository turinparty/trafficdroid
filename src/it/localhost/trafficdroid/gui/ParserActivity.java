package it.localhost.trafficdroid.gui;

import it.localhost.trafficdroid.core.Parser;
import it.localhost.trafficdroid.core.TrattaListAdapter;
import it.localhost.trafficdroid.dao.ContentDAO;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import it.localhost.trafficdroid.R;

public class ParserActivity extends Activity {
	private ListView tratteListView;
	private static final int[] autostradeNumeri = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 121, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 24, 241, 25, 26, 27, 28, 29, 30, 31, 32, 50, 51, 52, 55, 90, 91, 101, 102, 143, 103, 302, 303, 501, 701, 111, 131, 142, 141, 144, 161, 181, 211, 261, 262, 263, 291, 552, 551, 553, 56, 301 };
	
	private final int MENU_SETTINGS = 1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tratte);
		tratteListView = (ListView) findViewById(R.id.trattelist);
		final Spinner s = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.autostrade, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(adapter);
		Button okBtn = (Button) findViewById(R.id.okbtn);
		okBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int selected = s.getSelectedItemPosition();
				new TratteDownloader().execute(autostradeNumeri[selected]);
			}
		});
		
		SharedPreferences settings = getApplicationContext().getSharedPreferences(MainActivity.PREFS_NAME, MODE_PRIVATE);
		ContentDAO.url = settings.getString(MainActivity.KEY_URL, "");
	}
	
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		
		MenuItem m1 = menu.add(0, MENU_SETTINGS, Menu.NONE, R.string.settings);
		m1.setIcon(android.R.drawable.ic_menu_preferences);
		
		m1.setOnMenuItemClickListener(new OnMenuItemClickListener()
		{
			public boolean onMenuItemClick(MenuItem _menuItem)
			{
				startActivity(new Intent(ParserActivity.this, UrlRequestActivity.class));
				return true;
			}
		});
		
		return true;
	}

	private class TratteDownloader extends AsyncTask<Integer, Void, List<ZoneDTO>> {
		private ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = ProgressDialog.show(ParserActivity.this, getResources().getText(R.string.pleaswait), getResources().getText(R.string.downloading), true);
		}

		protected List<ZoneDTO> doInBackground(Integer... params) {
			try {
				return Parser.parse(params[0]);
			} catch (CoreException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(List<ZoneDTO> tratte) {
			dialog.dismiss();
			if (tratte == null) {
				new AlertDialog.Builder(ParserActivity.this).setTitle(getResources().getText(R.string.errore)).setMessage(getResources().getText(R.string.errordownloading)).setPositiveButton("OK", null).show();
			} else {
				TrattaListAdapter tla = new TrattaListAdapter(ParserActivity.this);
				tla.setListItems(tratte);
				tratteListView.setAdapter(tla);
			}
		}
	}
}