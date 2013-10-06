package it.localhost.trafficdroid.tabFragment;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.adapter.MainAdapter;
import it.localhost.trafficdroid.adapter.item.AbstractItem;
import it.localhost.trafficdroid.common.Utility;
import it.localhost.trafficdroid.dao.PersistanceService;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.fragment.MessageDialogFragment;
import it.localhost.trafficdroid.fragment.SetupDialogFragment;
import it.localhost.trafficdroid.service.TdListener;
import it.localhost.trafficdroid.service.TdService;
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

public class MainFragment extends Fragment implements TabListener {
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
				listView.setAdapter(new MainAdapter(MainFragment.this, mainDTO, Utility.isAdFree(getActivity())));
				registerForContextMenu(listView);
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
