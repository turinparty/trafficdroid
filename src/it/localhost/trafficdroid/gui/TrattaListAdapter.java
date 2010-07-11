package it.localhost.trafficdroid.gui;

import it.localhost.trafficdroid.dto.ZoneDTO;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TrattaListAdapter extends BaseAdapter {
	private Context mContext;
	private List<ZoneDTO> mItems = new ArrayList<ZoneDTO>();

	public TrattaListAdapter(Context context) {
		mContext = context;
	}

	public void addItem(ZoneDTO it) {
		mItems.add(it);
	}

	public void setListItems(List<ZoneDTO> lit) {
		mItems = lit;
	}

	public int getCount() {
		return mItems.size();
	}

	public Object getItem(int position) {
		return mItems.get(position);
	}

	public boolean areAllItemsSelectable() {
		return false;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		TrattaView btv;
		if (convertView == null) {
			btv = new TrattaView(mContext, mItems.get(position));
		} else {
			btv = (TrattaView) convertView;
			btv.setZona(mItems.get(position));
		}
		return btv;
	}
}
