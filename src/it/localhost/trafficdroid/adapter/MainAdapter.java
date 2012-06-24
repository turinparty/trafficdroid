package it.localhost.trafficdroid.adapter;

import it.localhost.trafficdroid.adapter.item.AbstractChildItem;
import it.localhost.trafficdroid.adapter.item.AbstractItem;
import it.localhost.trafficdroid.adapter.item.BadNewsItem;
import it.localhost.trafficdroid.adapter.item.StreetItem;
import it.localhost.trafficdroid.adapter.item.ZoneItem;
import it.localhost.trafficdroid.common.TdApp;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class MainAdapter extends BaseExpandableListAdapter {
	public static final String expanded = "Expanded";
	private ArrayList<AbstractItem> groupItems;
	private ArrayList<ArrayList<AbstractChildItem>> childItems;
	public static final byte[] itemTypes = new byte[] { 0, 1, 2 };

	public MainAdapter(Context context, MainDTO mainDTO) {
		super();
		groupItems = new ArrayList<AbstractItem>();
		childItems = new ArrayList<ArrayList<AbstractChildItem>>();
		for (StreetDTO street : mainDTO.getStreets()) {
			groupItems.add(new StreetItem(context, street));
			ArrayList<AbstractChildItem> childItem = new ArrayList<AbstractChildItem>();
			childItem.add(new BadNewsItem(context, street));
			for (ZoneDTO zone : street.getZones())
				childItem.add(new ZoneItem(context, zone));
			this.childItems.add(childItem);
		}
	}

	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		AbstractItem rowModel = groupItems.get(groupPosition);
		if (convertView == null)
			convertView = rowModel.inflateView();
		rowModel.fillView(convertView);
		return convertView;
	}

	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		AbstractChildItem rowModel = childItems.get(groupPosition).get(childPosition);
		if (convertView == null)
			convertView = rowModel.inflateView();
		rowModel.fillView(convertView);
		return convertView;
	}

	public Object getGroup(int groupPosition) {
		return groupItems.get(groupPosition);
	}

	public Object getChild(int groupPosition, int childPosition) {
		return childItems.get(groupPosition).get(childPosition);
	}

	public int getGroupCount() {
		return groupItems.size();
	}

	public int getChildrenCount(int groupPosition) {
		return childItems.get(groupPosition).size();
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
		return childItems.get(groupPosition).get(childPosition).getType();
	}

	@Override
	public int getChildTypeCount() {
		return MainAdapter.itemTypes.length;
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
		TdApp.getEditor().putBoolean(expanded + groupPosition, false).commit();
	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		super.onGroupExpanded(groupPosition);
		TdApp.getEditor().putBoolean(expanded + groupPosition, true).commit();
	}
}