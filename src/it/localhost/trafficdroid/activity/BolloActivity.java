package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.dao.BolloService;
import it.localhost.trafficdroid.dto.BaseDTO;
import it.localhost.trafficdroid.dto.BolloDTO;
import it.localhost.trafficdroid.fragment.MessageDialogFragment;
import it.localhost.trafficdroid.fragment.WebviewDialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;

public class BolloActivity extends AbstractActivity { // NO_UCD
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.bollo);
		setTitle(R.string.bollo);
		setProgressBarIndeterminateVisibility(false);
		findViewById(R.id.ok).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String targa = ((EditText) findViewById(R.id.targaA)).getText().toString() + ((EditText) findViewById(R.id.targaB)).getText().toString() + ((EditText) findViewById(R.id.targaC)).getText().toString();
				String tipoVeicolo = getResources().getStringArray(R.array.tipoVeicoloKey)[((Spinner) findViewById(R.id.tipoVeicolo)).getSelectedItemPosition()];
				String regioneResidenza = getResources().getStringArray(R.array.regioneResidenzaKey)[((Spinner) findViewById(R.id.regioneResidenza)).getSelectedItemPosition()];
				new BolloAsyncTask().execute(tipoVeicolo, regioneResidenza, targa);
			}
		});
	}

	private class BolloAsyncTask extends BolloService {
		@Override
		protected void onPreExecute() {
			setProgressBarIndeterminateVisibility(true);
			((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}

		@Override
		protected void onPostExecute(BaseDTO abstractResult) {
			setProgressBarIndeterminateVisibility(false);
			if (abstractResult.isSuccess())
				new WebviewDialogFragment().show(getFragmentManager(), null, ((BolloDTO) abstractResult).getBollo());
			else
				new MessageDialogFragment().show(getFragmentManager(), getString(R.string.error), abstractResult.getMessage(), false);
		}
	}
}
