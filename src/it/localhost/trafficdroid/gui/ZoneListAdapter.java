package it.localhost.trafficdroid.gui;

import it.localhost.trafficdroid.dto.ZoneDTO;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ZoneListAdapter extends BaseAdapter {
	private Context context;
	private List<ZoneDTO> zonesDTO = new ArrayList<ZoneDTO>();

	public ZoneListAdapter(Context context) {
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
		ZoneLayout zoneLayout;
		if (convertView == null) {
			zoneLayout = new ZoneLayout(context, zonesDTO.get(position));
		} else {
			zoneLayout = (ZoneLayout) convertView;
			zoneLayout.setZona(zonesDTO.get(position));
		}
		return zoneLayout;
	}
}