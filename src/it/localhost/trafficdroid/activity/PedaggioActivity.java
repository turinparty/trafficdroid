package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.ListExit;
import it.localhost.trafficdroid.dao.PedaggioService;
import it.localhost.trafficdroid.dto.BaseDTO;
import it.localhost.trafficdroid.dto.PedaggioDTO;
import it.localhost.trafficdroid.fragment.MessageDialogFragment;
import android.content.Context;
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
				String from = ListExit.getInstance().get(moneyFrom.getText().toString()).toString();
				String to = ListExit.getInstance().get(moneyTo.getText().toString()).toString();
				if (from != null && to != null)
					new PedaggioAsyncTask().execute(from, to);
				else
					new MessageDialogFragment().show(getFragmentManager(), getString(R.string.error), getString(R.string.wrongData), false);
			}
		});
	}

	private class PedaggioAsyncTask extends PedaggioService {
		@Override
		protected void onPreExecute() {
			setProgressBarIndeterminateVisibility(true);
			((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}

		@Override
		protected void onPostExecute(BaseDTO dto) {
			setProgressBarIndeterminateVisibility(false);
			if (dto.isSuccess())
				((TextView) findViewById(R.id.result)).setText("€ " + ((PedaggioDTO) dto).getPedaggio());
			else
				new MessageDialogFragment().show(getFragmentManager(), getString(R.string.error), dto.getMessage(), false);
		}
	}
}
