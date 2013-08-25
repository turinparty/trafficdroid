package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.adapter.MainAdapter;
import it.localhost.trafficdroid.adapter.item.AbstractItem;
import it.localhost.trafficdroid.common.Utility;
import it.localhost.trafficdroid.common.billing.IabResult;
import it.localhost.trafficdroid.common.billing.Inventory;
import it.localhost.trafficdroid.dao.PersistanceService;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.exception.GenericException;
import it.localhost.trafficdroid.service.TdListener;
import it.localhost.trafficdroid.service.TdService;

import java.util.Random;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.commonsware.cwac.wakeful.WakefulIntentService;

public class MainActivity extends AbstractActivity {
	private static final String ALCOL_URL = "http://voti.kataweb.it/etilometro/index.php";
	private static final String APP_URL = "https://code.google.com/p/trafficdroid";
	private static final String donate = "market://details?id=it.localhost.donate";
	private static final String removePrefToastUndo = " è stato aggiunto ai preferiti.";
	private static final String removePrefToast = " è stato rimosso dai preferiti.";
	private static final String unknownError = "Unknown Error";
	public static final String blank = " ";
	private ExpandableListView listView;
	private IntentFilter intentFilter;
	private TdListener tdListener;
	private BroadcastReceiver receiver;
	private String[] questionsTrue, questionFalse;
	private Random rnd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
		//StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.main);
		setProgressBarIndeterminateVisibility(false);
		intentFilter = new IntentFilter();
		intentFilter.addAction(TdService.beginUpdate);
		intentFilter.addAction(TdService.endUpdate);
		listView = (ExpandableListView) findViewById(R.id.mainTable);
		tdListener = new TdListener();
		receiver = new UpdateReceiver();
		questionsTrue = getResources().getStringArray(R.array.patenteTrue);
		questionFalse = getResources().getStringArray(R.array.patenteFalse);
		rnd = new Random();
		listView.setOnChildClickListener(new OnChildClickListener() {
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				((AbstractItem) parent.getExpandableListAdapter().getChild(groupPosition, childPosition)).onClick();
				return true;
			}
		});
		if (Utility.getPrefString(this, R.string.providerTrafficKey, R.string.providerTrafficDefault).equals(getString(R.string.providerTrafficDefault))) {
			new AlertDialog.Builder(this).setTitle(R.string.warning).setMessage(R.string.badConf).setPositiveButton(R.string.setProvider, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					startActivity(new Intent(MainActivity.this, PreferencesActivity.class));
				}
			}).setNegativeButton(R.string.betterInfo, new OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
					intent.putExtra(WebViewActivity.urlTag, APP_URL);
					startActivity(intent);
				}
			}).show();
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
			menu.removeItem(R.id.menuPremium);
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
		case R.id.menuDonate:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(donate)));
			return true;
		case R.id.menuPremium:
			launchPurchaseFlow(SKU_AD_FREE);
			return true;
		case R.id.menuQuiz:
			showQuiz(R.string.patenteQuiz);
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
		if (packedPositionType == ExpandableListView.PACKED_POSITION_TYPE_GROUP || (packedPositionType == ExpandableListView.PACKED_POSITION_TYPE_CHILD && ((Integer) item.getTag(R.id.zoneType)) == AbstractItem.itemTypes[4])) {
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
	public void onQueryInventoryFinished(IabResult result, Inventory inv) {
		super.onQueryInventoryFinished(result, inv);
		if (result.isSuccess() && !inv.hasPurchase(SKU_QUIZ_FREE) && !Utility.getPrefString(this, R.string.providerTrafficKey, R.string.providerTrafficDefault).equals(getString(R.string.providerTrafficDefault)))
			showQuiz(R.string.patenteQuiz);
	}

	public void showQuiz(int title) {
		final boolean typeQuestion = rnd.nextBoolean();
		new AlertDialog.Builder(this).setPositiveButton(R.string.trueAns, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (!typeQuestion)
					showQuiz(R.string.retry);
			}
		}).setNegativeButton(R.string.falseAns, new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if (typeQuestion)
					showQuiz(R.string.retry);
			}
		}).setNeutralButton("Skip", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				launchPurchaseFlow(SKU_QUIZ_FREE);
			}
		}).setTitle(title).setCancelable(false).setMessage(typeQuestion ? questionsTrue[rnd.nextInt(questionsTrue.length)] : questionFalse[rnd.nextInt(questionFalse.length)]).show();
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
				listView.setAdapter(new MainAdapter(MainActivity.this, mainDTO));
				registerForContextMenu(listView);
				for (int i = 0; i < listView.getExpandableListAdapter().getGroupCount(); i++)
					if (Utility.getPrefBoolean(MainActivity.this, MainAdapter.expanded + i, false))
						listView.expandGroup(i);
					else
						listView.collapseGroup(i);
			}
			if (Utility.getPrefBoolean(MainActivity.this, GenericException.exceptionCheck, false)) {
				String msg = Utility.getPrefString(MainActivity.this, GenericException.exceptionMsg, unknownError);
				new AlertDialog.Builder(MainActivity.this).setTitle(R.string.error).setPositiveButton(R.string.ok, null).setMessage(msg).show();
				setTitle(msg);
				Utility.getEditor(MainActivity.this).putBoolean(GenericException.exceptionCheck, false).commit();
			}
		}
	}
}