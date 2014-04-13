package it.localhost.trafficdroid.tabFragment;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.MainActivity;
import it.localhost.trafficdroid.adapter.HeterogeneousExpandableListAdapter;
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

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import localhost.toolkit.widget.HeterogeneousItem;
import localhost.toolkit.widget.HeterogeneousItem.OnHeterogeneousItemClickListener;
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

import com.google.android.gms.ads.AdView;

public class MainFragment extends Fragment implements TabListener {
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
	private BroadcastReceiver receiver;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.main, null);
		listView = (ExpandableListView) v.findViewById(R.id.mainTable);
		listView.setOnChildClickListener(new OnChildClickListener() {
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				((HeterogeneousItem) parent.getExpandableListAdapter().getChild(groupPosition, childPosition)).onItemClick(childPosition);
				return true;
			}
		});
		if (Utility.getProviderTraffic(getActivity()).equals(getString(R.string.providerTrafficDefault))) {
			new SetupDialogFragment().show(getFragmentManager(), SetupDialogFragment.class.getSimpleName());
		} else if (Utility.isBerserkKey(getActivity()))
			getActivity().sendBroadcast(new Intent(getString(R.string.RUN_UPDATE)));
		((MainActivity) getActivity()).sendScreenName(MainFragment.class.getSimpleName());
		new AdManager().load(getActivity(), ((AdView) v.findViewById(R.id.adView)), false);
		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
		receiver = new UpdateReceiver();
		getActivity().registerReceiver(receiver, new IntentFilter(getString(R.string.END_UPDATE)));
		new RefreshTask().execute();
	}

	@Override
	public void onPause() {
		super.onPause();
		getActivity().unregisterReceiver(receiver);
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
				ArrayList<HeterogeneousItem> groupItems = new ArrayList<HeterogeneousItem>();
				ArrayList<ArrayList<HeterogeneousItem>> childItems = new ArrayList<ArrayList<HeterogeneousItem>>();
				OnGraphItemClickListener onGraphItemClickListener = new OnGraphItemClickListener();
				OnBadNewsItemClickListener onBadNewsItemClickListener = new OnBadNewsItemClickListener();
				OnZoneItemClickListener onZoneItemClickListener = new OnZoneItemClickListener();
				for (StreetDTO street : mainDTO.getStreets()) {
					groupItems.add(new StreetItem(getActivity(), street));
					ArrayList<HeterogeneousItem> childData = new ArrayList<HeterogeneousItem>();
					if (street.getGraph().length() != 0) {
						GraphItem graphItem = new GraphItem(getActivity(), street.getGraph());
						graphItem.setOnHeterogeneousItemClickListener(onGraphItemClickListener);
						childData.add(graphItem);
					}
					BadNewsItem badNewsItem = new BadNewsItem(getActivity(), street);
					badNewsItem.setOnHeterogeneousItemClickListener(onBadNewsItemClickListener);
					childData.add(badNewsItem);
					for (ZoneDTO zone : street.getZones()) {
						ZoneItem zoneItem = new ZoneItem(getActivity(), zone);
						zoneItem.setOnHeterogeneousItemClickListener(onZoneItemClickListener);
						childData.add(zoneItem);
					}
					childItems.add(childData);
				}
				for (int i = 0; i < childItems.size(); i++) {
					int size = childItems.get(i).size();
					for (int j = 0; j < size; j++)
						if (Math.random() < 0.05 && !Utility.isAdFree(getActivity())) {
							childItems.get(i).add(j++, new AdViewItem(getActivity(), R.layout.adview_smart_banner));
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

	private class UpdateReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			new RefreshTask().execute();
		}
	}

	private class OnZoneItemClickListener implements OnHeterogeneousItemClickListener {
		@Override
		public boolean onHeterogeneousItemClick(int position, Serializable extra) {
			String webcam = ((ZoneDTO) extra).getWebcam();
			if (webcam.charAt(0) == camNone) {
				((MainActivity) getActivity()).sendEvent(MainActivity.EVENT_CAT_WEBCAM, MainActivity.EVENT_ACTION_NONE, webcam);
				new MessageDialogFragment().show(getFragmentManager(), getString(R.string.info), getString(R.string.webcamNone), false);
			} else if (webcam.charAt(0) == camAutostrade) {
				((MainActivity) getActivity()).sendEvent(MainActivity.EVENT_CAT_WEBCAM, MainActivity.EVENT_ACTION_OPEN, webcam);
				int id = Integer.parseInt(webcam.substring(1)) + 6280 * (date);
				new WebviewDialogFragment().show(getFragmentManager(), autostrade + id, null);
			} else if (webcam.charAt(0) == camCavspa) {
				((MainActivity) getActivity()).sendEvent(MainActivity.EVENT_CAT_WEBCAM, MainActivity.EVENT_ACTION_OPEN, webcam);
				new WebviewDialogFragment().show(getFragmentManager(), cavspa + webcam.substring(1) + jpg, null);
			} else if (webcam.charAt(0) == camEdidomus) {
				((MainActivity) getActivity()).sendEvent(MainActivity.EVENT_CAT_WEBCAM, MainActivity.EVENT_ACTION_OPEN, webcam);
				new WebviewDialogFragment().show(getFragmentManager(), edidomus + webcam.substring(1), null);
			} else if (webcam.charAt(0) == camAutofiori) {
				((MainActivity) getActivity()).sendEvent(MainActivity.EVENT_CAT_WEBCAM, MainActivity.EVENT_ACTION_OPEN, webcam);
				new WebviewDialogFragment().show(getFragmentManager(), autofiori + webcam.substring(1), null);
			} else if (webcam.charAt(0) == camAutobspd) {
				((MainActivity) getActivity()).sendEvent(MainActivity.EVENT_CAT_WEBCAM, MainActivity.EVENT_ACTION_OPEN, webcam);
				new WebviewDialogFragment().show(getFragmentManager(), autobspd + webcam.substring(1) + jpg, null);
			} else {
				((MainActivity) getActivity()).sendEvent(MainActivity.EVENT_CAT_WEBCAM, MainActivity.EVENT_ACTION_REQUEST, webcam);
				new MessageDialogFragment().show(getFragmentManager(), getString(R.string.info), getString(R.string.webcamAdd), false);
			}
			return true;
		}
	}

	private class OnBadNewsItemClickListener implements OnHeterogeneousItemClickListener {
		@Override
		public boolean onHeterogeneousItemClick(int position, Serializable extra) {
			StreetDTO street = (StreetDTO) extra;
			if (street.getBadNews().size() != 0) {
				((MainActivity) getActivity()).sendEvent(MainActivity.EVENT_CAT_BADNEWS, MainActivity.EVENT_ACTION_OPEN, street.getName());
				new BadnewsDialogFragment().show(getFragmentManager(), street);
			}
			return true;
		}
	}

	private class OnGraphItemClickListener implements OnHeterogeneousItemClickListener {
		@Override
		public boolean onHeterogeneousItemClick(int position, Serializable extra) {
			String graph = (String) extra;
			((MainActivity) getActivity()).sendEvent(MainActivity.EVENT_CAT_GRAPH, MainActivity.EVENT_ACTION_OPEN, graph);
			new WebviewDialogFragment().show(getFragmentManager(), firstUrl + graph + secondUrl + new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault()).format(new Date()), null);
			return true;
		}
	}
}
