package it.localhost.trafficdroid.tabFragment;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.MainActivity;
import it.localhost.trafficdroid.adapter.HeterogeneousExpandableListAdapter;
import it.localhost.trafficdroid.adapter.item.AbstractItem;
import it.localhost.trafficdroid.adapter.item.AbstractItem.OnAbstractItemClickListener;
import it.localhost.trafficdroid.adapter.item.AdViewItem;
import it.localhost.trafficdroid.adapter.item.BadNewsItem;
import it.localhost.trafficdroid.adapter.item.GraphItem;
import it.localhost.trafficdroid.adapter.item.StreetItem;
import it.localhost.trafficdroid.adapter.item.ZoneItem;
import it.localhost.trafficdroid.common.AdManager;
import it.localhost.trafficdroid.common.Utility;
import it.localhost.trafficdroid.dao.PersistanceService;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import it.localhost.trafficdroid.fragment.BadnewsDialogFragment;
import it.localhost.trafficdroid.fragment.MessageDialogFragment;
import it.localhost.trafficdroid.fragment.SetupDialogFragment;
import it.localhost.trafficdroid.fragment.WebviewDialogFragment;
import it.localhost.trafficdroid.service.TdListener;
import it.localhost.trafficdroid.service.TdService;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.android.gms.ads.AdView;

public class MainFragment extends Fragment implements TabListener, OnAbstractItemClickListener {
	private static final String firstUrl = "http://vai-cdn.stradeanas.it/Appscripts/sinotraffic.php?city=";
	private static final String secondUrl = "&ts=";
	private static final String autostrade = "http://mobile.autostrade.it/autostrade-mobile/popupTelecamera.do?tlc=";
	private static final String cavspa = "http://www.cavspa.it/webcam/temp-imgs/camsbig/";
	private static final String edidomus = "http://telecamere.edidomus.it/vp2/vpimage.aspx?camid=";
	private static final String autofiori = "http://www.autofiori.it/cgi-bin/cgiwebcam.exe?site=";
	private static final String autobspd = "http://www.autobspd.it/images/telecamereAutobspd/";
	private static final String jpg = ".jpg";
	private static final int date = new GregorianCalendar().get(GregorianCalendar.DATE);
	private static final char camAutostrade = 'A';
	private static final char camCavspa = 'C';
	private static final char camEdidomus = 'E';
	private static final char camAutofiori = 'F';
	private static final char camAutobspd = 'B';
	private static final char camNone = 'H';
	private ExpandableListView listView;
	private TdListener tdListener;
	private BroadcastReceiver receiver;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.main, null);
		listView = (ExpandableListView) v.findViewById(R.id.mainTable);
		listView.setOnChildClickListener(new OnChildClickListener() {
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				((AbstractItem) parent.getExpandableListAdapter().getChild(groupPosition, childPosition)).onClick();
				return true;
			}
		});
		tdListener = new TdListener();
		if (Utility.getProviderTraffic(getActivity()).equals(getString(R.string.providerTrafficDefault))) {
			new SetupDialogFragment().show(getFragmentManager(), SetupDialogFragment.class.getSimpleName());
		} else if (Utility.isBerserkKey(getActivity()))
			tdListener.sendWakefulWork(getActivity());
		EasyTracker.getInstance(getActivity()).send(MapBuilder.createAppView().set(Fields.SCREEN_NAME, MainFragment.class.getSimpleName()).build());
		new AdManager().load(getActivity(), ((AdView) v.findViewById(R.id.adView)), false);
		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
		receiver = new UpdateReceiver();
		getActivity().registerReceiver(receiver, new IntentFilter(TdService.endUpdate));
		new RefreshTask().execute();
	}

	@Override
	public void onPause() {
		super.onPause();
		getActivity().unregisterReceiver(receiver);
	}

	@Override
	public boolean onAbstractItemClick(Serializable extra) {
		if (extra instanceof String) {
			String graph = (String) extra;
			EasyTracker.getInstance(getActivity()).send(MapBuilder.createEvent(MainActivity.EVENT_CAT_GRAPH, MainActivity.EVENT_ACTION_OPEN, graph, (long) 0).build());
			new WebviewDialogFragment().show(getFragmentManager(), firstUrl + graph + secondUrl + new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault()).format(new Date()), null);
			return true;
		} else if (extra instanceof ZoneDTO) {
			String webcam = ((ZoneDTO) extra).getWebcam();
			if (webcam.charAt(0) == camNone) {
				EasyTracker.getInstance(getActivity()).send(MapBuilder.createEvent(MainActivity.EVENT_CAT_WEBCAM, MainActivity.EVENT_ACTION_NONE, webcam, (long) 0).build());
				new MessageDialogFragment().show(getFragmentManager(), getString(R.string.info), getString(R.string.webcamNone), false);
			} else if (webcam.charAt(0) == camAutostrade) {
				EasyTracker.getInstance(getActivity()).send(MapBuilder.createEvent(MainActivity.EVENT_CAT_WEBCAM, MainActivity.EVENT_ACTION_OPEN, webcam, (long) 0).build());
				int id = Integer.parseInt(webcam.substring(1)) + 6280 * (date);
				new WebviewDialogFragment().show(getFragmentManager(), autostrade + id, null);
			} else if (webcam.charAt(0) == camCavspa) {
				EasyTracker.getInstance(getActivity()).send(MapBuilder.createEvent(MainActivity.EVENT_CAT_WEBCAM, MainActivity.EVENT_ACTION_OPEN, webcam, (long) 0).build());
				new WebviewDialogFragment().show(getFragmentManager(), cavspa + webcam.substring(1) + jpg, null);
			} else if (webcam.charAt(0) == camEdidomus) {
				EasyTracker.getInstance(getActivity()).send(MapBuilder.createEvent(MainActivity.EVENT_CAT_WEBCAM, MainActivity.EVENT_ACTION_OPEN, webcam, (long) 0).build());
				new WebviewDialogFragment().show(getFragmentManager(), edidomus + webcam.substring(1), null);
			} else if (webcam.charAt(0) == camAutofiori) {
				EasyTracker.getInstance(getActivity()).send(MapBuilder.createEvent(MainActivity.EVENT_CAT_WEBCAM, MainActivity.EVENT_ACTION_OPEN, webcam, (long) 0).build());
				new WebviewDialogFragment().show(getFragmentManager(), autofiori + webcam.substring(1), null);
			} else if (webcam.charAt(0) == camAutobspd) {
				EasyTracker.getInstance(getActivity()).send(MapBuilder.createEvent(MainActivity.EVENT_CAT_WEBCAM, MainActivity.EVENT_ACTION_OPEN, webcam, (long) 0).build());
				new WebviewDialogFragment().show(getFragmentManager(), autobspd + webcam.substring(1) + jpg, null);
			} else {
				EasyTracker.getInstance(getActivity()).send(MapBuilder.createEvent(MainActivity.EVENT_CAT_WEBCAM, MainActivity.EVENT_ACTION_REQUEST, webcam, (long) 0).build());
				new MessageDialogFragment().show(getFragmentManager(), getString(R.string.info), getString(R.string.webcamAdd), false);
			}
			return true;
		} else if (extra instanceof StreetDTO) {
			StreetDTO street = (StreetDTO) extra;
			if (street.getBadNews().size() != 0) {
				EasyTracker.getInstance(getActivity()).send(MapBuilder.createEvent(MainActivity.EVENT_CAT_BADNEWS, MainActivity.EVENT_ACTION_OPEN, street.getName(), (long) 0).build());
				new BadnewsDialogFragment().show(getFragmentManager(), street);
			}
			return true;
		}
		return false;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.replace(android.R.id.content, new MainFragment());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	private class RefreshTask extends AsyncTask<Void, Void, MainDTO> {
		@Override
		protected MainDTO doInBackground(Void... params) {
			try {
				return PersistanceService.retrieve(getActivity());
			} catch (Exception e) {
				return null;
			}
		}

		@Override
		protected void onPostExecute(MainDTO mainDTO) {
			if (mainDTO != null && mainDTO.getTrafficTime() != null) {
				getActivity().getActionBar().setSubtitle(DateFormat.getTimeFormat(getActivity()).format(mainDTO.getTrafficTime()));
				ArrayList<AbstractItem> groupItems = new ArrayList<AbstractItem>();
				ArrayList<ArrayList<AbstractItem>> childItems = new ArrayList<ArrayList<AbstractItem>>();
				for (StreetDTO street : mainDTO.getStreets()) {
					groupItems.add(new StreetItem(MainFragment.this, street));
					ArrayList<AbstractItem> childData = new ArrayList<AbstractItem>();
					if (street.getGraph().length() != 0)
						childData.add(new GraphItem(MainFragment.this, street.getGraph()));
					childData.add(new BadNewsItem(MainFragment.this, street));
					for (ZoneDTO zone : street.getZones())
						childData.add(new ZoneItem(MainFragment.this, zone));
					childItems.add(childData);
				}
				for (int i = 0; i < childItems.size(); i++) {
					int size = childItems.get(i).size();
					for (int j = 0; j < size; j++)
						if (Math.random() < 0.05 && !Utility.isAdFree(getActivity())) {
							childItems.get(i).add(j++, new AdViewItem(MainFragment.this, R.layout.adview_smart_banner));
							size++;
						}
				}
				listView.setAdapter(new HeterogeneousExpandableListAdapter(getActivity(), groupItems, childItems));
				for (int i = 0; i < listView.getExpandableListAdapter().getGroupCount(); i++)
					if (Utility.isExpanded(getActivity(), i))
						listView.expandGroup(i);
					else
						listView.collapseGroup(i);
			}
			if (Utility.isExCheck(getActivity())) {
				new MessageDialogFragment().show(getFragmentManager(), getString(R.string.error), Utility.getExMsg(getActivity()), false);
				Utility.setExCheck(getActivity(), false);
			}
		}
	}

	private final class UpdateReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			new RefreshTask().execute();
		}
	}
}
