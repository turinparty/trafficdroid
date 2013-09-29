package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.adapter.BadNewsDialogAdapter;
import it.localhost.trafficdroid.adapter.item.BadNewsItem.OnBadNewsItemClickListener;
import it.localhost.trafficdroid.adapter.item.GraphItem.OnGraphItemClickListener;
import it.localhost.trafficdroid.adapter.item.ZoneItem.OnZoneItemChildClickListener;
import it.localhost.trafficdroid.common.Utility;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import it.localhost.trafficdroid.fragment.MessageDialogFragment;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.google.analytics.tracking.android.EasyTracker;

public class MainActivity extends AbstractActivity implements OnZoneItemChildClickListener, OnBadNewsItemClickListener, OnGraphItemClickListener { // NO_UCD
	private static final String ALCOL_URL = "http://voti.kataweb.it/etilometro/index.php";
	private static final String autostrade = "http://mobile.autostrade.it/autostrade-mobile/popupTelecamera.do?tlc=";
	private static final String cavspa = "http://www.cavspa.it/webcam/temp-imgs/camsbig/";
	private static final String edidomus = "http://telecamere.edidomus.it/vp2/vpimage.aspx?camid=";
	private static final String autofiori = "http://www.autofiori.it/cgi-bin/cgiwebcam.exe?site=";
	private static final String autobspd = "http://www.autobspd.it/images/telecamereAutobspd/";
	private static final String firstUrl = "http://vai-cdn.stradeanas.it/Appscripts/sinotraffic.php?city=";
	private static final String secondUrl = "&ts=";
	private static final String jpg = ".jpg";
	private static final int date = new GregorianCalendar().get(GregorianCalendar.DATE);
	private static final char camAutostrade = 'A';
	private static final char camCavspa = 'C';
	private static final char camEdidomus = 'E';
	private static final char camAutofiori = 'F';
	private static final char camAutobspd = 'B';
	private static final char camNone = 'H';

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// android.os.StrictMode.setThreadPolicy(new
		// android.os.StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
		// android.os.StrictMode.setVmPolicy(new
		// android.os.StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setProgressBarIndeterminateVisibility(false);
		ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		Tab tab = bar.newTab();
		tab.setText("Main");
		tab.setTabListener(new MainFragment());
		bar.addTab(tab);
		Tab tab2 = bar.newTab();
		tab2.setText("Video");
		tab2.setTabListener(new VideoFragment());
		bar.addTab(tab2);
		Tab tab3 = bar.newTab();
		tab3.setText("Pedaggio");
		tab3.setTabListener(new PedaggioFragment());
		bar.addTab(tab3);
		Tab tab4 = bar.newTab();
		tab4.setText("Patente");
		tab4.setTabListener(new PatenteFragment());
		bar.addTab(tab4);
		Tab tab5 = bar.newTab();
		tab5.setText("Bollo");
		tab5.setTabListener(new BolloFragment());
		bar.addTab(tab5);
		Tab tab6 = bar.newTab();
		tab6.setText("settings");
		tab6.setTabListener(new PreferencesFragment());
		bar.addTab(tab6);
	}

	@Override
	public void onResume() {
		super.onResume();
		WakefulIntentService.scheduleAlarms(new TdListener(), this, false);
		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(TdService.notificationId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_option, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (isAdFree())
			menu.removeItem(R.id.menuAdFree);
		if (isInterstitialFree())
			menu.removeItem(R.id.menuInterstitialFree);
		return true;
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
			if (!Utility.getPrefString(this, R.string.providerTrafficKey, R.string.providerTrafficDefault).equals(getString(R.string.providerTrafficDefault)))
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

	@Override
	public void onBadNewsItemClick(StreetDTO streetDTO) {
		if (streetDTO.getBadNews().size() != 0) {
			EasyTracker.getTracker().sendEvent(AbstractActivity.EVENT_CAT_BADNEWS, AbstractActivity.EVENT_ACTION_OPEN, streetDTO.getName(), (long) 0);
			Dialog dialog = new Dialog(this);
			dialog.setTitle(streetDTO.getName());
			ListView listview = (ListView) LayoutInflater.from(this).inflate(R.layout.dialog_badnews, null);
			listview.setAdapter(new BadNewsDialogAdapter(this, streetDTO, isAdFree()));
			dialog.setContentView(listview);
			dialog.show();
		}
	}

	@Override
	public void onZoneItemChildClick(ZoneDTO zone) {
		String webcam = zone.getWebcam();
		if (webcam.charAt(0) == camNone) {
			EasyTracker.getTracker().sendEvent(AbstractActivity.EVENT_CAT_WEBCAM, AbstractActivity.EVENT_ACTION_NONE, webcam, (long) 0);
			new MessageDialogFragment().show(getFragmentManager(), getString(R.string.info), getString(R.string.webcamNone), false);
		} else if (webcam.charAt(0) == camAutostrade) {
			EasyTracker.getTracker().sendEvent(AbstractActivity.EVENT_CAT_WEBCAM, AbstractActivity.EVENT_ACTION_OPEN, webcam, (long) 0);
			int id = Integer.parseInt(webcam.substring(1)) + 6280 * (date);
			new WebviewDialogFragment().show(getFragmentManager(), autostrade + id, null);
		} else if (webcam.charAt(0) == camCavspa) {
			EasyTracker.getTracker().sendEvent(AbstractActivity.EVENT_CAT_WEBCAM, AbstractActivity.EVENT_ACTION_OPEN, webcam, (long) 0);
			new WebviewDialogFragment().show(getFragmentManager(), cavspa + webcam.substring(1) + jpg, null);
		} else if (webcam.charAt(0) == camEdidomus) {
			EasyTracker.getTracker().sendEvent(AbstractActivity.EVENT_CAT_WEBCAM, AbstractActivity.EVENT_ACTION_OPEN, webcam, (long) 0);
			new WebviewDialogFragment().show(getFragmentManager(), edidomus + webcam.substring(1), null);
		} else if (webcam.charAt(0) == camAutofiori) {
			EasyTracker.getTracker().sendEvent(AbstractActivity.EVENT_CAT_WEBCAM, AbstractActivity.EVENT_ACTION_OPEN, webcam, (long) 0);
			new WebviewDialogFragment().show(getFragmentManager(), autofiori + webcam.substring(1), null);
		} else if (webcam.charAt(0) == camAutobspd) {
			EasyTracker.getTracker().sendEvent(AbstractActivity.EVENT_CAT_WEBCAM, AbstractActivity.EVENT_ACTION_OPEN, webcam, (long) 0);
			new WebviewDialogFragment().show(getFragmentManager(), autobspd + webcam.substring(1) + jpg, null);
		} else {
			EasyTracker.getTracker().sendEvent(AbstractActivity.EVENT_CAT_WEBCAM, AbstractActivity.EVENT_ACTION_REQUEST, webcam, (long) 0);
			new MessageDialogFragment().show(getFragmentManager(), getString(R.string.info), getString(R.string.webcamAdd), false);
		}
	}

	@Override
	public void onGraphItemClick(String graph) {
		EasyTracker.getTracker().sendEvent(AbstractActivity.EVENT_CAT_GRAPH, AbstractActivity.EVENT_ACTION_OPEN, graph, (long) 0);
		new WebviewDialogFragment().show(getFragmentManager(), firstUrl + graph + secondUrl + new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault()).format(new Date()), null);
	}
}