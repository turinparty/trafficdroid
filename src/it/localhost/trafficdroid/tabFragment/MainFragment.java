package it.localhost.trafficdroid.tabFragment;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.adapter.MainAdapter;
import it.localhost.trafficdroid.adapter.item.AbstractItem;
import it.localhost.trafficdroid.common.Utility;
import it.localhost.trafficdroid.dao.PersistanceService;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.exception.GenericException;
import it.localhost.trafficdroid.fragment.MessageDialogFragment;
import it.localhost.trafficdroid.fragment.SetupDialogFragment;
import it.localhost.trafficdroid.service.TdListener;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

public class MainFragment extends Fragment implements TabListener {
	private static final String removePrefToastUndo = " è stato aggiunto ai preferiti.";
	private static final String removePrefToast = " è stato rimosso dai preferiti.";
	private ExpandableListView listView;
	private TdListener tdListener;

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
		if (Utility.getPrefString(getActivity(), R.string.providerTrafficKey, R.string.providerTrafficDefault).equals(getString(R.string.providerTrafficDefault))) {
			new SetupDialogFragment().show(getFragmentManager(), SetupDialogFragment.class.getSimpleName());
		} else if (Utility.getPrefBoolean(getActivity(), R.string.berserkKey, R.string.berserkDefault))
			tdListener.sendWakefulWork(getActivity());
		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
		new RefreshTask().execute();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		ExpandableListContextMenuInfo info = (ExpandableListContextMenuInfo) menuInfo;
		int packedPositionType = ExpandableListView.getPackedPositionType(info.packedPosition);
		View item = info.targetView;
		if (packedPositionType == ExpandableListView.PACKED_POSITION_TYPE_GROUP || (packedPositionType == ExpandableListView.PACKED_POSITION_TYPE_CHILD && ((Integer) item.getTag(R.id.zoneType)) == AbstractItem.itemTypes[4])) {
			getActivity().getMenuInflater().inflate(R.menu.main_context, menu);
			menu.getItem(0).setChecked(Utility.getPrefBoolean(getActivity(), Integer.toString((Integer) item.getTag(R.id.itemKey)), false));
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
				if (Utility.getPrefBoolean(getActivity(), itemKey, false)) {
					item.setChecked(false);
					Utility.getEditor(getActivity()).putBoolean(itemKey, false).commit();
					msg = removePrefToast;
				} else {
					item.setChecked(true);
					Utility.getEditor(getActivity()).putBoolean(itemKey, true).commit();
					msg = removePrefToastUndo;
				}
				Toast.makeText(getActivity(), itemName + msg, Toast.LENGTH_SHORT).show();
				return true;
			default:
				return super.onContextItemSelected(item);
		}
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
				getActivity().setTitle(DateFormat.getTimeFormat(getActivity()).format(mainDTO.getTrafficTime()));
				listView.setAdapter(new MainAdapter(getActivity(), mainDTO, true));// isAdFree()
				registerForContextMenu(listView);
				for (int i = 0; i < listView.getExpandableListAdapter().getGroupCount(); i++)
					if (Utility.getPrefBoolean(getActivity(), MainAdapter.expanded + i, false))
						listView.expandGroup(i);
					else
						listView.collapseGroup(i);
			}
			if (Utility.getPrefBoolean(getActivity(), GenericException.exceptionCheck, false)) {
				String msg = Utility.getPrefString(getActivity(), GenericException.exceptionMsg, "Unknown Error");
				new MessageDialogFragment().show(getFragmentManager(), getString(R.string.error), msg, false);
				Utility.getEditor(getActivity()).putBoolean(GenericException.exceptionCheck, false).commit();
			}
		}
	}
}
