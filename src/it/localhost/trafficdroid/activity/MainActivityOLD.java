package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.fragment.WebviewDialogFragment;
import android.content.Intent;
import android.view.MenuItem;

public class MainActivityOLD extends AbstractActivity { // NO_UCD
	private static final String ALCOL_URL = "http://voti.kataweb.it/etilometro/index.php";
	public static final String blank = " ";

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menuNews:
				startActivity(new Intent(MainActivityOLD.this, VideoActivity.class));
				return true;
			case R.id.menuMoney:
				startActivity(new Intent(MainActivityOLD.this, PedaggioActivity.class));
				return true;
			case R.id.menuPatente:
				startActivity(new Intent(MainActivityOLD.this, PatenteActivity.class));
				return true;
			case R.id.menuBollo:
				startActivity(new Intent(MainActivityOLD.this, BolloActivity.class));
				return true;
			case R.id.menuAlcol:
				new WebviewDialogFragment().show(getFragmentManager(), ALCOL_URL, null);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}