package it.localhost.trafficdroid.gui.task;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.core.Parser;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.exception.CoreException;
import it.localhost.trafficdroid.gui.activity.MainActivity;

import java.util.List;

import android.app.AlertDialog;
import android.os.AsyncTask;

public class DLCTask extends AsyncTask<List<StreetDTO>, Void, List<StreetDTO>> {
	private String error;

	@Override
	protected void onPreExecute() {
		error = null;
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
		if (error != null)
			new AlertDialog.Builder(MainActivity.this).setTitle(getResources().getText(R.string.error)).setMessage(error).setPositiveButton(getResources().getText(R.string.ok), null).show();
		else {
			allEnabledStreets = tratte;
			setView();
		}
	}
}
