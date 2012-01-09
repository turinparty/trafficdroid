package it.localhost.trafficdroid.activity;

import com.commonsware.cwac.wakeful.WakefulIntentService;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.adapter.MainAdapter;
import it.localhost.trafficdroid.adapter.item.AbstractChildItem;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdAnalytics;
import it.localhost.trafficdroid.common.TdApp;
import it.localhost.trafficdroid.dao.MainDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.service.TdListener;
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

public class MainActivity extends AbstractActivity {
	private ExpandableListView listView;
	private IntentFilter intentFilter;
	private BroadcastReceiver receiver;
	private TdListener tdListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.main);
		TdAnalytics.trackEvent(Const.eventCatApp, Const.eventActionVersion, TdApp.getVersionName(), TdApp.getVersionCode());
		intentFilter = new IntentFilter();
		intentFilter.addAction(Const.beginUpdate);
		intentFilter.addAction(Const.endUpdate);
		listView = (ExpandableListView) findViewById(R.id.mainTable);
		tdListener = new TdListener();
		listView.setOnChildClickListener(new OnChildClickListener() {
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				((AbstractChildItem) parent.getExpandableListAdapter().getChild(groupPosition, childPosition)).onClick();
				return true;
			}
		});
		receiver = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (intent.getAction().equals(Const.beginUpdate)) {
					setProgressBarIndeterminateVisibility(true);
				} else if (intent.getAction().equals(Const.endUpdate)) {
					setProgressBarIndeterminateVisibility(false);
					refresh();
				}
			}
		};
	}

	@Override
	public void onResume() {
		super.onResume();
		registerReceiver(receiver, intentFilter);
		WakefulIntentService.scheduleAlarms(new TdListener(), this, false);
		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(Const.notificationId);
		if (TdApp.getPrefString(R.string.providerTrafficKey, R.string.providerTrafficDefault).equals(getString(R.string.providerTrafficDefault)))
			new AlertDialog.Builder(this).setTitle(R.string.warning).setPositiveButton(R.string.ok, null).setMessage(R.string.badConf).show();
		else if (TdApp.getPrefBoolean(R.string.berserkKey, R.string.berserkDefault))
			tdListener.sendWakefulWork(TdApp.getContext());
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
			intent.putExtra(Const.url, Const.http + TdApp.getPrefString(R.string.providerFuelKey, R.string.providerFuelDefault) + Const.fuel);
			startActivity(intent);
			return true;
		case R.id.menuDonate:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Const.paypal)));
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
		if (packedPositionType == ExpandableListView.PACKED_POSITION_TYPE_GROUP || (packedPositionType == ExpandableListView.PACKED_POSITION_TYPE_CHILD && ((Integer) item.getTag(R.id.zoneType)) == Const.itemTypes[1])) {
			getMenuInflater().inflate(R.menu.main_context, menu);
			menu.getItem(0).setChecked(TdApp.getPrefBoolean((String) item.getTag(R.id.itemKey), false));
			menu.setHeaderTitle((String) item.getTag(R.id.itemName));
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		View v = ((ExpandableListContextMenuInfo) item.getMenuInfo()).targetView;
		String itemKey = (String) v.getTag(R.id.itemKey);
		String itemName = (String) v.getTag(R.id.itemName);
		switch (item.getItemId()) {
		case R.id.removePref:
			String msg;
			if (TdApp.getPrefBoolean(itemKey, false)) {
				item.setChecked(false);
				TdApp.getEditor().putBoolean(itemKey, false).commit();
				msg = Const.removePrefToast;
			} else {
				item.setChecked(true);
				TdApp.getEditor().putBoolean(itemKey, true).commit();
				msg = Const.removePrefToastUndo;
			}
			Toast.makeText(TdApp.getContext(), itemName + msg, Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	private void refresh() {
		if (!TdApp.getPrefBoolean(Const.exceptionCheck, false)) {
			new RefreshTask().execute((Void[]) null);
		} else {
			String msg = TdApp.getPrefString(Const.exceptionMsg, Const.unknowError);
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
				setTitle(getString(R.string.app_name) + Const.blank + DateFormat.getTimeFormat(MainActivity.this).format(mainDTO.getTrafficTime()));
				listView.setAdapter(new MainAdapter(MainActivity.this, mainDTO));
				registerForContextMenu(listView);
				for (int i = 0; i < listView.getExpandableListAdapter().getGroupCount(); i++)
					if (TdApp.getPrefBoolean(Const.expanded + i, false))
						listView.expandGroup(i);
					else
						listView.collapseGroup(i);
			} else
				tdListener.sendWakefulWork(TdApp.getContext());
		}
	}
}