package com.google.code.trafficdroid.gui;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.google.code.trafficdroid.R;
import com.google.code.trafficdroid.core.Parser;
import com.google.code.trafficdroid.core.TrattaListAdapter;
import com.google.code.trafficdroid.dto.ZoneDTO;

public class ParserActivity extends Activity {
	// private String PROTO_URL =
	// "http://traffico.octotelematics.com/dyn/#CITY#.gif?ts=1";
	// private List<Tratta> tratte = new ArrayList<Tratta>();
	private ListView tratteListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tratte);
		tratteListView = (ListView) findViewById(R.id.trattelist);
		new TratteDownloader().execute(1);
	}

	private class TratteDownloader extends AsyncTask<Integer, Void, List<ZoneDTO>> {
		private ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = ProgressDialog.show(ParserActivity.this, "Please wait", "Downloading tratte...", true);
		}

		protected List<ZoneDTO> doInBackground(Integer... params) {
			try {
				return Parser.parse(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(List<ZoneDTO> tratte) {
			dialog.dismiss();
			if (tratte == null) {
				new AlertDialog.Builder(ParserActivity.this).setTitle("Error").setMessage("Error downloading image").setPositiveButton("OK", null).show();
			} else {
				TrattaListAdapter tla = new TrattaListAdapter(ParserActivity.this);
				tla.setListItems(tratte);
				tratteListView.setAdapter(tla);
			}
		}
	}
}