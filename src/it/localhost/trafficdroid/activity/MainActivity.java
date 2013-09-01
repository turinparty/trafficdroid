package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.adapter.BadNewsDialogAdapter;
import it.localhost.trafficdroid.adapter.MainAdapter;
import it.localhost.trafficdroid.adapter.item.AbstractItem;
import it.localhost.trafficdroid.adapter.item.BadNewsItem.OnBadNewsItemClickListener;
import it.localhost.trafficdroid.adapter.item.ZoneItem.OnZoneItemChildClickListener;
import it.localhost.trafficdroid.common.Utility;
import it.localhost.trafficdroid.dao.PersistanceService;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import it.localhost.trafficdroid.exception.GenericException;
import it.localhost.trafficdroid.fragment.MessageDialogFragment;
import it.localhost.trafficdroid.fragment.QuizDialogFragment;
import it.localhost.trafficdroid.fragment.SetupDialogFragment;
import it.localhost.trafficdroid.service.TdListener;
import it.localhost.trafficdroid.service.TdService;

import java.util.GregorianCalendar;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.google.analytics.tracking.android.EasyTracker;

public class MainActivity extends AbstractActivity implements OnZoneItemChildClickListener, OnBadNewsItemClickListener { // NO_UCD
	private static final String autostrade = "http://mobile.autostrade.it/autostrade-mobile/popupTelecamera.do?tlc=";
	private static final String cavspa = "http://www.cavspa.it/webcam/temp-imgs/camsbig/";
	private static final String edidomus = "http://telecamere.edidomus.it/vp2/vpimage.aspx?camid=";
	private static final String autofiori = "http://www.autofiori.it/cgi-bin/cgiwebcam.exe?site=";
	private static final String autobspd = "http://www.autobspd.it/images/telecamereAutobspd/";
	private static final String jpg = ".jpg";
	private static final int date = new GregorianCalendar().get(GregorianCalendar.DATE);
	private static final String ALCOL_URL = "http://voti.kataweb.it/etilometro/index.php";
	private static final String removePrefToastUndo = " è stato aggiunto ai preferiti.";
	private static final String removePrefToast = " è stato rimosso dai preferiti.";
	private static final String unknownError = "Unknown Error";
	private static final char camAutostrade = 'A';
	private static final char camCavspa = 'C';
	private static final char camEdidomus = 'E';
	private static final char camAutofiori = 'F';
	private static final char camAutobspd = 'B';
	private static final char camNone = 'H';
	public static final String blank = " ";
	private ExpandableListView listView;
	private IntentFilter intentFilter;
	private TdListener tdListener;
	private BroadcastReceiver receiver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android.os.StrictMode.setThreadPolicy(new android.os.StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
		android.os.StrictMode.setVmPolicy(new android.os.StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.main);
		setProgressBarIndeterminateVisibility(false);
		intentFilter = new IntentFilter();
		intentFilter.addAction(TdService.beginUpdate);
		intentFilter.addAction(TdService.endUpdate);
		listView = (ExpandableListView) findViewById(R.id.mainTable);
		tdListener = new TdListener();
		receiver = new UpdateReceiver();
		listView.setOnChildClickListener(new OnChildClickListener() {
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				((AbstractItem) parent.getExpandableListAdapter().getChild(groupPosition, childPosition)).onClick();
				return true;
			}
		});
		if (Utility.getPrefString(this, R.string.providerTrafficKey, R.string.providerTrafficDefault).equals(getString(R.string.providerTrafficDefault))) {
			new SetupDialogFragment().show(getFragmentManager(), SetupDialogFragment.class.getSimpleName());
		} else if (Utility.getPrefBoolean(this, R.string.berserkKey, R.string.berserkDefault))
			tdListener.sendWakefulWork(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		registerReceiver(receiver, intentFilter);
		WakefulIntentService.scheduleAlarms(tdListener, this, false);
		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(TdService.notificationId);
		new RefreshTask().execute();
	}

	@Override
	public void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_option, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuSettings:
			startActivity(new Intent(MainActivity.this, PreferencesActivity.class));
			return true;
		case R.id.menuNews:
			startActivity(new Intent(MainActivity.this, VideoActivity.class));
			return true;
		case R.id.menuRefresh:
			if (!Utility.getPrefString(this, R.string.providerTrafficKey, R.string.providerTrafficDefault).equals(getString(R.string.providerTrafficDefault)))
				tdListener.sendWakefulWork(this);
			return true;
		case R.id.menuMoney:
			startActivity(new Intent(MainActivity.this, PedaggioActivity.class));
			return true;
		case R.id.menuPatente:
			startActivity(new Intent(MainActivity.this, PatenteActivity.class));
			return true;
		case R.id.menuBollo:
			startActivity(new Intent(MainActivity.this, BolloActivity.class));
			return true;
		case R.id.menuAlcol:
			Intent intent = new Intent(this, WebViewActivity.class);
			intent.putExtra(WebViewActivity.urlTag, ALCOL_URL);
			startActivity(intent);
			return true;
		case R.id.menuAdFree:
			launchPurchaseFlow(SKU_AD_FREE);
			return true;
		case R.id.menuInterstitialFree:
			launchPurchaseFlow(SKU_INTERSTITIAL_FREE);
			return true;
		case R.id.menuQuiz:
			new QuizDialogFragment().show(getFragmentManager(), getString(R.string.patenteQuiz));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		ExpandableListContextMenuInfo info = (ExpandableListContextMenuInfo) menuInfo;
		int packedPositionType = ExpandableListView.getPackedPositionType(info.packedPosition);
		View item = info.targetView;
		if (packedPositionType == ExpandableListView.PACKED_POSITION_TYPE_GROUP || (packedPositionType == ExpandableListView.PACKED_POSITION_TYPE_CHILD && ((Integer) item.getTag(R.id.zoneType)) == AbstractItem.itemTypes[4]))
		{
			getMenuInflater().inflate(R.menu.main_context, menu);
			menu.getItem(0).setChecked(Utility.getPrefBoolean(this, Integer.toString((Integer) item.getTag(R.id.itemKey)), false));
			menu.setHeaderTitle((String) item.getTag(R.id.itemName));
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		View v = ((ExpandableListContextMenuInfo) item.getMenuInfo()).targetView;
		String itemKey = Integer.toString((Integer) v.getTag(R.id.itemKey));
		String itemName = (String) v.getTag(R.id.itemName);
		switch (item.getItemId()) {
		case R.id.removePref:
			String msg;
			if (Utility.getPrefBoolean(this, itemKey, false)) {
				item.setChecked(false);
				Utility.getEditor(this).putBoolean(itemKey, false).commit();
				msg = removePrefToast;
			} else {
				item.setChecked(true);
				Utility.getEditor(this).putBoolean(itemKey, true).commit();
				msg = removePrefToastUndo;
			}
			Toast.makeText(this, itemName + msg, Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onContextItemSelected(item);
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
			Intent intent = new Intent(this, WebViewActivity.class);
			int id = Integer.parseInt(webcam.substring(1)) + 6280 * (date);
			intent.putExtra(WebViewActivity.urlTag, autostrade + id);
			startActivity(intent);
		} else if (webcam.charAt(0) == camCavspa) {
			EasyTracker.getTracker().sendEvent(AbstractActivity.EVENT_CAT_WEBCAM, AbstractActivity.EVENT_ACTION_OPEN, webcam, (long) 0);
			Intent intent = new Intent(this, WebViewActivity.class);
			intent.putExtra(WebViewActivity.urlTag, cavspa + webcam.substring(1) + jpg);
			startActivity(intent);
		} else if (webcam.charAt(0) == camEdidomus) {
			EasyTracker.getTracker().sendEvent(AbstractActivity.EVENT_CAT_WEBCAM, AbstractActivity.EVENT_ACTION_OPEN, webcam, (long) 0);
			Intent intent = new Intent(this, WebViewActivity.class);
			intent.putExtra(WebViewActivity.urlTag, edidomus + webcam.substring(1));
			startActivity(intent);
		} else if (webcam.charAt(0) == camAutofiori) {
			EasyTracker.getTracker().sendEvent(AbstractActivity.EVENT_CAT_WEBCAM, AbstractActivity.EVENT_ACTION_OPEN, webcam, (long) 0);
			Intent intent = new Intent(this, WebViewActivity.class);
			intent.putExtra(WebViewActivity.urlTag, autofiori + webcam.substring(1));
			startActivity(intent);
		} else if (webcam.charAt(0) == camAutobspd) {
			EasyTracker.getTracker().sendEvent(AbstractActivity.EVENT_CAT_WEBCAM, AbstractActivity.EVENT_ACTION_OPEN, webcam, (long) 0);
			Intent intent = new Intent(this, WebViewActivity.class);
			intent.putExtra(WebViewActivity.urlTag, autobspd + webcam.substring(1) + jpg);
			startActivity(intent);
		} else {
			EasyTracker.getTracker().sendEvent(AbstractActivity.EVENT_CAT_WEBCAM, AbstractActivity.EVENT_ACTION_REQUEST, webcam, (long) 0);
			new MessageDialogFragment().show(getFragmentManager(), getString(R.string.info), getString(R.string.webcamAdd), false);
		}
	}

	private final class UpdateReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(TdService.beginUpdate)) {
				setProgressBarIndeterminateVisibility(true);
			} else if (intent.getAction().equals(TdService.endUpdate)) {
				setProgressBarIndeterminateVisibility(false);
				new RefreshTask().execute();
			}
		}
	}

	private class RefreshTask extends AsyncTask<Void, Void, MainDTO> {
		@Override
		protected MainDTO doInBackground(Void... params) {
			try {
				return PersistanceService.retrieve(MainActivity.this);
			} catch (Exception e) {
				return null;
			}
		}

		@Override
		protected void onPostExecute(MainDTO mainDTO) {
			if (mainDTO != null && mainDTO.getTrafficTime() != null) {
				setTitle(DateFormat.getTimeFormat(MainActivity.this).format(mainDTO.getTrafficTime()) + blank + getString(R.string.app_name));
				listView.setAdapter(new MainAdapter(MainActivity.this, mainDTO, isAdFree()));
				registerForContextMenu(listView);
				for (int i = 0; i < listView.getExpandableListAdapter().getGroupCount(); i++)
					if (Utility.getPrefBoolean(MainActivity.this, MainAdapter.expanded + i, false))
						listView.expandGroup(i);
					else
						listView.collapseGroup(i);
			}
			if (Utility.getPrefBoolean(MainActivity.this, GenericException.exceptionCheck, false)) {
				String msg = Utility.getPrefString(MainActivity.this, GenericException.exceptionMsg, unknownError);
				new MessageDialogFragment().show(getFragmentManager(), getString(R.string.error), msg, false);
				setTitle(msg);
				Utility.getEditor(MainActivity.this).putBoolean(GenericException.exceptionCheck, false).commit();
			}
		}
	}
}