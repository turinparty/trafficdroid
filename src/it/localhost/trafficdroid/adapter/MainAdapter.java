package it.localhost.trafficdroid.adapter;

import it.localhost.trafficdroid.adapter.item.AbstractItem;
import it.localhost.trafficdroid.adapter.item.BadNewsItem;
import it.localhost.trafficdroid.adapter.item.StreetItem;
import it.localhost.trafficdroid.adapter.item.ZoneItem;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class MainAdapter extends BaseExpandableListAdapter {
	private ArrayList<AbstractItem> streetItem;
	private ArrayList<ArrayList<AbstractItem>> zoneItem;

	public MainAdapter(Context context, MainDTO mainDTO) {
		super();
		streetItem = new ArrayList<AbstractItem>();
		zoneItem = new ArrayList<ArrayList<AbstractItem>>();
		ArrayList<StreetDTO> streets = mainDTO.getStreets();
		for (StreetDTO streetDTO : streets) {
			streetItem.add(new StreetItem(context, streetDTO));
			ArrayList<AbstractItem> zoneItem = new ArrayList<AbstractItem>();
			zoneItem.add(new BadNewsItem(context, streetDTO));
			//if (streetDTO.getBadNews().size() > 0)
			for (ZoneDTO zoneDTO : streetDTO.getZones())
				zoneItem.add(new ZoneItem(context, zoneDTO));
			this.zoneItem.add(zoneItem);
		}
	}

	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		AbstractItem rowModel = streetItem.get(groupPosition);
		if (convertView == null)
			convertView = rowModel.inflateView();
		rowModel.fillView(convertView);
		return convertView;
	}

	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		AbstractItem rowModel = zoneItem.get(groupPosition).get(childPosition);
		if (convertView == null)
			convertView = rowModel.inflateView();
		rowModel.fillView(convertView);
		return convertView;
	}

	public Object getGroup(int groupPosition) {
		return streetItem.get(groupPosition);
	}

	public Object getChild(int groupPosition, int childPosition) {
		return zoneItem.get(groupPosition).get(childPosition);
	}

	public int getGroupCount() {
		return streetItem.size();
	}

	public int getChildrenCount(int groupPosition) {
		return zoneItem.get(groupPosition).size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public boolean hasStableIds() {
		return true;
	}

	@Override
	public int getChildType(int groupPosition, int childPosition) {
		return zoneItem.get(groupPosition).get(childPosition).getType();
	}

	@Override
	public int getChildTypeCount() {
		return Const.itemTypes.length;
	}
}