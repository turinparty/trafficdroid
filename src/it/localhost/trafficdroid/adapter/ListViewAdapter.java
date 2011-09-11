package it.localhost.trafficdroid.adapter;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ListViewAdapter extends BaseAdapter {
	private List<AbstractItem> items;

	public ListViewAdapter(Context context, MainDTO mainDTO) {
		items = new ArrayList<AbstractItem>();
		List<StreetDTO> streets = mainDTO.getStreets();
		for (StreetDTO streetDTO : streets) {
			items.add(new StreetItem(context, streetDTO));
			if (streetDTO.getBadNews().size() > 0)
				items.add(new BadNewsItem(context, streetDTO));
			for (ZoneDTO zoneDTO : streetDTO.getZones())
				items.add(new ZoneItem(context, zoneDTO));
		}
	}

	@Override
	public int getItemViewType(int position) {
		return items.get(position).getType();
	}

	@Override
	public int getViewTypeCount() {
		return Const.itemTypes.length;
	}

	public int getCount() {
		return items.size();
	}

	public Object getItem(int position) {
		return items.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		AbstractItem rowModel = items.get(position);
		if (convertView == null)
			convertView = rowModel.inflateView();
		rowModel.fillView(convertView);
		return convertView;
	}
}