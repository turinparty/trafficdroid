package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Utility;
import it.localhost.trafficdroid.fragment.QuizDialogFragment;
import it.localhost.trafficdroid.fragment.WebviewDialogFragment;
import it.localhost.trafficdroid.service.TdListener;
import it.localhost.trafficdroid.service.TdService;
import it.localhost.trafficdroid.tabFragment.BolloFragment;
import it.localhost.trafficdroid.tabFragment.MainFragment;
import it.localhost.trafficdroid.tabFragment.PatenteFragment;
import it.localhost.trafficdroid.tabFragment.PedaggioFragment;
import it.localhost.trafficdroid.tabFragment.PreferencesFragment;
import it.localhost.trafficdroid.tabFragment.VideoFragment;
import android.app.ActionBar;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.commonsware.cwac.wakeful.WakefulIntentService;

public class MainActivity extends AbstractActivity { // NO_UCD
	private static final String ALCOL_URL = "http://voti.kataweb.it/etilometro/index.php";
	private BroadcastReceiver receiver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android.os.StrictMode.setThreadPolicy(new android.os.StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
		android.os.StrictMode.setVmPolicy(new android.os.StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setProgressBarIndeterminateVisibility(false);
		ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.addTab(bar.newTab().setText(R.string.app_name).setTabListener(new MainFragment()));
		bar.addTab(bar.newTab().setText(R.string.anasTv).setTabListener(new VideoFragment()));
		bar.addTab(bar.newTab().setText(R.string.pedaggio).setTabListener(new PedaggioFragment()));
		bar.addTab(bar.newTab().setText(R.string.patente).setTabListener(new PatenteFragment()));
		bar.addTab(bar.newTab().setText(R.string.bollo).setTabListener(new BolloFragment()));
		bar.addTab(bar.newTab().setText(R.string.settings).setTabListener(new PreferencesFragment()));
	}

	@Override
	public void onResume() {
		super.onResume();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(TdService.beginUpdate);
		intentFilter.addAction(TdService.endUpdate);
		receiver = new UpdateReceiver();
		registerReceiver(receiver, intentFilter);
		WakefulIntentService.scheduleAlarms(new TdListener(), this, false);
		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(TdService.notificationId);
	}

	@Override
	public void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_option, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (Utility.isAdFree(this))
			menu.removeItem(R.id.menuAdFree);
		if (Utility.isInterstitialFree(this))
			menu.removeItem(R.id.menuInterstitialFree);
		return true;
	}

	@Override
	public void onBackPressed() {
		if (getActionBar().getSelectedNavigationIndex() == 0)
			super.onBackPressed();
		else
			getActionBar().setSelectedNavigationItem(0);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menuAlcol:
				new WebviewDialogFragment().show(getFragmentManager(), ALCOL_URL, null);
				return true;
			case R.id.menuQuiz:
				new QuizDialogFragment().show(getFragmentManager(), getString(R.string.patenteQuiz));
				return true;
			case R.id.menuRefresh:
				if (!Utility.getProviderTraffic(this).equals(getString(R.string.providerTrafficDefault)))
					new TdListener().sendWakefulWork(this);
				return true;
			case R.id.menuAdFree:
				launchPurchaseFlow(SKU_AD_FREE);
				return true;
			case R.id.menuInterstitialFree:
				launchPurchaseFlow(SKU_INTERSTITIAL_FREE);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private final class UpdateReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(TdService.beginUpdate))
				setProgressBarIndeterminateVisibility(true);
			else if (intent.getAction().equals(TdService.endUpdate))
				setProgressBarIndeterminateVisibility(false);
		}
	}
}