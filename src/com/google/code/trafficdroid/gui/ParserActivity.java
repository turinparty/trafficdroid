package com.google.code.trafficdroid.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
import com.google.code.trafficdroid.dto.Tratta;

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

	private static String streamToString(InputStream ists) throws java.io.IOException {
		StringBuilder sb = new StringBuilder();
        String line;

        try {
            BufferedReader r1 = new BufferedReader(new InputStreamReader(ists));
            while ((line = r1.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } finally {
            ists.close();
        }
        return sb.toString();

	}

	private class TratteDownloader extends AsyncTask<Integer, Void, List<Tratta>> {
		private ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = ProgressDialog.show(ParserActivity.this, "Please wait", "Downloading tratte...", true);
		}

		protected List<Tratta> doInBackground(Integer... params) {
			try {
				URL u = new URL("http://traffico.octotelematics.com/dyn/" + params[0] + ".html?ts=1");
				URLConnection uc = u.openConnection();
				String string = streamToString(uc.getInputStream());
				return Parser.parse(string);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(List<Tratta> tratte) {
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