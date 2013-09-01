package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Utility;
import it.localhost.trafficdroid.dao.PatenteService;
import it.localhost.trafficdroid.dto.BaseDTO;
import it.localhost.trafficdroid.dto.PatenteDTO;
import it.localhost.trafficdroid.fragment.MessageDialogFragment;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class PatenteActivity extends AbstractActivity { // NO_UCD
	private static final String BLANK = "";
	private static final String PATENTE_PWD = "PatentePwd";
	private static final String PATENTE_USR = "PatenteUsr";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.patente);
		setTitle(R.string.patente);
		setProgressBarIndeterminateVisibility(false);
		final EditText usrEdit = (EditText) findViewById(R.id.patenteUsr);
		final EditText pwdEdit = (EditText) findViewById(R.id.patentePwd);
		usrEdit.setText(Utility.getPrefString(this, PATENTE_USR, BLANK));
		pwdEdit.setText(Utility.getPrefString(this, PATENTE_PWD, BLANK));
		findViewById(R.id.ok).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String usr = usrEdit.getText().toString();
				String pwd = pwdEdit.getText().toString();
				Editor edit = Utility.getEditor(PatenteActivity.this);
				edit.putString(PATENTE_USR, usr);
				edit.putString(PATENTE_PWD, pwd);
				edit.commit();
				if (!usr.equals(BLANK) && !pwd.equals(BLANK))
					new PatenteAsyncTask().execute(usr, pwd);
				else
					new MessageDialogFragment().show(getFragmentManager(), getString(R.string.error), getString(R.string.wrongData), false);
			}
		});
	}

	private class PatenteAsyncTask extends PatenteService {
		@Override
		protected void onPreExecute() {
			setProgressBarIndeterminateVisibility(true);
			InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}

		@Override
		protected void onPostExecute(BaseDTO abstractResult) {
			setProgressBarIndeterminateVisibility(false);
			if (abstractResult.isSuccess()) {
				PatenteDTO patente = (PatenteDTO) abstractResult;
				((TextView) findViewById(R.id.patenteSaldo)).setText(patente.getSaldo());
				((TextView) findViewById(R.id.patenteNumero)).setText(patente.getNumeoPatente());
				((TextView) findViewById(R.id.patenteScadenza)).setText(patente.getScadenzaPatente());
			} else
				new MessageDialogFragment().show(getFragmentManager(), getString(R.string.error), abstractResult.getMessage(), false);
		}
	}
}
