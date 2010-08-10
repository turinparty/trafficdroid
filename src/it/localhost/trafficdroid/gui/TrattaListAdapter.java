package it.localhost.trafficdroid.gui;

import it.localhost.trafficdroid.dto.ZoneDTO;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TrattaListAdapter extends BaseAdapter {
	private Context context;
	private List<ZoneDTO> zonesDTO = new ArrayList<ZoneDTO>();

	public TrattaListAdapter(Context context) {
		this.context = context;
	}

	public void addItem(ZoneDTO it) {
		zonesDTO.add(it);
	}

	public void setListItems(List<ZoneDTO> lit) {
		zonesDTO = lit;
	}

	public int getCount() {
		return zonesDTO.size();
	}

	public Object getItem(int position) {
		return zonesDTO.get(position);
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
			btv = new TrattaView(context, zonesDTO.get(position));
		} else {
			btv = (TrattaView) convertView;
			btv.setZona(zonesDTO.get(position));
		}
		return btv;
	}
}
