package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.adapter.MainAdapter;
import it.localhost.trafficdroid.adapter.item.AbstractChildItem;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdAnalytics;
import it.localhost.trafficdroid.common.TdApp;
import it.localhost.trafficdroid.common.ViewTagger;
import it.localhost.trafficdroid.dao.MainDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.service.TdListener;
import it.localhost.trafficdroid.service.TdService;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
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
	private static final String fuel = "/public/tabellanazionale/tabellaNazionale.php";
	private static final String donate = "market://details?id=it.localhost.donate";
	private static final String removePrefToastUndo = " è stato aggiunto ai preferiti.";
	private static final String removePrefToast = " è stato rimosso dai preferiti.";
	private static final String unknowError = "Unknow Error";
	private static final String blank = " ";
	private ExpandableListView listView;
	private IntentFilter intentFilter;
	private BroadcastReceiver receiver;
	private TdListener tdListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.main);
		TdAnalytics.trackEvent(TdAnalytics.eventCatApp, TdAnalytics.eventActionVersion, TdApp.getVersionName(), TdApp.getVersionCode());
		intentFilter = new IntentFilter();
		intentFilter.addAction(TdService.beginUpdate);
		intentFilter.addAction(TdService.endUpdate);
		listView = (ExpandableListView) findViewById(R.id.mainTable);
		tdListener = new TdListener();
		if (TdApp.getPrefString(R.string.providerTrafficKey, R.string.providerTrafficDefault).equals(getString(R.string.providerTrafficDefault)))
			new AlertDialog.Builder(this).setTitle(R.string.warning).setPositiveButton(R.string.ok, null).setMessage(R.string.badConf).show();
		else if (TdApp.getPrefBoolean(R.string.berserkKey, R.string.berserkDefault))
			tdListener.sendWakefulWork(TdApp.getContext());
		listView.setOnChildClickListener(new OnChildClickListener() {
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				((AbstractChildItem) parent.getExpandableListAdapter().getChild(groupPosition, childPosition)).onClick();
				return true;
			}
		});
		receiver = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (intent.getAction().equals(TdService.beginUpdate)) {
					setProgressBarIndeterminateVisibility(true);
				} else if (intent.getAction().equals(TdService.endUpdate)) {
					setProgressBarIndeterminateVisibility(false);
					refresh();
				}
			}
		};
		new AlertDialog.Builder(this).setTitle(R.string.info).setPositiveButton(R.string.ok, null).setMessage(R.string.help).show();
		
	}

	@Override
	public void onResume() {
		super.onResume();
		registerReceiver(receiver, intentFilter);
		WakefulIntentService.scheduleAlarms(new TdListener(), this, false);
		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(Const.notificationId);
		refresh();
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
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuSettings:
			startActivity(new Intent(MainActivity.this, PreferencesActivity.class));
			return true;
		case R.id.menuRefresh:
			tdListener.sendWakefulWork(TdApp.getContext());
			return true;
		case R.id.menuFuel:
			Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
			intent.putExtra(Const.url, Const.http + TdApp.getPrefString(R.string.providerFuelKey, R.string.providerFuelDefault) + fuel);
			startActivity(intent);
			return true;
		case R.id.menuDonate:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(donate)));
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
		if (packedPositionType == ExpandableListView.PACKED_POSITION_TYPE_GROUP || (packedPositionType == ExpandableListView.PACKED_POSITION_TYPE_CHILD && ((Integer) ViewTagger.getTag(item, R.id.zoneType)) == Const.itemTypes[1])) {
			getMenuInflater().inflate(R.menu.main_context, menu);
			menu.getItem(0).setChecked(TdApp.getPrefBoolean((String) ViewTagger.getTag(item, R.id.itemKey), false));
			menu.setHeaderTitle((String) ViewTagger.getTag(item, R.id.itemName));
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		View v = ((ExpandableListContextMenuInfo) item.getMenuInfo()).targetView;
		String itemKey = (String) ViewTagger.getTag(v, R.id.itemKey);
		String itemName = (String) ViewTagger.getTag(v, R.id.itemName);
		switch (item.getItemId()) {
		case R.id.removePref:
			String msg;
			if (TdApp.getPrefBoolean(itemKey, false)) {
				item.setChecked(false);
				TdApp.getEditor().putBoolean(itemKey, false).commit();
				msg = removePrefToast;
			} else {
				item.setChecked(true);
				TdApp.getEditor().putBoolean(itemKey, true).commit();
				msg = removePrefToastUndo;
			}
			Toast.makeText(TdApp.getContext(), itemName + msg, Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	private void refresh() {
		new RefreshTask().execute((Void[]) null);
		if (TdApp.getPrefBoolean(Const.exceptionCheck, false)) {
			String msg = TdApp.getPrefString(Const.exceptionMsg, unknowError);
			new AlertDialog.Builder(this).setTitle(R.string.error).setPositiveButton(R.string.ok, null).setMessage(msg).show();
			setTitle(msg);
			TdApp.getEditor().putBoolean(Const.exceptionCheck, false).commit();
		}
	}

	private class RefreshTask extends AsyncTask<Void, Void, MainDTO> {
		@Override
		protected MainDTO doInBackground(Void... params) {
			try {
				return MainDAO.retrieve();
			} catch (Exception e) {
				return null;
			}
		}

		@Override
		protected void onPostExecute(MainDTO mainDTO) {
			if (mainDTO != null && mainDTO.getTrafficTime() != null) {
				setTitle(getString(R.string.app_name) + blank + DateFormat.getTimeFormat(MainActivity.this).format(mainDTO.getTrafficTime()));
				listView.setAdapter(new MainAdapter(MainActivity.this, mainDTO));
				registerForContextMenu(listView);
				for (int i = 0; i < listView.getExpandableListAdapter().getGroupCount(); i++)
					if (TdApp.getPrefBoolean(MainAdapter.expanded + i, false))
						listView.expandGroup(i);
					else
						listView.collapseGroup(i);
			} else
				tdListener.sendWakefulWork(TdApp.getContext());
		}
	}
}