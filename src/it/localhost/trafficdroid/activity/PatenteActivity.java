package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.TdApp;
import it.localhost.trafficdroid.dto.PatenteDTO;
import it.localhost.trafficdroid.parser.PatenteParser;
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

public class PatenteActivity extends AbstractActivity {
	private static final String BLANK = "";
	private static final String PATENTE_PWD = "PatentePwd";
	private static final String PATENTE_USR = "PatenteUsr";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.patente);
		setProgressBarIndeterminateVisibility(false);
		final EditText usrEdit = (EditText) findViewById(R.id.patenteUsr);
		final EditText pwdEdit = (EditText) findViewById(R.id.patentePwd);
		usrEdit.setText(TdApp.getPrefString(PATENTE_USR, BLANK));
		pwdEdit.setText(TdApp.getPrefString(PATENTE_PWD, BLANK));
		findViewById(R.id.ok).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String usr = usrEdit.getText().toString();
				String pwd = pwdEdit.getText().toString();
				TdApp.getEditor().putString(PATENTE_USR, usr);
				TdApp.getEditor().putString(PATENTE_PWD, pwd);
				TdApp.getEditor().commit();
				if (!usr.equals(BLANK) && !pwd.equals(BLANK))
					new RefreshTask().execute(usr, pwd);
				else
					new AlertDialog.Builder(PatenteActivity.this).setTitle(R.string.error).setPositiveButton(R.string.ok, null).setMessage(R.string.wrongData).show();
			}
		});
	}

	private class RefreshTask extends AsyncTask<String, Void, PatenteDTO> {
		private Exception e;

		@Override
		protected void onPreExecute() {
			setProgressBarIndeterminateVisibility(true);
			InputMethodManager im = (InputMethodManager) TdApp.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}

		@Override
		protected PatenteDTO doInBackground(String... args) {
			try {
				return PatenteParser.parse(args[0], args[1]);
			} catch (Exception e) {
				this.e = e;
				return null;
			}
		}

		@Override
		protected void onPostExecute(PatenteDTO patente) {
			setProgressBarIndeterminateVisibility(false);
			if (this.e == null) {
				((TextView) findViewById(R.id.patenteSaldo)).setText("Punti: " + patente.getSaldo());
				((TextView) findViewById(R.id.patenteNumero)).setText("Numero: " + patente.getNumeoPatente());
				((TextView) findViewById(R.id.patenteScadenza)).setText("Scadenza: " + patente.getScadenzaPatente());
			} else
				new AlertDialog.Builder(PatenteActivity.this).setTitle(R.string.error).setPositiveButton(R.string.ok, null).setMessage(e.getMessage()).show();
		}
	}
}
