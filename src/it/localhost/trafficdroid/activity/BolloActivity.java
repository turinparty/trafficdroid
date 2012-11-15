package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.TdApp;
import it.localhost.trafficdroid.dao.BolloDAO;
import it.localhost.trafficdroid.dto.BolloDTO;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class BolloActivity extends AbstractActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.bollo);
		setProgressBarIndeterminateVisibility(false);
		final EditText targa = (EditText) findViewById(R.id.targa);
		findViewById(R.id.ok).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new RefreshTask().execute(targa.getText().toString());
			}
		});
	}

	private class RefreshTask extends AsyncTask<String, Void, BolloDTO> {
		private Exception e;

		@Override
		protected void onPreExecute() {
			setProgressBarIndeterminateVisibility(true);
			InputMethodManager im = (InputMethodManager) TdApp.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}

		@Override
		protected BolloDTO doInBackground(String... args) {
			try {
				return BolloDAO.getData(args[0]);
			} catch (Exception e) {
				this.e = e;
				return null;
			}
		}

		@Override
		protected void onPostExecute(BolloDTO bolloDto) {
			setProgressBarIndeterminateVisibility(false);
			if (this.e == null) {
				((TextView) findViewById(R.id.euro)).setText(bolloDto.getEuro());
				((TextView) findViewById(R.id.result)).setText(bolloDto.getBollo());
			} else
				new AlertDialog.Builder(BolloActivity.this).setTitle(R.string.error).setPositiveButton(R.string.ok, null).setMessage(e.getMessage()).show();
		}
	}
}
