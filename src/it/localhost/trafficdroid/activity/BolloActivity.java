package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.premium.R;
import it.localhost.trafficdroid.common.TdApp;
import it.localhost.trafficdroid.dao.BolloDAO;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class BolloActivity extends AbstractActivity {
	private static final String BLANK = "";
	private static final String targaKey = "targa";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.bollo);
		setProgressBarIndeterminateVisibility(false);
		final EditText targa = (EditText) findViewById(R.id.targa);
		targa.setText(TdApp.getPrefString(targaKey, BLANK));
		findViewById(R.id.ok).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				TdApp.getEditor().putString(targaKey, targa.getText().toString()).commit();
				new RefreshTask().execute(targa.getText().toString());
			}
		});
	}

	private class RefreshTask extends AsyncTask<String, Void, String> {
		private Exception e;

		@Override
		protected void onPreExecute() {
			setProgressBarIndeterminateVisibility(true);
			((InputMethodManager) TdApp.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}

		@Override
		protected String doInBackground(String... args) {
			try {
				return BolloDAO.getData(args[0]);
			} catch (Exception e) {
				e.printStackTrace();
				this.e = e;
				return null;
			}
		}

		@Override
		protected void onPostExecute(String bollo) {
			setProgressBarIndeterminateVisibility(false);
			if (this.e == null) {
				Intent intent = new Intent(TdApp.getContext(), WebViewActivity.class);
				intent.putExtra(WebViewActivity.dataTag, bollo);
				startActivity(intent);
			} else
				new AlertDialog.Builder(BolloActivity.this).setTitle(R.string.error).setPositiveButton(R.string.ok, null).setMessage(e.getMessage()).show();
		}
	}
}
