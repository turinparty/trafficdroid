package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.TdApp;
import it.localhost.trafficdroid.dao.MoneyDAO;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class MoneyActivity extends AbstractActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.money);
		setProgressBarIndeterminateVisibility(false);
		findViewById(R.id.moneyOk).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String from = ((EditText) findViewById(R.id.moneyFrom)).getText().toString();
				String to = ((EditText) findViewById(R.id.moneyTo)).getText().toString();
				new RefreshTask().execute(from, to);
			}
		});
	}

	private class RefreshTask extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			setProgressBarIndeterminateVisibility(true);
		}

		@Override
		protected String doInBackground(String... args) {
			String money;
			try {
				money = "#" + MoneyDAO.getData(args[0], args[1], TdApp.getPrefString(R.string.providerCamKey, R.string.providerCamDefault));
			} catch (Exception e) {
				money = e.getMessage();
			}
			return money;
		}

		@Override
		protected void onPostExecute(String money) {
			if (money.charAt(0) == '#')
				((TextView) findViewById(R.id.money)).setText(money.substring(1));
			else
				new AlertDialog.Builder(MoneyActivity.this).setTitle(R.string.error).setPositiveButton(R.string.ok, null).setMessage(money).show();
			setProgressBarIndeterminateVisibility(false);
		}
	}
}
