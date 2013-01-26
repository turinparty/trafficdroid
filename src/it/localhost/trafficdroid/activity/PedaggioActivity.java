package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.ListExit;
import it.localhost.trafficdroid.common.TdApp;
import it.localhost.trafficdroid.dao.PedaggioDAO;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class PedaggioActivity extends AbstractActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.pedaggio);
		setTitle(R.string.pedaggio);
		setProgressBarIndeterminateVisibility(false);
		final AutoCompleteTextView moneyFrom = (AutoCompleteTextView) findViewById(R.id.moneyFrom);
		final AutoCompleteTextView moneyTo = (AutoCompleteTextView) findViewById(R.id.moneyTo);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ListExit.getInstance().getKeys());
		moneyFrom.setAdapter(adapter);
		moneyTo.setAdapter(adapter);
		findViewById(R.id.ok).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Integer from = ListExit.getInstance().get(moneyFrom.getText().toString());
				Integer to = ListExit.getInstance().get(moneyTo.getText().toString());
				if (from != null && to != null)
					new RefreshTask().execute(from, to);
				else
					new AlertDialog.Builder(PedaggioActivity.this).setTitle(R.string.error).setPositiveButton(R.string.ok, null).setMessage(R.string.wrongData).show();
			}
		});
	}

	private class RefreshTask extends AsyncTask<Integer, Void, String> {
		private Exception e;

		@Override
		protected void onPreExecute() {
			setProgressBarIndeterminateVisibility(true);
			InputMethodManager im = (InputMethodManager) TdApp.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}

		@Override
		protected String doInBackground(Integer... args) {
			try {
				String money = "€ " + PedaggioDAO.getData(args[0], args[1]);
				return money;
			} catch (Exception e) {
				this.e = e;
				return null;
			}
		}

		@Override
		protected void onPostExecute(String money) {
			setProgressBarIndeterminateVisibility(false);
			if (this.e == null)
				((TextView) findViewById(R.id.result)).setText(money);
			else
				new AlertDialog.Builder(PedaggioActivity.this).setTitle(R.string.error).setPositiveButton(R.string.ok, null).setMessage(e.getMessage()).show();
		}
	}
}
